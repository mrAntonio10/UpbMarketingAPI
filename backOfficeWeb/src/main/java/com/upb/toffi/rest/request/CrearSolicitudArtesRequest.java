package com.upb.toffi.rest.request;

import com.upb.models.archivos.dto.Base64DtoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private String tipoSolicitud;

    //solicitud
    private String fechasTentativasString;
    private String nombreSolicitud;

    //imagen
    private List<Base64DtoRequest> base64ImagesList;

}


