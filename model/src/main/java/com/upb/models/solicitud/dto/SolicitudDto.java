package com.upb.models.solicitud.dto;

import com.upb.models.solicitud.Solicitud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    //solicitud
    private String id;
    private String state;
    private String fecha;

    //usuario
    private String nombreCompleto;
    private String email;

    //tiposolicitud
    private String nombreSolicitud;

    public SolicitudDto(Solicitud solicitud) {
        this.id = solicitud.getId();
        this.nombreCompleto = solicitud.getUsuarioSolicitante().getName() + " " + solicitud.getUsuarioSolicitante().getLastname();
        this.email = solicitud.getUsuarioSolicitante().getEmail();
        this.nombreSolicitud = solicitud.getTipoSolicitud().getNombre();

        this.state = estadoSolicitud(solicitud.getDetalleSolicitud().getFecha(), solicitud.getState());
        this.fecha = this.getDateTime(ZonedDateTime.ofInstant(Instant.ofEpochMilli(solicitud.getFecha()), ZoneId.of("America/La_Paz")).toLocalDateTime());
    }


    private String estadoSolicitud(Long fechaDetalleEntrega, Boolean state) {
        Long fechaActual = LocalDateTime.now(ZoneId.of("America/La_Paz")).atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        Long compare1day = 24 * 60 * 60 * 1000L;
        Long compare3days = 48 * 60 * 60 * 1000L;
        Long compare5days = 48 *48 * 24 * 60 * 60 * 1000L;

        return state ? "Finalizado" :
                Math.abs(fechaActual - fechaDetalleEntrega) <= compare1day ? "Inmediato" :
                Math.abs(fechaActual - fechaDetalleEntrega) <= compare3days ? "Moderado" :
                    Math.abs(fechaActual - fechaDetalleEntrega) <= compare5days ? "Aceptable" : "Archivado";
    }

    private String getDateTime(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd-MMMM-yyyy HH:mm:ss", new Locale("es", "ES"));

        return dt.format(formatter).substring(0, 1).toUpperCase() + dt.format(formatter).substring(1).toLowerCase();
    }
}


