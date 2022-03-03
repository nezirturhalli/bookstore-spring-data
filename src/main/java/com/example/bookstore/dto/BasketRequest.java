package com.example.bookstore.dto;

import java.util.Collection;
import java.util.Objects;

public class BasketRequest {
    private Collection<BookItemDTO> items;

    public BasketRequest() {
    }

    public Collection<BookItemDTO> getItems() {
        return items;
    }

    public void setItems(Collection<BookItemDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketRequest that = (BasketRequest) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "BasketDTO{" +
                "items=" + items +
                '}';
    }
}
