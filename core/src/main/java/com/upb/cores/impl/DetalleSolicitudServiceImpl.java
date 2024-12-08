package com.upb.cores.impl;


import com.upb.cores.DetalleSolicitudService;
import com.upb.cores.SolicitudService;
import com.upb.cores.utils.StringUtilMod;
import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.repositories.DetalleSolicitudRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
@AllArgsConstructor
public class DetalleSolicitudServiceImpl implements DetalleSolicitudService {
    private final DetalleSolicitudRepository detalleSolicitudRepository;


    @Override
    public DetalleSolicitud crearDetalleSolicitudArte(LocalDateTime fechaEntrega, String area, String titulo, String ubicacion,
                                            String tipoPost, String informacionAdicional, String detalleTipoSolicitud) {
        StringUtilMod.notNullStringMaxLength(area, 30, "Área");
        StringUtilMod.notNullStringMaxLength(titulo, 120, "Título");
        StringUtilMod.notNullStringMaxLength(ubicacion, 255, "Ubición");
        StringUtilMod.notNullStringMaxLength(tipoPost, 30, "Tipo post");
        StringUtilMod.notNullStringMaxLength(informacionAdicional, 180, "Información adicional");
        StringUtilMod.notNullStringMaxLength(detalleTipoSolicitud, 30, "Tipo solicitud");

        Long start = fechaEntrega.atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        DetalleSolicitud dArte = DetalleSolicitud.builder()
                .area(area)
                .titulo(titulo)
                .ubicacion(ubicacion)
                .formatoPost(tipoPost)
                .tipo(detalleTipoSolicitud)
                .informacionAdicional(informacionAdicional)
                .fecha(start)
                .build();

        detalleSolicitudRepository.save(dArte);

        return dArte;
    }

    @Override
    public DetalleSolicitud crearDetalleSolicitudVideo(LocalDateTime fechaEntrega, String area, String tipoPost, String informacionAdicional) {
        StringUtilMod.notNullStringMaxLength(area, 30, "Área");
        StringUtilMod.notNullStringMaxLength(tipoPost, 30, "Tipo post");
        StringUtilMod.notNullStringMaxLength(informacionAdicional, 180, "Información adicional");

        Long start = fechaEntrega.atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        DetalleSolicitud dVideo = DetalleSolicitud.builder()
                .area(area)
                .formatoPost(tipoPost)
                .informacionAdicional(informacionAdicional)
                .fecha(start)
                .build();

        detalleSolicitudRepository.save(dVideo);

        return dVideo;
    }

    @Override
    public DetalleSolicitud crearDetalleSolicitudEvento(LocalDateTime fechaEntrega, String informacion, String area, String titulo, String informacionAdicional, String ubicacion) {
        StringUtilMod.notNullStringMaxLength(area, 30, "Área");
        StringUtilMod.notNullStringMaxLength(titulo, 120, "Título");
        StringUtilMod.notNullStringMaxLength(ubicacion, 255, "Ubición");
        StringUtilMod.notNullStringMaxLength(informacion, 180, "Informacion");
        StringUtilMod.notNullStringMaxLength(informacionAdicional, 180, "Información adicional");

        Long start = fechaEntrega.atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        DetalleSolicitud dArte = DetalleSolicitud.builder()
                .area(area)
                .titulo(titulo)
                .ubicacion(ubicacion)
                .informacion(informacion)
                .informacionAdicional(informacionAdicional)
                .fecha(start)
                .build();

        detalleSolicitudRepository.save(dArte);

        return dArte;
    }

    @Override
    public DetalleSolicitud crearDetalleSolicitudPodcast(LocalDateTime fechaEntrega, String informacion, String informacionAdicional) {
        StringUtilMod.notNullStringMaxLength(informacion, 180, "Informacion");
        StringUtilMod.notNullStringMaxLength(informacionAdicional, 180, "Información adicional");

        Long start = fechaEntrega.atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        DetalleSolicitud dPodcast = DetalleSolicitud.builder()
                .informacion(informacion)
                .informacionAdicional(informacionAdicional)
                .fecha(start)
                .build();

        detalleSolicitudRepository.save(dPodcast);

        return dPodcast;
    }
}
