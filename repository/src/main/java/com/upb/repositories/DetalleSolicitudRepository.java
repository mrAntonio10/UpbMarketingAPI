package com.upb.repositories;


import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleSolicitudRepository extends JpaRepository<DetalleSolicitud, String> {

}
