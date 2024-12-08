package com.upb.cores.impl;


import com.upb.cores.ArchivosService;
import com.upb.cores.DetalleSolicitudService;
import com.upb.cores.SolicitudService;
import com.upb.cores.utils.StringUtilMod;
import com.upb.models.archivos.Archivo;
import com.upb.models.archivos.dto.Base64DtoRequest;
import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import com.upb.models.user.User;
import com.upb.repositories.ArchivoRepository;
import com.upb.repositories.SolicitudRepository;
import com.upb.repositories.TipoSolicitudRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@AllArgsConstructor
public class ArchivosServiceImpl implements ArchivosService {
    private final ArchivoRepository archivoRepository;

    @Override
    public void  cargarArchivosSolicitud(List<Base64DtoRequest> archivosList, Solicitud solicitud) {
        for (Base64DtoRequest dto: archivosList
             ) {
            Archivo archivo = Archivo.builder()
                    .nombre(dto.getNombre())
                    .base64Data(dto.getBase64())
                    .descripcion(dto.getDescripcion())
                    .formato(dto.getFormato())
                    .solicitud(solicitud)
                    .build();

            archivoRepository.save(archivo);
        }

    }
}
