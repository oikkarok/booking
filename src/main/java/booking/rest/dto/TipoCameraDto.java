package booking.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoCameraDto {

    private int id;
    
    private String tipologia;
    
    private int posti;
}
