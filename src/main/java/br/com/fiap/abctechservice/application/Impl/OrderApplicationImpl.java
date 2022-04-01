package br.com.fiap.abctechservice.application.Impl;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDTO;
import br.com.fiap.abctechservice.application.dto.OrderLocationDTO;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private final OrderService orderService;

    public OrderApplicationImpl(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public void createOrder(OrderDTO orderDto) throws Exception {
        Order order = new Order();
        order.setOperatorId(orderDto.getOperatorId());
        order.setStartOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getStart()));
        order.setEndOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getEnd()));

        this.orderService.saveOrder(order, orderDto.getServices());
    }


    private OrderLocation getOrderLocationFromOrderLocationDto(OrderLocationDTO orderLocationDto) {
        OrderLocation location = new OrderLocation();
        location.setLatitude(orderLocationDto.getLatitude());
        location.setLongitude(orderLocationDto.getLatitude());
        location.setDate(orderLocationDto.getDatetime());
        return location;
    }
}
