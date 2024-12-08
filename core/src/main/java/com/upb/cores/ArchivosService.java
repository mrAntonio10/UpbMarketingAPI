package com.upb.cores;

import com.upb.models.archivos.dto.Base64DtoRequest;
import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.solicitud.Solicitud;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ArchivosService {
   void cargarArchivosSolicitud(List<Base64DtoRequest> archivosList, Solicitud solicitud);
}
