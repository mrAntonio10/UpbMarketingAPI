package com.upb.cores.impl;


import ch.qos.logback.core.util.StringUtil;
import com.upb.cores.DetalleSolicitudService;
import com.upb.cores.RolService;
import com.upb.cores.SolicitudService;
import com.upb.cores.UserService;
import com.upb.cores.utils.StringUtilMod;
import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.rol.Rol;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import com.upb.models.user.User;
import com.upb.models.user.dto.UserDto;
import com.upb.repositories.SolicitudRepository;
import com.upb.repositories.TipoSolicitudRepository;
import com.upb.repositories.UserRepository;
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

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Slf4j
@AllArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {
    private final DetalleSolicitudService detalleSolicitudService;

    private final TipoSolicitudRepository tipoSolicitudRepository;
    private final SolicitudRepository solicitudRepository;

    @Transactional
    @Override
    public String crearSolicitudArte(Authentication auth, LocalDateTime fechaEntrega, String area, String titulo, String detalleTipoSolicitud, String ubicacion, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudService.crearDetalleSolicitudArte(fechaEntrega, area, titulo, ubicacion, tipoPost, informacionAdicional, detalleTipoSolicitud);

        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findTipoSolicitudByName("%" +nombreSolicitud.toUpperCase()+ "%")
                .orElseThrow(() -> new NoSuchElementException("No fue posible recuperar los valores correspondientes para la solicitud de nombre: " +nombreSolicitud));

        User user = (User) auth.getPrincipal();

        StringUtilMod.throwStringIsNullOrEmpty(fechasTentativasString, "Fechas tentativas");

        Solicitud solicitud = Solicitud.builder()
                .detalleSolicitud(detalleSolicitud)
                .tipoSolicitud(tipoSolicitud)
                .fechasTentativasString(fechasTentativasString)
                .usuarioSolicitante(user)
                .build();

        solicitudRepository.save(solicitud);

        return solicitud.getId();
    }

    @Override
    public String crearSolicitudVideo(Authentication auth, LocalDateTime fechaEntrega, String area, String tipoPost, String informacionAdicional, String fechasTentativasString, String nombreSolicitud) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudService.crearDetalleSolicitudVideo(fechaEntrega, area, tipoPost, informacionAdicional);

        TipoSolicitud tipoSolicitud = tipoSolicitudRepository.findTipoSolicitudByName("%" +nombreSolicitud.toUpperCase()+ "%")
                .orElseThrow(() -> new NoSuchElementException("No fue posible recuperar los valores correspondientes para la solicitud de nombre: " +nombreSolicitud));

        User user = (User) auth.getPrincipal();

        StringUtilMod.throwStringIsNullOrEmpty(fechasTentativasString, "Fechas tentativas");

        Solicitud solicitud = Solicitud.builder()
                .detalleSolicitud(detalleSolicitud)
                .tipoSolicitud(tipoSolicitud)
                .fechasTentativasString(fechasTentativasString)
                .usuarioSolicitante(user)
                .build();

        solicitudRepository.save(solicitud);

        return solicitud.getId();
    }
}
