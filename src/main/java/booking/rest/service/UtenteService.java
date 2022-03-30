package booking.rest.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.rest.dto.UtenteDto;
import booking.rest.entities.Utente;
import booking.rest.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UtenteRepository utenteRepository;

    private static final Logger logger = LoggerFactory.getLogger(UtenteService.class);
    
    Utente utente;
    
    public void mapAndSaveUtente(UtenteDto utenteDto) {
	
	logger.info("UtenteDto -> {}", utenteDto.toString());
	Utente utente = modelMapper.map(utenteDto, Utente.class);
	logger.info("Utente -> {}", utente.toString());
	Utente utenteEntity = utenteRepository.saveAndFlush(utente);
	logger.info("UtenteEntity -> {}", utenteEntity.toString());
	this.utente = utenteEntity;
    }

    
    public UtenteDto getById(int id) {

	Optional<Utente> utente = utenteRepository.findById(id);

	if (utente == null) {
	    return null;
	}

	return modelMapper.map(utente.get(), UtenteDto.class);
    }
    
    public UtenteDto getByNome(String nome) {

	Optional<Utente> utente = utenteRepository.findByNome(nome);

	if (utente == null) {
	    return null;
	}

	return modelMapper.map(utente.get(), UtenteDto.class);
    }
    
}
