package booking.rest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Utente {
    
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_sequence")
    @SequenceGenerator(name = "utente_sequence", schema = "booking", sequenceName = "SQutente", allocationSize = 1)
    private int id;
    
    @NonNull
    private String nome;
    
    @NonNull
    private String password;  

}
