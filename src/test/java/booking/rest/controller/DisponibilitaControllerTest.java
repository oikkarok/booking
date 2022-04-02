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
import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
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
import booking.rest.dto.CameraDto;
import booking.rest.dto.DisponibilitaDto;
import booking.rest.dto.HotelDto;
import booking.rest.dto.TipoCameraDto;
import booking.rest.dto.TipoHotelDto;
import booking.rest.dto.UtenteDto;
import booking.rest.security.JwtProvider;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DisponibilitaControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    private TipoCameraDto tipoCameraDto;
    private TipoCameraDto tipoCameraDto2;
    private TipoHotelDto tipoHotelDto;
    private HotelDto hotelDto;
    private CameraDto cameraDto;
    private CameraDto cameraDto2;
    private DisponibilitaDto disponibilitaDto;
    private DisponibilitaDto disponibilitaDto2;

    @BeforeEach
    private void setUpTest() throws URISyntaxException, Exception {
	entryList = new ArrayList<>();
	entryList.add(new UtenteDto(1, username, password));
	token = "Bearer".concat(JwtProvider.createJwt(username, entryList));

	tipoCameraDto = new TipoCameraDto(1, "test", 2);
	tipoCameraDto2 = new TipoCameraDto(2, "test", 3);
	tipoHotelDto = new TipoHotelDto(1, "test");
	hotelDto = new HotelDto(1, tipoHotelDto, "test", "test", "test", "test", 2, 2);
	cameraDto = new CameraDto(0, tipoCameraDto, hotelDto, null);
	cameraDto2 = new CameraDto(0, tipoCameraDto2, hotelDto, null);
	disponibilitaDto = new DisponibilitaDto(0, cameraDto, new Date(164884758), new Date(1648849383));
	disponibilitaDto2 = new DisponibilitaDto(0, cameraDto2, new Date(164884758), new Date(1648849383));

	this.mockMvc.perform(
		post(new URI("/insertTipoCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(tipoCameraDto)).contentType(MediaType.APPLICATION_JSON));

	this.mockMvc.perform(
		post(new URI("/insertTipoCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(tipoCameraDto2)).contentType(MediaType.APPLICATION_JSON));

	this.mockMvc.perform(
		post(new URI("/insertTipoHotel")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(tipoHotelDto)).contentType(MediaType.APPLICATION_JSON));

	this.mockMvc
		.perform(post(new URI("/insertHotel")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(hotelDto)).contentType(MediaType.APPLICATION_JSON));

	this.mockMvc.perform(
		post(new URI("/insertCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(cameraDto)).contentType(MediaType.APPLICATION_JSON));

	this.mockMvc.perform(
		post(new URI("/insertCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(cameraDto2)).contentType(MediaType.APPLICATION_JSON));

    }

//    @AfterEach
//    private void cleanEnviroment() throws URISyntaxException, Exception {
//	this.mockMvc.perform(
//		delete(new URI("/deleteTipoCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
//			.content(asJsonString(tipoCameraDto)).contentType(MediaType.APPLICATION_JSON));
//
//	this.mockMvc.perform(
//		delete(new URI("/deleteTipoCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
//			.content(asJsonString(tipoCameraDto2)).contentType(MediaType.APPLICATION_JSON));
//
//	this.mockMvc.perform(
//		delete(new URI("/deleteTipoHotel")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
//			.content(asJsonString(tipoHotelDto)).contentType(MediaType.APPLICATION_JSON));
//
//	this.mockMvc
//		.perform(delete(new URI("/deleteHotel")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
//			.content(asJsonString(hotelDto)).contentType(MediaType.APPLICATION_JSON));
//
//	this.mockMvc.perform(
//		delete(new URI("/deleteCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
//			.content(asJsonString(cameraDto)).contentType(MediaType.APPLICATION_JSON));
//
//	this.mockMvc.perform(
//		delete(new URI("/deleteCamera")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
//			.content(asJsonString(cameraDto2)).contentType(MediaType.APPLICATION_JSON));
//    }

    @Test
    public void insertDisponibilitaTest() throws Exception {
	cameraDto.setId(1);
	this.mockMvc.perform(
		post(new URI("/insertDisponibilita")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(disponibilitaDto)).contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void insertDisponibilitaWhitoutTokenTest() throws Exception {
	this.mockMvc
		.perform(post(new URI("/insertDisponibilita")).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(disponibilitaDto)).contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void editDisponibilitaTest() throws Exception {
	cameraDto2.setId(1);
	this.mockMvc.perform(
		put(new URI("/editDisponibilita")).header("Authorization", token).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(disponibilitaDto2)).contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void editDisponibilitaWhitoutTokenTest() throws Exception {
	this.mockMvc
		.perform(put(new URI("/editDisponibilita")).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(disponibilitaDto2)).contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void getAllDisponibilitaTest() throws Exception {
	this.mockMvc.perform(get("/getAllDisponibilita").header("Authorization", token)).andDo(print())
		.andExpect(status().isOk());
    }

    @Test
    public void getAllDisponibilitaWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(get("/getAllDisponibilita")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void getDisponibilitaTest() throws Exception {
	this.mockMvc.perform(get("/getDisponibilita/{id}", 1).header("Authorization", token)).andDo(print())
		.andExpect(status().isOk());
    }

    @Test
    public void getDisponibilitaWhitoutTokenTest() throws Exception {
	this.mockMvc.perform(get("/getDisponibilita/{id}", 1)).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void deleteDisponibilitaTest() throws Exception {
	this.mockMvc.perform(delete(new URI("/deleteDisponibilita")).header("Authorization", token)
		.accept(MediaType.APPLICATION_JSON).content(asJsonString(disponibilitaDto2))
		.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteDisponibilitaWhitoutTokenTest() throws Exception {
	this.mockMvc
		.perform(delete(new URI("/deleteDisponibilita")).accept(MediaType.APPLICATION_JSON)
			.content(asJsonString(disponibilitaDto2)).contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isForbidden());
    }

}
