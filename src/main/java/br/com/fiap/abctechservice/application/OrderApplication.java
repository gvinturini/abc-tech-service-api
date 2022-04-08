package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.OrderDTO;
import br.com.fiap.abctechservice.application.dto.OrderResponseDTO;


public interface OrderApplication {

    public OrderResponseDTO getOrder(Long id);
    public void createOrder(OrderDTO orderDTO) throws Exception;
}
