package com.example.bookstore.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PurchaseEvent extends BusinessEvent {

    private final double sum;


}
