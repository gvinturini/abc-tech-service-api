package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl (@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public void saveOrder(Order order) throws Exception {

        if (!order.hasMinAssists()) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (order.exceedsMaxAssists()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        orderRepository.save(order);

    }

    @Override
    public List<Order> listOrderByOperator(Long operadorId) {
        return null;
    }
}
