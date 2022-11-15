package com.fiveplus.platform.service;


import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.model.Purchase;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepo purchaseRepo;
    @Autowired
    UserService userService;

    public ResponseEntity<Purchase> addPurchase(Purchase purchase){
        purchaseRepo.save(purchase);
        return new ResponseEntity<Purchase>(HttpStatus.OK);
    }

    public ResponseEntity<Purchase> editPurchase(Purchase purchase, Long id){
        try {
            Purchase purchase1 = purchaseRepo.findById(id).get();
            purchase1.setType(purchase.getType());
            purchase1.setQuantity(purchase.getQuantity());
            purchaseRepo.save(purchase1);
            return new ResponseEntity<Purchase>(purchase1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Purchase>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Purchase> getPurchaseById(Long id){
        try {
            return new ResponseEntity<Purchase>(purchaseRepo.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Purchase>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<List<Purchase>> getPurchaseByParentId(Long id){
        User parent = userService.getUserById(id).getBody();
        if (parent != null) {
            return new ResponseEntity<List<Purchase>>(purchaseRepo.findByOwner(parent), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
