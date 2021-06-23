package com.flamexander.book.store.controllers;

import com.flamexander.book.store.beans.Cart;
import com.flamexander.book.store.dto.OrderDto;
import com.flamexander.book.store.entities.Order;
import com.flamexander.book.store.entities.User;
import com.flamexander.book.store.exceptions.AccessDeniedException;
import com.flamexander.book.store.exceptions.ResourceNotFoundException;
import com.flamexander.book.store.services.CartService;
import com.flamexander.book.store.services.OrderService;
import com.flamexander.book.store.services.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id, Principal principal) {
        Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find order with id: " + id));
        if (!order.getUser().getUsername().equals(principal.getName())) {
            throw new AccessDeniedException(String.format("User %s do not have access to another user's order", principal.getName()), principal);
        }
        return new OrderDto(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createNewOrder(Principal principal, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).get();
        Order result = orderService.createOrderForCurrentUser(user, address);
        return new OrderDto(result);
    }

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        return orderService.findAllByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
