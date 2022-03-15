package booking.rest.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class TipoHotel {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_hotel_sequence")
    @SequenceGenerator(name = "tipo_hotel_sequence", schema = "booking", sequenceName = "SQtipo_hotel", allocationSize = 1)
    private int id;
    
    @OneToMany(targetEntity = Hotel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Hotel> hotel;
    
    private String tipologia;
}
