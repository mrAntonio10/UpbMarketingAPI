package com.upb.toffi.rest;

import com.upb.cores.SolicitudService;
import com.upb.cores.UserService;
import com.upb.models.user.dto.UserDto;
import com.upb.toffi.config.util.GenericResponse;
import com.upb.toffi.rest.request.artes.CrearSolicitudArtesRequest;
import com.upb.toffi.rest.request.artes.CrearSolicitudVideosRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/solicitudes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class SolicitudController {
    private final SolicitudService solicitudService;

    @PostMapping("artes")
    public ResponseEntity<GenericResponse<String>> crearSolicitudArtes(@RequestBody CrearSolicitudArtesRequest a) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    solicitudService.crearSolicitudArte(auth, a.getFechaEntrega(), a.getArea(), a.getTitulo(), a.getTipoSoliciud(),
                            a.getUbicacion(), a.getTipoPost(), a.getInformacionAdicional(), a.getFechasTentativasString(), a.getNombreSolicitud()))
            );
        }catch (NoSuchElementException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(GenericResponse.error(HttpStatus.NOT_FOUND.value(),
                            e.getMessage()));
        } catch (NullPointerException | IllegalArgumentException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(GenericResponse.error(HttpStatus.NOT_ACCEPTABLE.value(),
                            e.getMessage()));
        } catch (Exception e) {
            log.error("Error genérico al obtener", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error en el servidor. Favor contactarse con el administrador."));
        }
    }

    @PostMapping("videos")
    public ResponseEntity<GenericResponse<String>> crearSolicitudVideos(@RequestBody CrearSolicitudVideosRequest v) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    solicitudService.crearSolicitudVideo(auth, v.getFechaEntrega(), v.getArea(), v.getTipoPost(),
                            v.getInformacionAdicional(), v.getFechasTentativasString(), v.getNombreSolicitud()))
            );
        }catch (NoSuchElementException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(GenericResponse.error(HttpStatus.NOT_FOUND.value(),
                            e.getMessage()));
        } catch (NullPointerException | IllegalArgumentException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(GenericResponse.error(HttpStatus.NOT_ACCEPTABLE.value(),
                            e.getMessage()));
        } catch (Exception e) {
            log.error("Error genérico al obtener", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error en el servidor. Favor contactarse con el administrador."));
        }
    }

}
