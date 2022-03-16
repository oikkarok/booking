package booking.rest.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Disponibilita {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disponibilita")
    @SequenceGenerator(name = "disponibilita", schema = "booking", sequenceName = "SQdisponibilita", allocationSize = 1)
    private int id;
    
    @NonNull
    @OneToOne(targetEntity = Camera.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Camera camera;
    
    private Date dataInizioPrenotazione;
    
    private Date dataFinePrenotazione;
}
