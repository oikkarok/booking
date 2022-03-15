package booking.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.rest.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

}
