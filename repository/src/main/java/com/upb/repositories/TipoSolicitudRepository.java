package com.upb.repositories;


import com.upb.models.rol.Rol;
import com.upb.models.rol.dto.RolForListDto;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoSolicitudRepository extends JpaRepository<TipoSolicitud, String> {
    @Query("SELECT ts FROM TipoSolicitud ts " +
                "WHERE UPPER(ts.nombre) LIKE :name "
    )
    Optional<TipoSolicitud> findTipoSolicitudByName(@Param("name") String nombreTipoSolicitud);
}
