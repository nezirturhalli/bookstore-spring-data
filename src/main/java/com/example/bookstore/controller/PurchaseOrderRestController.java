package com.example.bookstore.controller;

import com.example.bookstore.dto.BasketRequest;
import com.example.bookstore.dto.PurchaseResponse;
import com.example.bookstore.service.PurchaseOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("/orders")
@CrossOrigin
@Validated
public class PurchaseOrderRestController {

	private final PurchaseOrderService purchaseOrderService;
	
    public PurchaseOrderRestController(PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}

	@PostMapping
    public PurchaseResponse purchase(@RequestBody BasketRequest basket){
        return purchaseOrderService.makePurchase(basket);
    }

}
