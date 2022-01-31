package com.tacocloud.tacocloud.repository;

import com.tacocloud.tacocloud.domain.Order;

public interface OrderRepository {
    public Order save(Order order);
}
