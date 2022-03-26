package booking.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class TipoHotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertTipoHotelTest() throws Exception {
	this.mockMvc.perform(
			post(new URI("/insertTipoHotel"))
			.accept(MediaType.APPLICATION_JSON)
			.content("{\"id\":0,\"tipologia\":\"test\"}")
			.contentType(MediaType.APPLICATION_JSON))
			.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void editTipoHotelTest() throws Exception {
	this.mockMvc.perform(
		put(new URI("/editTipoHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"boh\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }   
    
    @Test
    public void getAllTipoHotelTest() throws Exception {
	this.mockMvc.perform(get("/getAllTipoHotel")).andDo(print()).andExpect(status().isOk());
    }
        
    @Test
    public void getTipoHotelTest() throws Exception {
	this.mockMvc.perform(get("/getTipoHotel/{id}", 1)).andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void deleteTipoHotelTest() throws Exception {
	this.mockMvc.perform(
		delete(new URI("/deleteTipoHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"tipologia\":\"boh\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
}
