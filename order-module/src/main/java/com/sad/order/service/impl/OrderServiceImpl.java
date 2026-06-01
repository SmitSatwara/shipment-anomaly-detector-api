package com.sad.order.service.impl;
import com.sad.common.entity.BaseEntity;
import com.sad.order.dto.*;
import com.sad.order.entity.*;
import com.sad.order.repository.*;
import com.sad.common.exception.*;
import com.sad.order.event.OrderCreatedEvent;
import com.sad.order.publisher.OrderEventPublisher;

import com.sad.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final OrderEventPublisher orderEventPublisher;

    @Override @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        //Validate customer exists — private helper method
        Customer customer = findCustomerOrThrow(createOrderRequest.getCustomerId());
        //Fetch all products in one query — validate all exist, collect missing IDs
        List<Long> productIdsFromRequest = createOrderRequest.getOrderItems()
                .stream()
                .map(OrderItemRequest::getProductId)
                .toList();
        List<Product>  productsFromDb = productRepository.findAllById(productIdsFromRequest);
        List<Long> productIdsFromDB = productsFromDb.stream().map(BaseEntity::getId).toList();

        Set<Long> returnedProductIds = new HashSet<>(productIdsFromDB);

        List<Long> missingIds = new ArrayList<>();

        for(Long productId : productIdsFromRequest) {
            if(!returnedProductIds.contains(productId)) {
                missingIds.add(productId);
            }
        }
        if(!missingIds.isEmpty()) {
            String missingIDString=String.join(", ", missingIds.stream().map(String::valueOf).toList());
            throw new ResourceNotFoundException("Missing product id from order: " + missingIDString );
        }


        //Build the Order entity — set status, reference number, customer
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setOrderReferenceNumber(generateOrderReferenceNumber());
//        orderRepository.save(order);


//        Build OrderItems — price from DB, not request
        Map<Long,Product> map = productsFromDb.stream()
                .collect(
                        Collectors.toMap(
                                BaseEntity::getId,
                                product -> product
                        ));

        List<Long> supplierIdsFromRequest = createOrderRequest.getOrderItems()
                .stream()
                .map(OrderItemRequest::getSupplierId)
                .toList();

        List<Supplier> suppliersFromDb = supplierRepository.findAllById(supplierIdsFromRequest);
        List<Long> supplierIdsFromDB = suppliersFromDb.stream().map(BaseEntity::getId).toList();
        Set<Long> returnedSupplierIds = new HashSet<>(supplierIdsFromDB);

        Map<Long,Supplier> supplierMap = suppliersFromDb.stream()
                .collect(
                        Collectors.toMap(BaseEntity::getId,supplier -> supplier)
                );

        List<Long> missingSupplierIds = new ArrayList<>();

        for(Long supplierId : supplierIdsFromRequest) {
            if(!returnedSupplierIds.contains(supplierId)) {
                missingSupplierIds.add(supplierId);
            }
        }
        if(!missingSupplierIds.isEmpty()) {
            String missingSupplierIdsString=String.join(", ", missingSupplierIds.stream().map(String::valueOf).toList());
            throw new ResourceNotFoundException("Missing supplier id from order: " + missingSupplierIdsString);
        }


        List<OrderItem>  orderItems = new ArrayList<>();
        for(OrderItemRequest od:createOrderRequest.getOrderItems()){
            Product product = map.get(od.getProductId());
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(od.getQuantity());
            orderItem.setUnitPrice(product.getUnitPrice());
            BigDecimal totalPrice = product.getUnitPrice().multiply(new BigDecimal(od.getQuantity()));
            orderItem.setTotalPrice(totalPrice);
            orderItem.setSupplier(supplierMap.get(od.getSupplierId()));
            orderItem.setOrder(order);
            orderItems.add(orderItem);



        }
        BigDecimal orderTotalPrice = BigDecimal.valueOf(0);
        for(OrderItem item:orderItems){
            orderTotalPrice = orderTotalPrice.add(item.getTotalPrice());
        }
        order.setTotalPrice(orderTotalPrice);
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        orderEventPublisher.publishOrderCreatedEvent(new OrderCreatedEvent(order.getId(),order.getOrderReferenceNumber(),LocalDateTime.now()));


        List<OrderItemResponse>  orderItemResponseList = new ArrayList<>();

        for(OrderItem od:order.getOrderItems()){
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setOrderItemId(od.getId());
            orderItemResponse.setOrderId(order.getId());
            orderItemResponse.setProductId(od.getProduct().getId());
            orderItemResponse.setSupplierId(od.getSupplier().getId());
            orderItemResponse.setQuantity(od.getQuantity());
            orderItemResponse.setUnitPrice(od.getUnitPrice());
            orderItemResponse.setTotalPrice(od.getTotalPrice());
            orderItemResponseList.add(orderItemResponse);
        }


        CreateOrderResponse  createOrderResponse = new CreateOrderResponse();
        createOrderResponse.setOrderId(order.getId());
        createOrderResponse.setOrderReferenceNumber(order.getOrderReferenceNumber());
        createOrderResponse.setOrderStatus(order.getOrderStatus().toString());
        createOrderResponse.setOrderItems(orderItemResponseList);






        return createOrderResponse;
    }

    private Customer findCustomerOrThrow(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found :"+customerId));
    }

    private String generateOrderReferenceNumber(){
        return "ORD-"+ LocalDate.now() +"-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }
}
