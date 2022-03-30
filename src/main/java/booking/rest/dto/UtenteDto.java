package booking.rest.dto;

import java.util.Map.Entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDto implements Entry {

    private int id;

    private String nome;

    private String password;

    @Override
    public Integer getKey() {
	
	return getId();
    }

    @Override
    public String getValue() {
	
	return getNome();
    }

    @Override
    public Object setValue(Object value) {
	// TODO Auto-generated method stub
	return null;
    }
}
