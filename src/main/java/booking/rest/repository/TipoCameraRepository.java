package booking.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.rest.entities.TipoCamera;

public interface TipoCameraRepository extends JpaRepository<TipoCamera, Integer>{

}
