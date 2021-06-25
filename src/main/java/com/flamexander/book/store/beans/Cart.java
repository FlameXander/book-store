package com.flamexander.book.store.beans;

import com.flamexander.book.store.dto.OrderItemDto;
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

@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    public Cart() {
        items = new ArrayList<>();
        sum = BigDecimal.ZERO;
    }

    public boolean addToCart(Long id) {
        for (OrderItemDto o : items) {
            if (o.getBookId().equals(id)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decrementBook(Long id) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getBookId().equals(id)) {
                o.changeQuantity(-1);
                if (o.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void removeBook(Long id) {
        items.removeIf(i -> i.getBookId().equals(id));
        recalculate();
    }

    public void addToCart(Book book) {
        items.add(new OrderItemDto(book));
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItemDto o : items) {
            sum = sum.add(o.getPrice());
        }
    }

    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getBookId().equals(anotherItem.getBookId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
