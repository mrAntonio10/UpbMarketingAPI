package com.upb.toffi.rest;

import com.upb.cores.SolicitudService;
import com.upb.models.archivos.Archivo;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.solicitud.dto.CharInfoDto;
import com.upb.models.solicitud.dto.SolicitudCompleteDto;
import com.upb.models.solicitud.dto.SolicitudDto;
import com.upb.models.user.dto.UserDto;
import com.upb.toffi.config.util.GenericResponse;
import com.upb.toffi.rest.request.CrearSolicitudArtesRequest;
import com.upb.toffi.rest.request.CrearSolicitudEventosRequest;
import com.upb.toffi.rest.request.CrearSolicitudVideosRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/solicitudes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class SolicitudController {
    private final SolicitudService solicitudService;


    @GetMapping("")
    public ResponseEntity<GenericResponse<PagedModel<SolicitudDto>>> getSolicitudPageable(@RequestParam(value = "tipoSolicitud", defaultValue = "") String tipoSolicitud,
                                                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                                     @RequestParam(value = "size", defaultValue = "5") Integer pageSize,
                                                                                     @RequestParam(value = "sortDir", defaultValue = "DESC")  String sortDir,
                                                                                     @RequestParam(value = "sortBy", defaultValue = "fecha") String sortBy
    ) {
        try {
            PageRequest pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(sortDir), sortBy);

            return ok(GenericResponse.success(HttpStatus.OK.value(), new PagedModel<>(
                    (this.solicitudService.getSolicitudPageable(tipoSolicitud, pageable))))
            );
        } catch (NullPointerException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(GenericResponse.error(HttpStatus.NOT_FOUND.value(),
                            e.getMessage()));
        } catch (Exception e) {
            log.error("Error genérico al obtener", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error en el servidor. Favor contactarse con el administrador."));
        }
    }


    @PostMapping("artes")
    public ResponseEntity<GenericResponse<String>> crearSolicitudArtes(@RequestBody CrearSolicitudArtesRequest a) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    solicitudService.crearSolicitudArte(auth, a.getFechaEntrega(), a.getArea(), a.getTitulo(), a.getTipoSolicitud(),
                            a.getUbicacion(), a.getTipoPost(), a.getInformacionAdicional(), a.getFechasTentativasString(), a.getNombreSolicitud(), a.getBase64ImagesList()))
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
                            v.getInformacionAdicional(), v.getFechasTentativasString(), v.getNombreSolicitud(), v.getBase64ImagesList()))
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

    @PostMapping("eventos")
    public ResponseEntity<GenericResponse<String>> crearSolicitudEventos(@RequestBody CrearSolicitudEventosRequest ev) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    solicitudService.crearSolicitudEvento(auth, ev.getFechaEntrega(), ev.getInformacion(), ev.getArea(),
                            ev.getTitulo(), ev.getInformacionAdicional(), ev.getUbicacion(), ev.getNombreSolicitud()))
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

    @PostMapping("podcast")
    public ResponseEntity<GenericResponse<String>> crearSolicitudPodcast(@RequestBody CrearSolicitudEventosRequest ev) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    solicitudService.crearSolicitudPodcast(auth, ev.getFechaEntrega(), ev.getInformacion(),
                            ev.getInformacionAdicional(), ev.getNombreSolicitud()))
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



    @GetMapping("get-files/{id}")
    public ResponseEntity<GenericResponse<List<Archivo>>> getFiles(@PathVariable(value = "id") String id
    ) {
        try {

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    (this.solicitudService.getArchivosList(id)))
            );
        } catch(NoSuchElementException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(GenericResponse.error(HttpStatus.NOT_FOUND.value(),
                            e.getMessage()));
        } catch (Exception e) {
            log.error("Error genérico al obtener", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error en el servidor. Favor contactarse con el administrador."));
        }
    }

    @GetMapping("char-info")
    public ResponseEntity<GenericResponse<List<CharInfoDto>>> getCharInfo(
    ) {
        try {

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    (this.solicitudService.getCharInfo()))
            );
        } catch(NoSuchElementException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(GenericResponse.error(HttpStatus.NOT_FOUND.value(),
                            e.getMessage()));
        } catch (Exception e) {
            log.error("Error genérico al obtener", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error en el servidor. Favor contactarse con el administrador."));
        }
    }

    @GetMapping("user-char-info")
    public ResponseEntity<GenericResponse<List<CharInfoDto>>> getUserCharInfo(
    ) {
        try {

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    (this.solicitudService.getUserCharInfo()))
            );
        } catch(NoSuchElementException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(GenericResponse.error(HttpStatus.NOT_FOUND.value(),
                            e.getMessage()));
        } catch (Exception e) {
            log.error("Error genérico al obtener", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error en el servidor. Favor contactarse con el administrador."));
        }
    }

    @GetMapping("solicitud/{id}")
    public ResponseEntity<GenericResponse<SolicitudCompleteDto>> getUserCharInfo(@PathVariable("id") String idSolicitud
    ) {
        try {

            return ok(GenericResponse.success(HttpStatus.OK.value(),
                    (this.solicitudService.getSolicitudById(idSolicitud)))
            );
        } catch(NoSuchElementException e) {
            log.error("Error {}, causa {}", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(GenericResponse.error(HttpStatus.NOT_FOUND.value(),
                            e.getMessage()));
        } catch (Exception e) {
            log.error("Error genérico al obtener", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error en el servidor. Favor contactarse con el administrador."));
        }
    }

}
