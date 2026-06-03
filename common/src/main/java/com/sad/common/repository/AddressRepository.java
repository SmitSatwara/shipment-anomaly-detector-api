package com.sad.common.repository;
import com.sad.common.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sad.common.entity.AddressEntityType;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByEntityIdAndEntityTypeAndIsDefaultTrue(Long entityId, AddressEntityType entityType);
}
