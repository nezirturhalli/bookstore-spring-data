package com.example.bookstore.entity;

import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String isbn;
    private String author;
    private String title;
    private int pages;
    @Column(name = "publication_year")
    private int year;
    private double price;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] cover;

    public Book(String isbn, String author, String title, int pages, int year, double price, byte[] cover) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.year = year;
        this.price = price;
        this.cover = cover;
    }

    public String getCoverBase64() {
        if (Objects.isNull(cover))
            return null;
        return Base64.encodeBase64String(cover);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
