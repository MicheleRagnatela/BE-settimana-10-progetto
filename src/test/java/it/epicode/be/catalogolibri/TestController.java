package it.epicode.be.catalogolibri;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.epicode.be.catalogolibri.model.Autore;


@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void inserimentoLibro() throws Exception {
		this.mockMvc.perform(post("/api/save/libro")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void listaLibriWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/findall/libri")).andExpect(status().isOk());
	}



	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void aggiungi() throws Exception {

		Autore autore = new Autore();
		autore.setNome("Loriano");
		autore.setCognome("Macchiavelli");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(autore);
		MvcResult result = mockMvc
				.perform(post("/api/save/autore").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(content().json("{'nome':'Loriano'}"))
				.andExpect(content().json("{'cognome':'Macchiavelli'}")).andReturn();
	}
	
	
}
