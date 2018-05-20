package com.company.enroller.controllers;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.company.enroller.model.Participant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MyApp.class)
@WebIntegrationTest
public class ParticipantRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;


	@Test
	public void getParticipants() throws Exception {
		Participant participant = new Participant();
		participant.setLogin("testlogin");
		participant.setPassword("testpassword");

		Collection<Participant> allParticipants = singletonList(participant);
	
		mvc.perform(get("/participants").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].login", is(participant.getLogin())));
	}
	
	@Test
	public void getParticipant() throws Exception {
		Participant participant = new Participant();
		participant.setLogin("testlogin");
		participant.setPassword("testpassword");

	
		mvc.perform(get("/participants/"+participant.getLogin()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				//.andExpect(content().string(new ObjectMapper().writeValueAsString(participant)));
		//.andExpect(content().string("{\"login\":\"testlogin\",\"password\":\"testpassword\"}"));
		.andExpect(jsonPath("login", is("testlogin")));
	}
	
	@Test
	public void addParticipant() throws Exception {
		Participant participant = new Participant();
		participant.setLogin("testlogin");
		participant.setPassword("testpassword");
		String inputJSON = "{\"login\":\"testlogin\", \"password\":\"somepassword\"}";

		
	}

}
