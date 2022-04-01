package booking.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import booking.rest.AbstractTest;
import booking.rest.dto.UtenteDto;
import booking.rest.security.JwtProvider;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoCameraControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setUpTest() {	
	entryList = new ArrayList<>();
	entryList.add(new UtenteDto(1, username, password));
	token = "Bearer".concat(JwtProvider.createJwt(username, entryList));		
    }

    @Test
    public void insertTipoCameraTest() throws Exception {
	this.mockMvc.perform(
		post(new URI("/insertTipoCamera"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":0,\"tipologia\":\"test\",\"posti\":2}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void insertTipoCameraWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(
		post(new URI("/insertTipoCamera"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":0,\"tipologia\":\"test\",\"posti\":2}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void editTipoCameraTest() throws Exception {
	this.mockMvc
		.perform(put(new URI("/editTipoCamera"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"test\",\"posti\":3}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void editTipoCameraWhitoutTokenTest() throws Exception {
	this.mockMvc
		.perform(put(new URI("/editTipoCamera"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"test\",\"posti\":3}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }
    
    @Test
    public void getAllTipoCameraTest() throws Exception {
	this.mockMvc.perform(get("/getAllTipoCamera").header("Authorization", token))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void getAllTipoCameraWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(get("/getAllTipoCamera"))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void getTipoCameraTest() throws Exception {
	this.mockMvc.perform(get("/getTipoCamera/{id}", 1).header("Authorization", token))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void getTipoCameraWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(get("/getTipoCamera/{id}", 1))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void deleteTipoCameraTest() throws Exception {
	this.mockMvc
		.perform(delete(new URI("/deleteTipoCamera"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"test\",\"posti\":3}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void deleteTipoCameraWhitoutTokenTest() throws Exception {
	this.mockMvc
		.perform(delete(new URI("/deleteTipoCamera"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"test\",\"posti\":3}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }
    
}
