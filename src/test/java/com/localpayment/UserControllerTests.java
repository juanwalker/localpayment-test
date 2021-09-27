package com.localpayment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.localpayment.application.actionsResponses.CardResponse;
import com.localpayment.application.actionsResponses.LoginResponse;
import com.localpayment.application.actionsResponses.PersonResponse;
import com.localpayment.application.actionsResponses.UserResponse;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTests {

	@Autowired
	private MockMvc mvc;

	private static String token;

	@Order(1)
	@Test void failedLogin() throws Exception {


		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/user/login?username=user1&password=aaaaaaa")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();


		ObjectMapper objectMapper = new ObjectMapper();
		LoginResponse response = objectMapper.readValue(responseStr, LoginResponse.class);
		assertEquals(response.getToken(),"Not a valid user and/or password!");

	}


	@Order(2)
	@Test void createPerson() throws Exception {
		String body = "{\"name\" :\"pepito\",\"surname\" :\"perez\",\"address\" :\"Calle real #456 Lima Peru\",\"idNumber\" : \"1234\"}";

		//String body = "{\"username\": \"" + username + "\", \"password\":\"" + password + "\"}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/person/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();


		ObjectMapper objectMapper = new ObjectMapper();
		PersonResponse response = objectMapper.readValue(responseStr, PersonResponse.class);
		assertEquals(response.getStatus(),"OK") ;
		assertNotNull(response.getPerson().getId());

		/*
		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"access_token\": \"", "");
		String token = response.replace("\"}", "");
		System.out.println(token);


		mvc.perform(MockMvcRequestBuilders.get("/test")
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());*/

	}

	@Order(3)
	@Test void createUser() throws Exception {

		String body =  "{\"name\" : \"user1\",	\"personId\" : \"1\",\"password\" : \"aaaaaaa\"	}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();


		ObjectMapper objectMapper = new ObjectMapper();
		UserResponse response = objectMapper.readValue(responseStr, UserResponse.class);
		assertEquals(response.getStatus(),"OK") ;
		assertNotNull(response.getUser().getId());

	}

	@Order(4)
	@Test void login() throws Exception {


		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/user/login?username=user1&password=aaaaaaa")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();


		ObjectMapper objectMapper = new ObjectMapper();
		LoginResponse response = objectMapper.readValue(responseStr, LoginResponse.class);
		assertEquals(response.getUserName(),"user1") ;
		assertNotNull(response.getToken());
		this.token = response.getToken();
	}

	@Order(5)
	@Test void getPerson() throws Exception {

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/person/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		PersonResponse response = objectMapper.readValue(responseStr, PersonResponse.class);
		assertEquals(response.getStatus(),"OK") ;
		assertNotNull(response.getPerson().getId());
	}

	@Order(6)
	@Test void getUser() throws Exception {

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/user/2")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserResponse response = objectMapper.readValue(responseStr, UserResponse.class);
		assertEquals(response.getStatus(),"OK") ;
		assertNotNull(response.getUser().getId());
	}

	@Order(7)
	@Test void createCard1() throws Exception {

		String  body = "{\"name\" : \"SQUA\",\"number\" : \"123456\",\"holder\" : \"pepito\",\"personId\" : \"1\",\"limit\" : \"2000\",\"expireDate\" : \"2020-06-26\"}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/card/")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token)
				.content(body))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		CardResponse response = objectMapper.readValue(responseStr, CardResponse.class);
		assertEquals(response.getStatus(),"OK") ;
		assertThat(response.getCards(), IsCollectionWithSize.hasSize(1));
		assertNotNull(response.getCards().get(0).getId());
	}

	@Order(8)
	@Test void validPayment() throws Exception {

		String body = "{\"id\" : \"3\",	\"value\" : \"2000\"}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/card/valid")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token)
				.content(body))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		CardResponse response = objectMapper.readValue(responseStr, CardResponse.class);
		assertEquals(response.getStatus(),"OK") ;

	}

	@Order(9)
	@Test void invalidPayment() throws Exception {

		String body = "{\"id\" : \"3\",	\"value\" : \"2001\"}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/card/valid")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token)
				.content(body))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		CardResponse response = objectMapper.readValue(responseStr, CardResponse.class);
		assertEquals(response.getStatus(),"KO") ;

	}

	@Order(10)
	@Test void createCard2() throws Exception {

		String  body = "{\"name\" : \"SQUA\",\"number\" : \"123456\",\"holder\" : \"pepito\",\"personId\" : \"1\",\"limit\" : \"2000\",\"expireDate\" : \"2020-06-26\"}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/card/")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token)
				.content(body))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		CardResponse response = objectMapper.readValue(responseStr, CardResponse.class);
		assertEquals(response.getStatus(),"OK") ;
		assertThat(response.getCards(), IsCollectionWithSize.hasSize(1));
		assertNotNull(response.getCards().get(0).getId());
	}

	@Order(11)
	@Test void compareCards() throws Exception {


		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/card/compare/4/3")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		CardResponse response = objectMapper.readValue(responseStr, CardResponse.class);
		assertEquals(response.getStatus(),"OK") ;
	}

	@Order(12)
	@Test void getAllCards() throws Exception {


		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/card/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  this.token))
				.andExpect(status().isOk()).andReturn();
		String responseStr = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		CardResponse response = objectMapper.readValue(responseStr, CardResponse.class);
		assertThat(response.getCards(), IsCollectionWithSize.hasSize(1));
		assertEquals(response.getStatus(),"OK") ;
	}
}
