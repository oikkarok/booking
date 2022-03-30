package booking.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import booking.rest.dto.CameraDto;
import booking.rest.service.CameraService;

@Controller
public class CameraController {

    @Autowired
    CameraService service;

    private static final Logger logger = LoggerFactory.getLogger(CameraController.class);

    @PostMapping("/insertCamera")
    public ResponseEntity<Integer> insertCamera(@RequestBody CameraDto cameraDto) {

	try {
	    service.mapAndSaveCamera(cameraDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @PutMapping("/editCamera")
    public ResponseEntity<Integer> editCamera(@RequestBody CameraDto cameraDto) {

	try {
	    service.mapAndSaveCamera(cameraDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Integer>(service.getId(), HttpStatus.OK);
    }

    @GetMapping("/getCamera")
    public ResponseEntity<CameraDto> getCamera(Integer id) {

	CameraDto cameraDto = new CameraDto();
	try {
	    cameraDto = service.getById(id);
	} catch (NoSuchElementException ex) {
	    logger.error(ex.getMessage(), ex);
	    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<CameraDto>(cameraDto, HttpStatus.OK);
    }

    @GetMapping("/getAllCamera")
    public ResponseEntity<List<CameraDto>> getAllCamera() {

	List<CameraDto> lista = new ArrayList<CameraDto>();
	try {
	    lista = service.getAll();
	    if (lista.isEmpty()) {
		logger.info("getAllCamera: Nessun elemento trovato");
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<List<CameraDto>>(lista, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCamera")
    public ResponseEntity<CameraDto> deleteCamera(@RequestBody CameraDto cameraDto) {

	try {
	    service.deleteCamera(cameraDto);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
}
