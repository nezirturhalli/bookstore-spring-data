package com.example.bookstore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@DynamicInsert
@DynamicUpdate
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "purchase_order_item")
    private Set<OrderItem> items;


    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
