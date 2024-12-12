package com.upb.models.solicitud.dto;

import ch.qos.logback.core.util.StringUtil;
import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCompleteDto {
   private TipoSolicitud tipoSolicitud;
   private DetalleSolicitud detalleSolicitud;
   private Solicitud solicitud;

   private LocalDateTime fechaEntrega;
    private List<String> fechaTentativaDate;

   public SolicitudCompleteDto(Solicitud s) {
       this.tipoSolicitud = s.getTipoSolicitud();
       this.detalleSolicitud = s.getDetalleSolicitud();
       this.solicitud = s;

       this.fechaEntrega = getDateTime(s.getDetalleSolicitud().getFecha());
       this.fechaTentativaDate = getDateTime(s.getFechasTentativasString());
   }

    private LocalDateTime getDateTime(Long fechaLong) {
        Instant instant = Instant.ofEpochMilli(fechaLong);
        return   LocalDateTime.ofInstant(instant, ZoneId.of("America/La_Paz"));
    }

    private List<String> getDateTime(String fechaString) {

       if (StringUtil.isNullOrEmpty(fechaString)) {
           return null;
       } else if (fechaString.contains(",")) {
            return Arrays.asList(fechaString.split(","));
        } else {
            return List.of(fechaString);
        }
   }
}


