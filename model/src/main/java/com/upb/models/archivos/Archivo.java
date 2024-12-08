package com.upb.models.archivos;

import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.solicitud.Solicitud;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import com.upb.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ARCHIVO")
public class Archivo implements Serializable {
    @Id
    @Column(name = "ID")
    @UuidGenerator
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_SOLICITUD", referencedColumnName = "ID")
    private Solicitud solicitud;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FORMATO")
    private String formato;

    @Lob
    @Column(name = "BASE64")
    private String base64Data;

    @Column(name = "DESCRIPCION")
    private String descripcion;

}
