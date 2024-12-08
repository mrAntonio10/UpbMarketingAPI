package com.upb.models.archivos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Base64DtoRequest {
    //detalleSolicitud
    private String nombre;
    private String formato;
    private String base64;
    private String descripcion;

}


