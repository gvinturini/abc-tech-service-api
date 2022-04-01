package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.OrderDTO;


public interface OrderApplication {

    public void createOrder(OrderDTO orderDTO) throws Exception;
}
