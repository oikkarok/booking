package booking.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {

    private int id;
    
    private TipoHotelDto tipoHotel;
    
    private String indirizzo;
    
    private String email;
    
    private String telefono;
    
    private String nome;
    
    private int numero_camere;
    
    private int numero_stelle;
}
