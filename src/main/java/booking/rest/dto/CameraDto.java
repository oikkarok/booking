package booking.rest.dto;

import java.util.List;

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
    
    private List<DisponibilitaDto> disponibilita;
}
