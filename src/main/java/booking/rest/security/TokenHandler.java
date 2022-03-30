package booking.rest.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenHandler implements Serializable {

    private static final long serialVersionUID = -5594675411680465245L;

    private String token;

}
