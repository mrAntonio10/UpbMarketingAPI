package com.upb.toffi.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CrearSolicitudPodcastRequest {
    //detalleSolicitud
    private LocalDateTime fechaHora;
    private String informacion;
    private String informacionAdicional;

    //solicitud
    private String nombreSolicitud;

}


