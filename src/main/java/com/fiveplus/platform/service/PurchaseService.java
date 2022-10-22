package com.fiveplus.platform.service;


import com.fiveplus.platform.model.Purchase;
import com.fiveplus.platform.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepo purchaseRepo;

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
}
