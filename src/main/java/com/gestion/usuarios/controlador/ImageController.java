package com.gestion.usuarios.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.usuarios.modelo.Image;
import com.gestion.usuarios.servicios.ImageManagementServiceImpl;

/**
 * The Class ImageController.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ImageController {

	/** The image service. */
	@Autowired
	private ImageManagementServiceImpl imageService;

	/**
	 * Gets the all images.
	 *
	 * @param title the title
	 * @return the all images
	 */
	@GetMapping("/images")
	public ResponseEntity<List<Image>> printAllImages(@RequestParam(required = false) String title) {
		try {
			List<Image> images = new ArrayList<Image>();

			if (title == null)
				imageService.searchAll().forEach(images::add);
			else
				imageService.searchByTitle(title).forEach(images::add);

			if (images.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(images, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/imagenes")
	public List<Image> findAll() {
		return imageService.searchAll();
	}

	@GetMapping("/images/find/{title}")
	public List<Image> getImagesByTitle(@PathVariable String title) {
		List<Image> images = imageService.searchByTitle(title);
		if (images == null) {
			throw new RuntimeException("Image not found " + title);
		}
		return images;
	}
	@GetMapping("/images/find/{title}")
	public List<Image> getImagesByTagAnimales(@PathVariable String tags) {
		tags = "animales";
		List<Image> images = imageService.searchByTags(tags);
		if (images == null) {
			throw new RuntimeException("Image not found " + tags);
		}
		return images;
	}
	@GetMapping("/images/find/{title}")
	public List<Image> getImagesByTagFlores(@PathVariable String tags) {
		tags = "flores";
		List<Image> images = imageService.searchByTags(tags);
		if (images == null) {
			throw new RuntimeException("Image not found " + tags);
		}
		return images;
	}
	@GetMapping("/images/find/{title}")
	public List<Image> getImagesByTagMusica(@PathVariable String tags) {
		tags = "musica";
		List<Image> images = imageService.searchByTags(tags);
		if (images == null) {
			throw new RuntimeException("Image not found " + tags);
		}
		return images;
	}

	@PostMapping("/images/add")
	public void addClient(@RequestBody Image image) {
		imageService.insertNewImage(image);
	}

	// Cambiar a get para Angular
	@DeleteMapping("/images/delete/{idImage}")
	public void deleteImage(@PathVariable Long idImage) {
		Image deletedImage = imageService.searchByIdImage(idImage);
		if (deletedImage != null)
			imageService.deleteImage(deletedImage);

	}
}