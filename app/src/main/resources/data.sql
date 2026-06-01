-- Customers
INSERT INTO customers (name, email, phone, created_at, updated_at)
VALUES

    ('Rahul Sharma', 'rahul.sharma@gmail.com', '9876543210', NOW(), NOW()),
    ('Priya Patel', 'priya.patel@gmail.com', '9876543211', NOW(), NOW()),
    ('Amit Singh', 'amit.singh@gmail.com', '9876543212', NOW(), NOW());

-- Suppliers
INSERT INTO suppliers (name, created_at, updated_at)
VALUES
    ('Tata Logistics', NOW(), NOW()),
    ('Reliance Supply Co', NOW(), NOW()),
    ('Mahindra Freight', NOW(), NOW());

-- Products
INSERT INTO products (name, category, description, weight, length, width, height, unit_price, created_at, updated_at)
VALUES

    ('Laptop Dell XPS 15', 'Electronics', 'High performance laptop', 2.50, 35.00, 24.00, 2.00, 85000.00, NOW(), NOW()),
    ('iPhone 15 Pro', 'Electronics', 'Apple smartphone', 0.50, 15.00, 7.50, 0.80, 120000.00, NOW(), NOW()),
    ('Office Chair', 'Furniture', 'Ergonomic office chair', 12.00, 65.00, 65.00, 110.00, 15000.00, NOW(), NOW()),
    ('Wireless Keyboard', 'Electronics', 'Bluetooth keyboard', 0.80, 45.00, 15.00, 3.00, 3500.00, NOW(), NOW()),
    ('Samsung TV 55 inch', 'Electronics', '4K Smart TV', 18.00, 130.00, 75.00, 10.00, 65000.00, NOW(), NOW());