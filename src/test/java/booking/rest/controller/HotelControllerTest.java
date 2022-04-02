package booking.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
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
import booking.rest.dto.HotelDto;
import booking.rest.dto.TipoHotelDto;
import booking.rest.dto.UtenteDto;
import booking.rest.security.JwtProvider;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;
    
    private TipoHotelDto tipoHotelDto;
    private HotelDto hotelDto;
    private HotelDto hotelDto2;
    
    @BeforeEach
    private void setUpTest() throws URISyntaxException, Exception {	
	entryList = new ArrayList<>();
	entryList.add(new UtenteDto(1, username, password));
	token = "Bearer".concat(JwtProvider.createJwt(username, entryList));	
	
	tipoHotelDto = new TipoHotelDto(1, "test");
	hotelDto = new HotelDto(1, tipoHotelDto, "test", "test", "test", "test", 2, 2);
	hotelDto2 = new HotelDto(1, tipoHotelDto, "test", "test", "test", "test", 2, 3);
	
	this.mockMvc.perform(
		post(new URI("/insertTipoHotel"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content(asJsonString(tipoHotelDto))
		.contentType(MediaType.APPLICATION_JSON));	
    }

    @Test
    public void insertHotelTest() throws Exception {
	this.mockMvc.perform(
		post(new URI("/insertHotel"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content(asJsonString(hotelDto))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void insertHotelWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(
		post(new URI("/insertHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content(asJsonString(hotelDto))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void editHotelTest() throws Exception {
	this.mockMvc
		.perform(put(new URI("/editHotel"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content(asJsonString(hotelDto2))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void editHotelWhitoutTokenTest() throws Exception {
	this.mockMvc
		.perform(put(new URI("/editHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content(asJsonString(hotelDto2))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }
    
    @Test
    public void getAllHotelTest() throws Exception {
	this.mockMvc.perform(get("/getAllHotel").header("Authorization", token))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void getAllHotelWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(get("/getAllHotel"))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void getHotelTest() throws Exception {
	this.mockMvc.perform(get("/getHotel/{id}", 1).header("Authorization", token))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void getHotelWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(get("/getHotel/{id}", 1))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void deleteHotelTest() throws Exception {
	this.mockMvc
		.perform(delete(new URI("/deleteHotel"))
		.header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON)
		.content(asJsonString(hotelDto2))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void deleteHotelWhitoutTokenTest() throws Exception {
	this.mockMvc
		.perform(delete(new URI("/deleteHotel"))
		.accept(MediaType.APPLICATION_JSON)
		.content(asJsonString(hotelDto2))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }
    
}
