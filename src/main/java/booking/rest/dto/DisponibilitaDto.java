package booking.rest.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilitaDto {

    private int id;
    
    private CameraDto camera;
    
    private Date dataInizioPrenotazione;
    
    private Date dataFinePrenotazione;
    
}
