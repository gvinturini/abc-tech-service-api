package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDTO;
import br.com.fiap.abctechservice.application.dto.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderApplication orderApplication;


    public OrderController(@Autowired OrderApplication orderApplication) {
        this.orderApplication = orderApplication;
    }

    @PostMapping()
    public ResponseEntity createOrder(
            @Valid
            @RequestBody
                    OrderDTO orderDto
    ) throws Exception {
        orderApplication.createOrder(orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO orderResponseDTO =  this.orderApplication.getOrder(id);
        return ResponseEntity.ok(orderResponseDTO);
    }

}
