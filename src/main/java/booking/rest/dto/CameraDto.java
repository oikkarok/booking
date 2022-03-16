package booking.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraDto {

    private int id;
    
    private TipoCameraDto tipoCamera;

    private HotelDto hotel;
    
}
