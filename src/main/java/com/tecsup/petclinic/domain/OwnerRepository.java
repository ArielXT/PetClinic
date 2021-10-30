package com.tecsup.petclinic.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository 
	extends CrudRepository<Owner, Long> {
		// Fetch owners by First name
		List<Owner> findByFirstname(String firstname);

		// Fetch owners by Last name
		List<Owner> findByLastname(String lastname);

		// Fetch owners by Telephone
		List<Owner> findByTelephone(String telephone);

}
