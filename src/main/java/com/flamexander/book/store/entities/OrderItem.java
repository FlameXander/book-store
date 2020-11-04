package com.flamexander.book.store.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "price_per_Item")
    private BigDecimal pricePerItem;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrderItem(Book book) {
        this.book = book;
        this.quantity = 1;
        this.price = new BigDecimal(0).add(book.getPrice());
    }

    public void increment() {
        this.quantity++;
        this.price = new BigDecimal(quantity * book.getPrice().doubleValue());
    }

    public void decrement() {
        this.quantity--;
        this.price = new BigDecimal(quantity * book.getPrice().doubleValue());
    }

    public boolean isEmpty() {
        return quantity == 0;
    }
}
