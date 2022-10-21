package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends CrudRepository<Purchase, Long> {
}
