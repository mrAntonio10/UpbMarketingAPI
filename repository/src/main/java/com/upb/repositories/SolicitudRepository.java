package com.upb.repositories;


import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.solicitud.dto.SolicitudDto;
import com.upb.models.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {
    @Query("SELECT s FROM Solicitud s " +
                "INNER JOIN FETCH s.usuarioSolicitante u " +
                "INNER JOIN FETCH s.tipoSolicitud t " +
                "INNER JOIN FETCH s.detalleSolicitud d " +
            "WHERE (:name IS NULL OR UPPER(t.nombre) LIKE :name)"
    )
    Page<SolicitudDto> getSolictudPageable(@Param("name") String name,
                                       Pageable pageable);

    @Query("SELECT s FROM Solicitud s " +
                "INNER JOIN FETCH s.usuarioSolicitante u " +
                "INNER JOIN FETCH s.tipoSolicitud t " +
                "INNER JOIN FETCH s.detalleSolicitud d " +
            "WHERE s.fecha < :actual " +
                "AND s.fecha >= :semana"
    )
    List<SolicitudDto> getSolictudForCharInfo(@Param("actual") Long fechaActual,
                                              @Param("semana") Long fecha2SemanasAtras);
}
