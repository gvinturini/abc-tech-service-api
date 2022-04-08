package br.com.fiap.abctechservice.application.Impl;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.AssistDTO;
import br.com.fiap.abctechservice.application.dto.OrderDTO;
import br.com.fiap.abctechservice.application.dto.OrderLocationDTO;
import br.com.fiap.abctechservice.application.dto.OrderResponseDTO;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private final OrderService orderService;

    public OrderApplicationImpl(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public OrderResponseDTO getOrder(Long id) {
        Order order = this.orderService.getOrderById(id);
        List<AssistDTO> assistDTOList = order.getServices().stream().map(assistance -> new AssistDTO(assistance.getId(), assistance.getName(), assistance.getDescription())).collect(Collectors.toList());
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order.getId(), order.getOperatorId(),
                assistDTOList,
                getOrderLocationDtoFromOrderLocation(order.getStartOrderLocation()),
                getOrderLocationDtoFromOrderLocation(order.getEndOrderLocation()));

        return orderResponseDTO;
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

    private OrderLocationDTO getOrderLocationDtoFromOrderLocation(OrderLocation orderLocation) {
        OrderLocationDTO location = new OrderLocationDTO();
        location.setLatitude(orderLocation.getLatitude());
        location.setLongitude(orderLocation.getLatitude());
        location.setDatetime(orderLocation.getDate());
        return location;
    }
}
