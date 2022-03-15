package booking.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.rest.entities.Camera;

public interface CameraRepository extends JpaRepository<Camera, Integer>{

}
