package com.upb.models.tipo_solicitud;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TIPO_SOLICITUD")
public class TipoSolicitud implements Serializable {
    @Id
    @Column(name = "ID")
    @UuidGenerator
    private String id;

    @Column(name = "NOMBRE", length = 60,nullable = false)
    private String nombre;

   @Column(name = "STATE")
   private Boolean state;

}
