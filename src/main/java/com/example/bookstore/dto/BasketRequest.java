package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class BasketRequest {
    private Collection<BookItemDTO> items;


}
