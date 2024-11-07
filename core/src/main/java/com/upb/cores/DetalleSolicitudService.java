package com.upb.cores;

import com.upb.models.detalle_solicitud.DetalleSolicitud;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface DetalleSolicitudService {
   DetalleSolicitud crearDetalleSolicitudArte(LocalDateTime fechaEntrega, String area, String titulo, String ubicacion, String tipoPost, String informacionAdicional, String detalleTipoSolicitud);
   DetalleSolicitud crearDetalleSolicitudVideo(LocalDateTime fechaEntrega, String area, String tipoPost, String informacionAdicional);


}