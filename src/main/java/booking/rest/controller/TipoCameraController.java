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

import booking.rest.dto.TipoCameraDto;
import booking.rest.service.TipoCameraService;

@RestController
public class TipoCameraController {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TipoCameraService service;
    
    private static final Logger logger = LoggerFactory.getLogger(TipoCameraController.class);

    @PostMapping("/insertTipoCamera")
    public ResponseEntity<Integer> insertTipoCamera(@RequestBody TipoCameraDto tipoCameraDto) {

	try {
	    service.mapAndSaveTipoCamera(tipoCameraDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @PutMapping("/editTipoCamera")
    public ResponseEntity<Integer> editTipoCamera(@RequestBody TipoCameraDto tipoCameraDto) {

	try {
	    service.mapAndSaveTipoCamera(tipoCameraDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @GetMapping("/getTipoCamera")
    public ResponseEntity<TipoCameraDto> getTipoCamera(Integer id) {

	TipoCameraDto tipoCameraDto = new TipoCameraDto();
	try {
	    tipoCameraDto = service.getById(id);
	} catch (NoSuchElementException ex) {
	    logger.error(ex.getMessage(), ex);
	    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<TipoCameraDto>(tipoCameraDto, HttpStatus.OK);
    }

    @GetMapping("/getAllTipoCamera")
    public ResponseEntity<List<TipoCameraDto>> getAllTipoCamera() {

	List<TipoCameraDto> lista = new ArrayList<TipoCameraDto>();
	try {
	    lista = service.getAll();
	    if (lista.isEmpty()) {
		logger.info("getAllTipoCamera: Nessun elemento trovato");
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<List<TipoCameraDto>>(lista, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteTipoCamera")
    public ResponseEntity<TipoCameraDto> deleteTipoCamera(@RequestBody TipoCameraDto tipoCameraDto) {

	try {
	    service.deleteTipoCamera(tipoCameraDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
