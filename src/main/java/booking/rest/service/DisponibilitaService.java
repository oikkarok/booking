package booking.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.rest.dto.DisponibilitaDto;
import booking.rest.entities.Disponibilita;
import booking.rest.repository.DisponibilitaRepository;

@Service
public class DisponibilitaService {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DisponibilitaRepository disponibilitaRepository;

    private static final Logger logger = LoggerFactory.getLogger(DisponibilitaService.class);
    
    Disponibilita disponibilita;

    public void mapAndSaveDisponibilita(DisponibilitaDto disponibilitaDto) {
	logger.info("DisponibilitaDto -> {}", disponibilitaDto.toString());
	Disponibilita disponibilita = modelMapper.map(disponibilitaDto, Disponibilita.class);
	logger.info("Disponibilita -> {}", disponibilita.toString());
	Disponibilita disponibilitaEntity = disponibilitaRepository.saveAndFlush(disponibilita);
	logger.info("DisponibilitaEntity -> {}", disponibilitaEntity.toString());
	this.disponibilita = disponibilitaEntity;
    }

    public Integer getId() {
	return this.disponibilita.getId();
    }

    public DisponibilitaDto getById(int id) {

	Optional<Disponibilita> disponibilita = disponibilitaRepository.findById(id);
	
	if (disponibilita == null) {
	    return null;
	}
	logger.info("Disponibilita -> {}", disponibilita.toString());
	return modelMapper.map(disponibilita, DisponibilitaDto.class);
    }

    public List<DisponibilitaDto> getAll() {

	List<DisponibilitaDto> list = new ArrayList<>();
	disponibilitaRepository.findAll().forEach(z -> list.add(modelMapper.map(z, DisponibilitaDto.class)));
	
	return list;
    }
    
    public void deleteDisponibilita(DisponibilitaDto disponibilitaDto) {	
	disponibilitaRepository.delete(modelMapper.map(disponibilitaDto, Disponibilita.class));
    }
}
