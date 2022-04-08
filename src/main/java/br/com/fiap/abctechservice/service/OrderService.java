package br.com.fiap.abctechservice.service;

import br.com.fiap.abctechservice.model.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order, List<Long> longs) throws Exception;
    List<Order> listOrderByOperator(Long operadorId);
    Order getOrderById(Long orderId);
}
