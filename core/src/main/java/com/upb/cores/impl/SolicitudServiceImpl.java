package com.upb.cores.impl;


import com.ctc.wstx.util.StringUtil;
import com.upb.cores.*;
import com.upb.cores.utils.StringUtilMod;
import com.upb.models.archivos.Archivo;
import com.upb.models.archivos.dto.Base64DtoRequest;
import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.rol.Rol;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.solicitud.dto.CharInfoDto;
import com.upb.models.solicitud.dto.SolicitudDto;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import com.upb.models.user.User;
import com.upb.models.user.dto.UserDto;
import com.upb.repositories.ArchivoRepository;
import com.upb.repositories.SolicitudRepository;
import com.upb.repositories.TipoSolicitudRepository;
import com.upb.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {
    private final DetalleSolicitudService detalleSolicitudService;
    private final ArchivosService archivosService;

    private final TipoSolicitudRepository tipoSolicitudRepository;
    private final SolicitudRepository solicitudRepository;
    private final ArchivoRepository archivoRepository;

    @Transactional
    @Override
    public String crearSolicitudArte(Authentication auth, LocalDateTime fechaEntrega, String area, String titulo, String detalleTipoSolicitud, String ubicacion, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud, List<Base64DtoRequest> listaArchivos) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudService.crearDetalleSolicitudArte(fechaEntrega, area, titulo, ubicacion, tipoPost, informacionAdicional, detalleTipoSolicitud);

        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findTipoSolicitudByName("%" +nombreSolicitud.toUpperCase()+ "%")
                .orElseThrow(() -> new NoSuchElementException("No fue posible recuperar los valores correspondientes para la solicitud de nombre: " +nombreSolicitud));

        User user = (User) auth.getPrincipal();

        StringUtilMod.throwStringIsNullOrEmpty(fechasTentativasString, "Fechas tentativas");

        Long fechaCreacion = LocalDateTime.now(ZoneId.of("America/La_Paz")).atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        Solicitud solicitud = Solicitud.builder()
                .detalleSolicitud(detalleSolicitud)
                .tipoSolicitud(tipoSolicitud)
                .fechasTentativasString(fechasTentativasString)
                .usuarioSolicitante(user)
                .state(false)
                .fecha(fechaCreacion)
                .build();

        solicitudRepository.save(solicitud);

        archivosService.cargarArchivosSolicitud(listaArchivos, solicitud);

        return solicitud.getId();
    }
    @Transactional
    @Override
    public String crearSolicitudVideo(Authentication auth, LocalDateTime fechaEntrega, String area, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud, List<Base64DtoRequest> listaArchivos) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudService.crearDetalleSolicitudVideo(fechaEntrega, area, tipoPost, informacionAdicional);

        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findTipoSolicitudByName("%" +nombreSolicitud.toUpperCase()+ "%")
                .orElseThrow(() -> new NoSuchElementException("No fue posible recuperar los valores correspondientes para la solicitud de nombre: " +nombreSolicitud));

        User user = (User) auth.getPrincipal();

        log.info("ASDKAJHADAD {}", fechasTentativasString);

        StringUtilMod.throwStringIsNullOrEmpty(fechasTentativasString, "Fechas tentativas");

        Long fechaCreacion = LocalDateTime.now(ZoneId.of("America/La_Paz")).atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        Solicitud solicitud = Solicitud.builder()
                .detalleSolicitud(detalleSolicitud)
                .tipoSolicitud(tipoSolicitud)
                .fechasTentativasString(fechasTentativasString)
                .usuarioSolicitante(user)
                .state(false)
                .fecha(fechaCreacion)
                .build();

        solicitudRepository.save(solicitud);

        archivosService.cargarArchivosSolicitud(listaArchivos, solicitud);

        return solicitud.getId();
    }

    @Transactional
    @Override
    public String crearSolicitudEvento(Authentication auth, LocalDateTime fechaEntrega, String informacion, String area, String titulo, String informacionAdicional, String ubicacion, String nombreSolicitud) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudService.crearDetalleSolicitudEvento(fechaEntrega, informacion, area, titulo, informacionAdicional, ubicacion);

        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findTipoSolicitudByName("%" +nombreSolicitud.toUpperCase()+ "%")
                .orElseThrow(() -> new NoSuchElementException("No fue posible recuperar los valores correspondientes para la solicitud de nombre: " +nombreSolicitud));

        User user = (User) auth.getPrincipal();

        Long fechaCreacion = LocalDateTime.now(ZoneId.of("America/La_Paz")).atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        Solicitud solicitud = Solicitud.builder()
                .detalleSolicitud(detalleSolicitud)
                .tipoSolicitud(tipoSolicitud)
                .usuarioSolicitante(user)
                .state(false)
                .fecha(fechaCreacion)
                .build();

        solicitudRepository.save(solicitud);

        return solicitud.getId();
    }
    @Transactional
    @Override
    public String crearSolicitudPodcast(Authentication auth, LocalDateTime fechaEntrega, String informacion, String informacionAdicional, String nombreSolicitud) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudService.crearDetalleSolicitudPodcast(fechaEntrega, informacion, informacionAdicional);

        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findTipoSolicitudByName("%" +nombreSolicitud.toUpperCase()+ "%")
                .orElseThrow(() -> new NoSuchElementException("No fue posible recuperar los valores correspondientes para la solicitud de nombre: " +nombreSolicitud));

        User user = (User) auth.getPrincipal();

        Long fechaCreacion = LocalDateTime.now(ZoneId.of("America/La_Paz")).atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        Solicitud solicitud = Solicitud.builder()
                .detalleSolicitud(detalleSolicitud)
                .tipoSolicitud(tipoSolicitud)
                .usuarioSolicitante(user)
                .state(false)
                .fecha(fechaCreacion)
                .build();

        solicitudRepository.save(solicitud);

        return solicitud.getId();
    }

    @Override
    public Page<SolicitudDto> getSolicitudPageable(String tipoSolicitud, Pageable pageable) {
        tipoSolicitud = StringUtils.isEmpty(tipoSolicitud) ? null : "%" +tipoSolicitud.toUpperCase()+ "%";

        return solicitudRepository.getSolictudPageable(tipoSolicitud, pageable);
    }

    @Transactional
    @Override
    public List<Archivo> getArchivosList(String idSolicitud) {
        StringUtilMod.throwStringIsNullOrEmpty(idSolicitud, "Id solicitud");

        return archivoRepository.archivoList(idSolicitud);
    }

    @Transactional
    @Override
    public List<CharInfoDto> getCharInfo() {
        Long fechaActual = LocalDateTime.now(ZoneId.of("America/La_Paz")).atZone(ZoneId.of("America/La_Paz")).toInstant().toEpochMilli();

        Long fechaAtras = LocalDateTime.now(ZoneId.of("America/La_Paz")).atZone(ZoneId.of("America/La_Paz")).minusDays(14).toInstant().toEpochMilli();

        return solicitudRepository.getSolictudForCharInfo(fechaActual, fechaAtras).stream()
                .collect(Collectors.groupingBy(SolicitudDto::getState,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> {
                                    String tipoSolicitud = list.get(0).getState();

                                    int cantidad = list.size();

                                    return new CharInfoDto(tipoSolicitud, cantidad);
                                }
                        )
                ))
                .values()
                .stream()
                .toList();

    }


}
