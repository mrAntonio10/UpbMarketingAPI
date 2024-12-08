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
public class CrearSolicitudVideosRequest {
    //detalleSolicitud
    private String area;
    private LocalDateTime fechaEntrega;
    private String tipoPost;
    private String informacionAdicional;

    //solicitud
    private String fechasTentativasString;
    private String nombreSolicitud;

    //imagen
    private List<Base64DtoRequest> base64ImagesList;

}


