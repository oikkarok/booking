package booking.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.rest.dto.TipoCameraDto;
import booking.rest.entities.TipoCamera;
import booking.rest.repository.TipoCameraRepository;

@Service
public class TipoCameraService {
    

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TipoCameraRepository tipoCameraRepository;

    private static final Logger logger = LoggerFactory.getLogger(TipoCameraService.class);
    
    TipoCamera tipoCamera;

    public void mapAndSaveTipoCamera(TipoCameraDto tipoCameraDto) {
	logger.info("TipoCameraDto -> {}", tipoCameraDto.toString());
	TipoCamera tipoCamera = modelMapper.map(tipoCameraDto, TipoCamera.class);
	logger.info("TipoCamera -> {}", tipoCamera.toString());
	TipoCamera tipoCameraEntity = tipoCameraRepository.saveAndFlush(tipoCamera);
	logger.info("TipoCameraEntity -> {}", tipoCameraEntity.toString());
	this.tipoCamera = tipoCameraEntity;
    }

    public Integer getId() {
	return this.tipoCamera.getId();
    }

    public TipoCameraDto getById(int id) {

	Optional<TipoCamera> tipoCamera = tipoCameraRepository.findById(id);
	
	if (tipoCamera == null) {
	    return null;
	}
	logger.info("TipoCamera -> {}", tipoCamera.toString());
	return modelMapper.map(tipoCamera, TipoCameraDto.class);
    }

    public List<TipoCameraDto> getAll() {

	List<TipoCameraDto> list = new ArrayList<>();
	tipoCameraRepository.findAll().forEach(z -> list.add(modelMapper.map(z, TipoCameraDto.class)));
	
	return list;
    }
    
    public void deleteTipoCamera(TipoCameraDto tipoCameraDto) {	
	tipoCameraRepository.delete(modelMapper.map(tipoCameraDto, TipoCamera.class));
    }

}
