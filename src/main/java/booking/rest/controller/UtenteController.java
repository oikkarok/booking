package booking.rest.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import booking.rest.dto.UtenteDto;
import booking.rest.service.UtenteService;

@RestController
public class UtenteController {

    @Autowired
    UtenteService service;
    
    private static final Logger logger = LoggerFactory.getLogger(UtenteController.class);
    
    @GetMapping("/getUtente/{id}")
    public ResponseEntity<UtenteDto> getUtente(@PathVariable("id") Integer id) {

	UtenteDto utenteDto = new UtenteDto();
	try {
	    utenteDto = service.getById(id);
	} catch (NoSuchElementException ex) {
	    logger.error(ex.getMessage(), ex);
	    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<UtenteDto>(utenteDto, HttpStatus.OK);
    }
    
    @GetMapping("/getUtente/{nome}")
    public ResponseEntity<UtenteDto> getUtente(@PathVariable("nome") String nome) {

	UtenteDto utenteDto = new UtenteDto();
	try {
	    utenteDto = service.getByNome(nome);
	} catch (NoSuchElementException ex) {
	    logger.error(ex.getMessage(), ex);
	    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<UtenteDto>(utenteDto, HttpStatus.OK);
    }

}
