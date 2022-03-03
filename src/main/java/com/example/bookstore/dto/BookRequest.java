package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BookRequest {
    @NotBlank
    private String isbn;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @Min(20)
    private int pages;
    private int year;
    private double price;
    private String cover;

    public byte[] getBase64Cover() {
        return Base64.decodeBase64(cover);
    }


}
