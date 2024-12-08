package com.upb.models.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InformacionSolicitudDto {
    //solicitud
    private String id;
    private String fechasTentativas;
    private String state;
    private String descripcion;
    private String fechaGeneracion;

    //usuario
    private String nombreCompleto;

    //tiposolicitud
    private String nombreSolicitud;

    //detalleSolicitud
    private String area;
    private String fechaDetalle;
    private String formatoPost;
    private String informacion;
    private String informacionAdicional;
    private String tipo;
    private String titulo;
    private String ubicacion;

}


