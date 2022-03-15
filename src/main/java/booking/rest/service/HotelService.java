package booking.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.rest.dto.HotelDto;
import booking.rest.entities.Hotel;
import booking.rest.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    HotelRepository hotelRepository;

    Hotel hotel;

    public void mapAndSaveHotel(HotelDto hotelDto) {

	Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
	Hotel hotelEntity = hotelRepository.saveAndFlush(hotel);
	this.hotel = hotelEntity;
    }

    public Integer getId() {
	return this.hotel.getId();
    }
    
    public HotelDto getById(int id) {

	Optional<Hotel> hotel = hotelRepository.findById(id);

	if (hotel == null) {
	    return null;
	}

	return modelMapper.map(hotel.get(), HotelDto.class);
    }

    public List<HotelDto> getAll() {

	List<HotelDto> list = new ArrayList<>();
	hotelRepository.findAll().forEach(z -> list.add(modelMapper.map(z, HotelDto.class)));
	
	return list;
    }
    
    public void deleteHotel(HotelDto hotelDto) {	
	hotelRepository.delete(modelMapper.map(hotelDto, Hotel.class));
    }
}
