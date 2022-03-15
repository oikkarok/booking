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

import booking.rest.dto.DisponibilitaDto;
import booking.rest.service.DisponibilitaService;

@RestController
public class DisponibilitaController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DisponibilitaService service;
    
    private static final Logger logger = LoggerFactory.getLogger(DisponibilitaController.class);

    @PostMapping("/insertDisponibilita")
    public ResponseEntity<Integer> insertDisponibilita(@RequestBody DisponibilitaDto disponibilitaDto) {

	try {
	    service.mapAndSaveDisponibilita(disponibilitaDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @PutMapping("/editDisponibilita")
    public ResponseEntity<Integer> editDisponibilita(@RequestBody DisponibilitaDto disponibilitaDto) {

	try {
	    service.mapAndSaveDisponibilita(disponibilitaDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @GetMapping("/getDisponibilita")
    public ResponseEntity<DisponibilitaDto> getDisponibilita(Integer id) {

	DisponibilitaDto disponibilitaDto = new DisponibilitaDto();
	try {
	    disponibilitaDto = service.getById(id);
	} catch (NoSuchElementException ex) {
	    logger.error(ex.getMessage(), ex);
	    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<DisponibilitaDto>(disponibilitaDto, HttpStatus.OK);
    }

    @GetMapping("/getAllDisponibilita")
    public ResponseEntity<List<DisponibilitaDto>> getAllDisponibilita() {

	List<DisponibilitaDto> lista = new ArrayList<DisponibilitaDto>();
	try {
	    lista = service.getAll();
	    if (lista.isEmpty()) {
		logger.info("getAllDisponibilita: Nessun elemento trovato");
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<List<DisponibilitaDto>>(lista, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteDisponibilita")
    public ResponseEntity<DisponibilitaDto> deleteDisponibilita(@RequestBody DisponibilitaDto disponibilitaDto) {

	try {
	    service.deleteDisponibilita(disponibilitaDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
