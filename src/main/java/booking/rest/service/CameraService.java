package booking.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.rest.dto.CameraDto;
import booking.rest.entities.Camera;
import booking.rest.repository.CameraRepository;

@Service
public class CameraService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CameraRepository cameraRepository;

    private static final Logger logger = LoggerFactory.getLogger(CameraService.class);

    Camera camera;

    public void mapAndSaveCamera(CameraDto cameraDto) {
	logger.info("CameraDto -> {}", cameraDto.toString());
	Camera camera = modelMapper.map(cameraDto, Camera.class);
	logger.info("Camera -> {}", camera.toString());
	Camera cameraEntity = cameraRepository.saveAndFlush(camera);
	logger.info("CameraEntity -> {}", cameraEntity.toString());
	this.camera = cameraEntity;
    }

    public Integer getId() {
	return this.camera.getId();
    }

    public CameraDto getById(int id) {

	Optional<Camera> camera = cameraRepository.findById(id);

	if (camera == null) {
	    return null;
	}
	logger.info("Camera -> {}", camera.toString());
	return modelMapper.map(camera, CameraDto.class);
    }

    public List<CameraDto> getAll() {

	List<CameraDto> list = new ArrayList<>();
	cameraRepository.findAll().forEach(z -> list.add(modelMapper.map(z, CameraDto.class)));

	return list;
    }

    public void deleteCamera(CameraDto cameraDto) {
	cameraRepository.delete(modelMapper.map(cameraDto, Camera.class));
    }
}
