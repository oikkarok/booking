package booking.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import booking.rest.dto.TipoHotelDto;
import booking.rest.service.TipoHotelService;

@RestController
public class TipoHotelController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TipoHotelService service;
    
    private static final Logger logger = LoggerFactory.getLogger(TipoHotelController.class);

    @PostMapping("/insertTipoHotel")
    public ResponseEntity<Integer> insertTipoHotel(@RequestBody TipoHotelDto tipoHotelDto) {

	try {
	    service.mapAndSaveTipoHotel(tipoHotelDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @PutMapping("/editTipoHotel")
    public ResponseEntity<Integer> editTipoHotel(@RequestBody TipoHotelDto tipoHotelDto) {

	try {
	    service.mapAndSaveTipoHotel(tipoHotelDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @GetMapping("/getTipoHotel")
    public ResponseEntity<TipoHotelDto> getTipoHotel(Integer id) {

	TipoHotelDto tipoHotelDto = new TipoHotelDto();
	try {
	    tipoHotelDto = service.getById(id);
	} catch (NoSuchElementException ex) {
	    logger.error(ex.getMessage(), ex);
	    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<TipoHotelDto>(tipoHotelDto, HttpStatus.OK);
    }

    @GetMapping("/getAllTipoHotel")
    public ResponseEntity<List<TipoHotelDto>> getAllTipoHotel() {

	List<TipoHotelDto> lista = new ArrayList<TipoHotelDto>();
	try {
	    lista = service.getAll();
	    if (lista.isEmpty()) {
		logger.info("getAllTipoHotel: Nessun elemento trovato");
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<List<TipoHotelDto>>(lista, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteTipoHotel")
    public ResponseEntity<TipoHotelDto> deleteTipoHotel(@RequestBody TipoHotelDto tipoHotelDto) {

	try {
	    service.deleteTipoHotel(tipoHotelDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
