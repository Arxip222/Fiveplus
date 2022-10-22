package com.fiveplus.platform.controller;


import com.fiveplus.platform.model.Purchase;
import com.fiveplus.platform.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/addPurchase")
    public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase){
        return purchaseService.addPurchase(purchase);
    }

    @PutMapping("/editPurchase/{id}")
    public ResponseEntity<Purchase> editPurchase(@RequestBody Purchase purchase, @PathVariable("id") Long id){
        return purchaseService.editPurchase(purchase, id);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable("id") Long id) {
        return purchaseService.getPurchaseById(id);
    }
}
