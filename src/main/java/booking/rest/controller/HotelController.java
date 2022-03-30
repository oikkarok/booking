package booking.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import booking.rest.dto.HotelDto;
import booking.rest.service.HotelService;

@RestController
public class HotelController {

    @Autowired
    HotelService service;

    @PostMapping("/insertHotel")
    public ResponseEntity<Integer> insertHotel(@RequestBody HotelDto hotelDto) {

	try {
	    service.mapAndSaveHotel(hotelDto);
	} catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @PutMapping("/editHotel")
    public ResponseEntity<Integer> editHotel(@RequestBody HotelDto hotelDto) {

	try {
	    service.mapAndSaveHotel(hotelDto);
	} catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @GetMapping("/getHotel")
    public ResponseEntity<HotelDto> getHotel(Integer id) {

	HotelDto hotelDto = new HotelDto();
	try {
	    hotelDto = service.getById(id);
	} catch (NoSuchElementException ex) {
	    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<HotelDto>(hotelDto, HttpStatus.OK);
    }

    @GetMapping("/getAllHotel")
    public ResponseEntity<List<HotelDto>> getAllHotel() {

	List<HotelDto> lista = new ArrayList<HotelDto>();
	try {
	    lista = service.getAll();
	    if (lista.isEmpty()) {
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	    }
	} catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<List<HotelDto>>(lista, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteHotel")
    public ResponseEntity<HotelDto> deleteHotel(@RequestBody HotelDto hotelDto) {

	try {
	    service.deleteHotel(hotelDto);
	} catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
