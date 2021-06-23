package com.flamexander.book.store.services;

import com.flamexander.book.store.beans.Cart;
import com.flamexander.book.store.dto.OrderItemDto;
import com.flamexander.book.store.entities.Order;
import com.flamexander.book.store.entities.OrderItem;
import com.flamexander.book.store.entities.User;
import com.flamexander.book.store.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final BookService bookService;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    public Order createOrderForCurrentUser(User user, String address) {
        Order order = new Order();
        order.setUser(user);
        Cart cart = cartService.getCurrentCart(user.getUsername());
        order.setPrice(cart.getSum());
        order.setAddress(address);
        order.setItems(new ArrayList<>());
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            order.getItems().add(orderItem);
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            orderItem.setPricePerItem(o.getPricePerItem());
            orderItem.setPrice(o.getPrice());
            orderItem.setBook(bookService.findById(o.getBookId()).get());
        }
        order = orderRepository.save(order);
        cart.clear();
        cartService.save(user.getUsername(), cart);
        return order;
    }
}
