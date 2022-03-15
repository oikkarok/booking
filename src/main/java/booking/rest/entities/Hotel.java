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
public class Hotel {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_sequence")
    @SequenceGenerator(name = "hotel_sequence", schema = "booking", sequenceName = "SQhotel", allocationSize = 1)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_hotel", referencedColumnName = "id", nullable=false)
    private TipoHotel tipoHotel;
    
    @OneToMany(targetEntity = Camera.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Camera> camera;
    
    private String indirizzo;
    
    private String email;
    
    private String telefono;
    
    private String nome;
    
    private int numero_camere;
    
    private int numero_stelle;
}
