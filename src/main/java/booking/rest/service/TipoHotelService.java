package booking.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.rest.dto.TipoHotelDto;
import booking.rest.entities.TipoHotel;
import booking.rest.repository.TipoHotelRepository;

@Service
public class TipoHotelService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TipoHotelRepository tipoHotelRepository;

    private static final Logger logger = LoggerFactory.getLogger(TipoHotelService.class);
    
    TipoHotel tipoHotel;

    public void mapAndSaveTipoHotel(TipoHotelDto tipoHotelDto) {
	logger.info("TipoHotelDto -> {}", tipoHotelDto.toString());
	TipoHotel tipoHotel = modelMapper.map(tipoHotelDto, TipoHotel.class);
	logger.info("TipoHotel -> {}", tipoHotel.toString());
	TipoHotel tipoHotelEntity = tipoHotelRepository.saveAndFlush(tipoHotel);
	logger.info("TipoHotelEntity -> {}", tipoHotelEntity.toString());
	this.tipoHotel = tipoHotelEntity;
    }

    public Integer getId() {
	return this.tipoHotel.getId();
    }

    public TipoHotelDto getById(int id) {

	Optional<TipoHotel> tipoHotel = tipoHotelRepository.findById(id);

	if (tipoHotel == null) {
	    return null;
	}

	return modelMapper.map(tipoHotel.get(), TipoHotelDto.class);
    }

    public List<TipoHotelDto> getAll() {

	List<TipoHotelDto> list = new ArrayList<>();
	tipoHotelRepository.findAll().forEach(z -> list.add(modelMapper.map(z, TipoHotelDto.class)));
	
	return list;
    }
    
    public void deleteTipoHotel(TipoHotelDto tipoHotelDto) {	
	tipoHotelRepository.delete(modelMapper.map(tipoHotelDto, TipoHotel.class));
    }
}
