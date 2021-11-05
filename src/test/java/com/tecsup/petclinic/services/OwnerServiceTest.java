package com.tecsup.petclinic.services;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;

@SpringBootTest
public class OwnerServiceTest{
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);	
	@Autowired
	private OwnerService ownerService;
	
	/**
	 * 
	 */
	@Test
	public void testFindByFirstname() {
		String FIND_NAME = "George";
		int SIZE_EXPECTED = 1;
		List<Owner> own = ownerService.findByFirstname(FIND_NAME);

		assertThat(SIZE_EXPECTED, is(own.size()));
	}
	
	@Test
	public void testCreateOwner() {
        String FIRST_NAME="Marcos";
        String LAST_NAME="Castro";
        String ADDRESS="marcos@gmail.com";
        String CITY="Arizona";
        String TELEPHONE="968569586";
        
        Owner owner=new Owner( FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
        owner=ownerService.create(owner);
        logger.info(""+owner);
        assertThat(owner.getId(),notNullValue());
        assertThat(FIRST_NAME, is(owner.getFirstname()));
        assertThat(LAST_NAME, is(owner.getLastname()));
        assertThat(ADDRESS, is(owner.getAddress()));
        assertThat(CITY, is(owner.getCity()));
        assertThat(TELEPHONE, is(owner.getTelephone()));        
    }	
	@Test
	public void testUpdateOwner() {
        
        String FIRST_NAME="Marcos";
        String LAST_NAME="Castro";
        String ADDRESS="marcos@gmail.com";
        String CITY="Arizona";
        String TELEPHONE="968569586";
        long create_id = -1;
        
        String UP_FIRST_NAME="Marcos";
        String UP_LAST_NAME="Lambada";
        String UP_ADDRESS="mario@gmail.com";
        String UP_CITY="Tenesis";
        String UP_TELEPHONE="966632365";
        
        Owner owner=new Owner( FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
        // Create record
        logger.info(">" + owner);
        Owner readOwner = ownerService.create(owner);
        logger.info(">>" + readOwner);

        create_id = readOwner.getId();

        // Prepare data for update
        readOwner.setFirstname(UP_FIRST_NAME);
        readOwner.setLastname(UP_LAST_NAME);
        readOwner.setAddress(UP_ADDRESS);
        readOwner.setCity(UP_CITY);
        readOwner.setTelephone(UP_TELEPHONE);

        // Execute update
        Owner upgradeOwner = ownerService.update(readOwner);
        logger.info(">>>>" + upgradeOwner);

        assertThat(create_id ,notNullValue());
        assertThat(create_id, is(upgradeOwner.getId()));
        assertThat(UP_FIRST_NAME, is(upgradeOwner.getFirstname()));
        assertThat(UP_LAST_NAME, is(upgradeOwner.getLastname()));
        assertThat(UP_ADDRESS, is(upgradeOwner.getAddress()));
        assertThat(UP_CITY, is(upgradeOwner.getCity()));
        assertThat(UP_TELEPHONE, is(upgradeOwner.getTelephone()));      	
    }
	@Test
	public void testDeleteOwner() {
        String FIRST_NAME="Jeff";
        String LAST_NAME="Black";
        String ADDRESS="1450 Oak Blvd.";
        String CITY="Monona";
        String TELEPHONE="6085555387'";
        Owner owner=new Owner( FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
        owner=ownerService.create(owner);
        logger.info(""+owner);
        try {
            ownerService.delete(owner.getId());
        }catch (OwnerNotFoundException e) {
            assertThat(true, is(true));
        }           
    }
}
