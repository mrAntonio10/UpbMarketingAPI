package com.upb.repositories;


import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.solicitud.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {

}
