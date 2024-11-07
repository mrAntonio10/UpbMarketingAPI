package com.upb.toffi.rest.request.artes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CrearSolicitudVideosRequest {
    //detalleSolicitud
    private String area;
    private LocalDateTime fechaEntrega;
    private String tipoPost;
    private String informacionAdicional;

    //solicitud
    private String fechasTentativasString;
    private String nombreSolicitud;

}


