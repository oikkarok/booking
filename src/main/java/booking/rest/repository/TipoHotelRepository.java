package booking.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.rest.entities.TipoHotel;

public interface TipoHotelRepository extends JpaRepository<TipoHotel, Integer> {

}
