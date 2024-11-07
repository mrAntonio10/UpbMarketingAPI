package com.upb.toffi.rest.request.artes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CrearSolicitudArtesRequest {
    //detalleSolicitud
    private LocalDateTime fechaEntrega;
    private String area;
    private String titulo;
    private String ubicacion;
    private String tipoPost;
    private String informacionAdicional;
    private String tipoSoliciud;

    //solicitud
    private String fechasTentativasString;
    private String nombreSolicitud;

}


