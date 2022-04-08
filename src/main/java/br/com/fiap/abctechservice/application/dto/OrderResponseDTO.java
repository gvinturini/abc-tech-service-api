package br.com.fiap.abctechservice.application.dto;

import br.com.fiap.abctechservice.model.Assistance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDTO {

    private Long id;
    private Long operatorId;
    private List<AssistDTO> services;
    private OrderLocationDTO start;
    private OrderLocationDTO end;
}
