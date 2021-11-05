package com.tecsup.petclinic.services;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public Owner update(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Long id) throws OwnerNotFoundException {
		Owner own = findById(id);
		ownerRepository.delete(own);		
	}

	@Override
	public Owner findById(long id) throws OwnerNotFoundException {
		Optional<Owner> own = ownerRepository.findById(id);
		if ( !own.isPresent())
			throw new OwnerNotFoundException("Record not found...!");			
		return own.get();
	}
	@Override
	public List<Owner> findByFirstname(String firstname) {
		List<Owner> owns = ownerRepository.findByFirstname(firstname);
		owns.stream().forEach(own -> logger.info("" + own));
		return owns;
	}
	@Override
	public List<Owner> findByLastname(String lastname) {
		List<Owner> owns = ownerRepository.findByLastname(lastname);
		owns.stream().forEach(own -> logger.info("" + own));
		return owns;
	}
	@Override
	public List<Owner> findByTelephone(String telephone) {
		List<Owner> owns = ownerRepository.findByTelephone(telephone);
		owns.stream().forEach(own -> logger.info("" + own));
		return owns;
	}
	@Override
	public Iterable<Owner> findAll() {
		return ownerRepository.findAll();
	}
}
