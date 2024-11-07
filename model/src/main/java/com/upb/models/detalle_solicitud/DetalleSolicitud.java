package com.upb.models.detalle_solicitud;

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
@Table(name = "DETALLE_SOLICITUD")
public class DetalleSolicitud implements Serializable {
    @Id
    @Column(name = "ID")
    @UuidGenerator
    private String id;

    @Column(name = "INFORMACION", length = 180)
    private String informacion;

   @Column(name = "FECHA")
   private Long fecha;

    @Column(name = "AREA", length = 30)
    private String area;

    @Column(name = "FORMATO_POST", length = 30)
    private String formatoPost;

    @Column(name = "TIPO", length = 30)
    private String tipo;

    @Column(name = "TITULO", length = 120)
    private String titulo;

    @Column(name = "UBICACION", length = 255)
    private String ubicacion;

    @Column(name = "INFORMACION_ADICIONAL", length = 180)
    private String informacionAdicional;
}
