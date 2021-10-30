package com.tecsup.petclinic.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author jgomezm
 *
 */
@Repository
public interface PetRepository 
	extends CrudRepository<Pet, Long> {

	// Fetch pets by name
	List<Pet> findByName(String name);

	// Fetch pets by typeId
	List<Pet> findByTypeId(int typeId);

	// Fetch pets by ownerId
	List<Pet> findByOwnerId(int ownerId);

}
