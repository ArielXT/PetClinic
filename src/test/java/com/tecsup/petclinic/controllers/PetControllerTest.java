package com.tecsup.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dto.PetDTO;
/**
 * 
 */
@AutoConfigureMockMvc
@SpringBootTest
public class PetControllerTest {

	private static final Logger logger 
			= LoggerFactory.getLogger(PetControllerTest.class);

    private static final ObjectMapper om = new ObjectMapper();
    
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetPets() throws Exception {

		//int SIZE = 216;
		int ID_FIRST = 1;
		//int ID_LAST = 332;  

		this.mockMvc.perform(get("/pets"))
					.andExpect(status().isOk()) // HTTP 200
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
								    // ACTUAL      EXPECTED 
					//.andExpect(jsonPath("$", hasSize(SIZE)))
					.andExpect(jsonPath("$[0].id", is(ID_FIRST)));
					//.andExpect(jsonPath("$[212].id", is(ID_LAST)));
	}
	

	/**
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testFindPetOK() throws Exception {

		int ID_SEARCH = 1;
		String NAME_PET = "Leo";
		int TYPE_ID = 1;
		int OWNER_ID = 1;
		String DATE_REF = "2000-09-07";

		/*
		 {
		    "id": 1,
		    "name": "Leo",
		    "typeId": 1,
		    "ownerId": 1,
		    "birthDate": "2000-09-07"
		}
		 */
		
		mockMvc.perform(get("/pets/" + ID_SEARCH))  // Finding object with ID = 1
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(NAME_PET)))
				.andExpect(jsonPath("$.typeId", is(TYPE_ID)))
				.andExpect(jsonPath("$.ownerId", is(OWNER_ID)))
				.andExpect(jsonPath("$.birthDate", is(DATE_REF)));

	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindPetKO() throws Exception {

		int ID_SEARCH = 666;

		
		mockMvc.perform(get("/pets/" + ID_SEARCH)) // Finding object with ID = 666
				.andExpect(status().isNotFound());

	}
	
	/**
	 * @throws Exception
	 */
	
	@Test
    public void testCreatePet() throws Exception {
		
    	String NAME_PET = "BeethovenY";
		int TYPE_ID = 1;
		int OWNER_ID = 1;
		String DATE_REF = "2021-10-03";
		Date DATE = new SimpleDateFormat("yyyy-MM-dd").parse(DATE_REF);
		
		PetDTO newPet = new PetDTO(NAME_PET, TYPE_ID, OWNER_ID, DATE);
	    
		logger.info(newPet.toString());
		logger.info(om.writeValueAsString(newPet));
	    
	    mockMvc.perform(post("/pets")
	            .content(om.writeValueAsString(newPet))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.name", is(NAME_PET)))
	            .andExpect(jsonPath("$.typeId", is(TYPE_ID)))
	            .andExpect(jsonPath("$.ownerId", is(OWNER_ID)))
	    		.andExpect(jsonPath("$.birthDate", is(DATE_REF)));
    
	}
    

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDeletePet() throws Exception {

    	String NAME_PET = "Beethoven3";
		int TYPE_ID = 1;
		int OWNER_ID = 1;
		String DATE_REF = "2021-10-03";
		Date DATE = new SimpleDateFormat("yyyy-MM-dd").parse(DATE_REF);
		
		PetDTO newPet = new PetDTO(NAME_PET, TYPE_ID, OWNER_ID, DATE);
		
		ResultActions mvcActions = mockMvc.perform(post("/pets")
	            .content(om.writeValueAsString(newPet))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
	            
		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/pets/" + id ))
                 /*.andDo(print())*/
                .andExpect(status().isOk());
    }
    
}
    