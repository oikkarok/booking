package booking.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import booking.rest.AbstractTest;
import booking.rest.dto.UtenteDto;
import booking.rest.security.JwtProvider;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtenteControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setUpTest() {	
	entryList = new ArrayList<>();
	entryList.add(new UtenteDto(1, username, password));
	token = "Bearer".concat(JwtProvider.createJwt(username, entryList));		
    }

    @Test
    public void getUtenteTest() throws Exception {
	this.mockMvc.perform(get("/getUtenteByNome/{nome}", username).header("Authorization", token))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void getUtenteWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(get("/getUtenteByNome/{nome}", username))
		.andDo(print()).andExpect(status().isForbidden());
    }
    
}
