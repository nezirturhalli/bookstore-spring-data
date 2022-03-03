package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookItemDTO {
    private String isbn;
    private int quantity;


}
