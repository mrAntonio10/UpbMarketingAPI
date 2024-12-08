package com.upb.toffi.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CrearSolicitudEventosRequest {
    //detalleSolicitud
    private String informacion;
    private String area;
    private String titulo;
    private LocalDateTime fechaEntrega;
    private String ubicacion;
    private String informacionAdicional;

    //solicitud
    private String nombreSolicitud;

}


