package booking.rest.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
public class Camera {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "camera_sequence")
    @SequenceGenerator(name = "camera_sequence", schema = "booking", sequenceName = "SQcamera", allocationSize = 1)
    private int id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_camera", referencedColumnName = "id", nullable=false)
    private TipoCamera tipoCamera;
    
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hotel", referencedColumnName = "id", nullable=false)
    private Hotel hotel;
    
    @OneToMany(targetEntity = Disponibilita.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Disponibilita> disponibilita;

}
