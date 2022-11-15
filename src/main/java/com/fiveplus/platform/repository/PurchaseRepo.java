package com.fiveplus.platform.repository;

import com.fiveplus.platform.model.Purchase;
import com.fiveplus.platform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepo extends CrudRepository<Purchase, Long> {
    List<Purchase> findByOwner(User user);
 }
