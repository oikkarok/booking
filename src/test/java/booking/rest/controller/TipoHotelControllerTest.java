package booking.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;

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
public class TipoHotelControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    private void setUpTest() {	
	entryList = new ArrayList<>();
	entryList.add(new UtenteDto(1, username, password));
	token = "Bearer".concat(JwtProvider.createJwt(username, entryList));		
    }

    @Test
    public void insertTipoHotelTest() throws Exception {
	setUpTest();
	this.mockMvc.perform(
		post(new URI("/insertTipoHotel"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":0,\"tipologia\":\"test\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void insertTipoHotelWrongTest() throws Exception {
	setUpTest();
	this.mockMvc.perform(
		post(new URI("/insertTipoHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":0,\"tipologia\":\"test\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void editTipoHotelTest() throws Exception {
	setUpTest();
	this.mockMvc
		.perform(put(new URI("/editTipoHotel"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"boh\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void editTipoHotelWrongTest() throws Exception {
	setUpTest();
	this.mockMvc
		.perform(put(new URI("/editTipoHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"boh\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }
    
    @Test
    public void getAllTipoHotelTest() throws Exception {
	setUpTest();
	this.mockMvc.perform(get("/getAllTipoHotel").header("Authorization", token))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void getAllTipoHotelWrongTest() throws Exception {
	setUpTest();
	this.mockMvc.perform(get("/getAllTipoHotel"))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void getTipoHotelTest() throws Exception {
	setUpTest();
	this.mockMvc.perform(get("/getTipoHotel/{id}", 1).header("Authorization", token))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void getTipoHotelWrongTest() throws Exception {
	setUpTest();
	this.mockMvc.perform(get("/getTipoHotel/{id}", 1))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void deleteTipoHotelTest() throws Exception {
	setUpTest();
	this.mockMvc
		.perform(delete(new URI("/deleteTipoHotel"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"boh\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void deleteTipoHotelWrongTest() throws Exception {
	setUpTest();
	this.mockMvc
		.perform(delete(new URI("/deleteTipoHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"boh\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }
}
