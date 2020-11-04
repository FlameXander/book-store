package com.flamexander.book.store.beans;

import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.entities.OrderItem;
import com.flamexander.book.store.exceptions.ResourceNotFoundException;
import com.flamexander.book.store.services.BookService;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private final BookService bookService;
    private List<OrderItem> items;
    private BigDecimal price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addOrIncrement(Long productId) {
        for (OrderItem o : items) {
            if (o.getBook().getId().equals(productId)) {
                o.increment();
                recalculate();
                return;
            }
        }
        Book b = bookService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to find book with id: " + productId + " (add to cart)"));
        items.add(new OrderItem(b));
        recalculate();
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getBook().getId().equals(productId)) {
                o.decrement();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getBook().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = new BigDecimal(0.0);
        for (OrderItem o : items) {
            o.setPricePerItem(o.getBook().getPrice());
            o.setPrice(new BigDecimal(o.getBook().getPrice().doubleValue()).multiply(BigDecimal.valueOf(o.getQuantity())));
            price = price.add(o.getPrice());
        }
    }

    public void clear() {
        items.clear();
        price = new BigDecimal(0.0);
    }
}
