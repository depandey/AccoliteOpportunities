package com.accolite.opportunities;

import org.springframework.test.context.junit4.SpringRunner;

import com.accolite.opportunities.controllers.UserController;
import com.accolite.opportunities.entities.User;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpportunitiesApplicationIntegrationTests {

	@Autowired 
	private TestRestTemplate restTemplate;
	
	@LocalServerPort 
	private int port;
	
	@Autowired
	private UserController userController;
	
	@Test
    public void contextLoads() {
        Assertions
          .assertThat(userController)
          .isNotNull();
    }
	
	@Test
	@WithMockUser(username="user")
	public void testGetUser() {
		
		ResponseEntity<Iterable<User>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<User>>() {
        });
        Iterable<User> products = responseEntity.getBody();
        Assertions
          .assertThat(products)
          .hasSize(5);
	}
	
	@Test
	@WithMockUser(username="user")
	public void testPostUser() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> entity = new HttpEntity<>(new User("Test", "Test@domain.com"), headers);
		
		restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.POST, entity, new ParameterizedTypeReference<Iterable<User>>() {
        });
		
		ResponseEntity<Iterable<User>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<User>>() {
        });
        Iterable<User> products = responseEntity.getBody();
        Assertions
          .assertThat(products)
          .hasSize(6);
	}
}
