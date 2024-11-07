package com.upb.cores;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface SolicitudService {
   String crearSolicitudArte(Authentication auth, LocalDateTime fechaEntrega, String area, String titulo, String tipoSolicitud, String ubicacion, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud);

   String crearSolicitudVideo(Authentication auth, LocalDateTime fechaEntrega, String area, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud);

}
