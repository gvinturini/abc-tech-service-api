package br.com.fiap.abctechservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLocationDTO {

    private Double latitude;
    private Double longitude;
    private Date datetime;
}
