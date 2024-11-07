package com.upb.models.solicitud;

import com.upb.models.detalle_solicitud.DetalleSolicitud;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import com.upb.models.user.User;
import jakarta.persistence.*;
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
@Table(name = "SOLICITUD")
public class Solicitud implements Serializable {
    @Id
    @Column(name = "ID")
    @UuidGenerator
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_TIPO_SOLICITUD", referencedColumnName = "ID")
    private TipoSolicitud tipoSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_DETALLE_SOLICITUD", referencedColumnName = "ID")
    private DetalleSolicitud detalleSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_USUARIO", referencedColumnName = "ID")
    private User usuarioSolicitante;

   @Column(name = "STATE")
   private Boolean state;

    @Column(name = "FECHAS_TENTATIVAS")
    private String fechasTentativasString;

}
