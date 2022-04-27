package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.Impl.OrderApplicationImpl;
import br.com.fiap.abctechservice.application.dto.OrderDTO;
import br.com.fiap.abctechservice.application.dto.OrderLocationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderApplicationImpl orderApplication;

    @InjectMocks
    private OrderController orderController;

    @Autowired
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void createOrder_success() throws Exception {
        OrderLocationDTO start = new OrderLocationDTO(1.2, 2.1, new Date(2022, 04, 26));
        OrderLocationDTO end = new OrderLocationDTO(1.3, 3.1, new Date(2022, 04, 26));
        OrderDTO orderDTO = new OrderDTO(1L, new ArrayList<Long>(Arrays.asList(1L, 2L)), start, end);

        //Mockito.when(orderApplication.createOrder(Mockito.any(OrderDTO.class))).thenReturn(orderDTO);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(orderDTO));

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
