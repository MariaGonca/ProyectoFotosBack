package com.gestion.usuarios.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.usuarios.modelo.Image;

/**
 * The Interface ImageRepository.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {


	/**
	 * Find by tags.
	 *
	 * @param tags the tags
	 * @return the list
	 */
	List<Image> findByTags(String tags);

	
	/**
	 * Find by title.
	 *
	 * @param title the title
	 * @return the list
	 */
	List<Image> findByTitle(String title);

}
