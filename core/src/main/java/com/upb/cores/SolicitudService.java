package com.upb.cores;

import com.upb.models.archivos.Archivo;
import com.upb.models.archivos.dto.Base64DtoRequest;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.solicitud.dto.CharInfoDto;
import com.upb.models.solicitud.dto.SolicitudDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SolicitudService {
   String crearSolicitudArte(Authentication auth, LocalDateTime fechaEntrega, String area, String titulo, String tipoSolicitud, String ubicacion, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud, List<Base64DtoRequest> listaArchivos);
   String crearSolicitudVideo(Authentication auth, LocalDateTime fechaEntrega, String area, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud, List<Base64DtoRequest> listaArchivos);
   String crearSolicitudEvento(Authentication auth, LocalDateTime fechaEntrega, String informacion, String area, String titulo, String informacionAdicional, String ubicacion, String nombreSolicitud);
   String crearSolicitudPodcast(Authentication auth, LocalDateTime fechaEntrega, String informacion, String informacionAdicional, String nombreSolicitud);
   Page<SolicitudDto> getSolicitudPageable(String tipoSolicitud, Pageable pageable);
   List<Archivo> getArchivosList(String idSolicitud);
   List<CharInfoDto> getCharInfo();
}
