package com.upb.models.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CharInfoDto {
    private String estado;
    private int cantidad;

}
