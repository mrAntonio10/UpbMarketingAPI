package com.upb.repositories;


import com.upb.models.archivos.Archivo;
import com.upb.models.rol.Rol;
import com.upb.models.rol.dto.RolForListDto;
import com.upb.models.solicitud.dto.SolicitudDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, String> {
    @Query("SELECT a FROM Archivo a " +
                "INNER JOIN FETCH a.solicitud s " +
            "WHERE s.id =:idSolicitud"
    )
    List<Archivo> archivoList(@Param("idSolicitud") String id);
}
