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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpportunitiesApplicationTestsIT {

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
	public void testGetUser() {
		
		ResponseEntity<Iterable<User>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<User>>() {
        });
        Iterable<User> products = responseEntity.getBody();
        Assertions
          .assertThat(products)
          .hasSize(5);
	}
	
	@Test
	public void testPostUser() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> entity = new HttpEntity<>(new User("Tests", "Test@domain.com"), headers);
		
		restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.POST, entity, new ParameterizedTypeReference<User>() {
        });
		
		ResponseEntity<Iterable<User>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<User>>() {
        });
        Iterable<User> products = responseEntity.getBody();
        Assertions
          .assertThat(products)
          .hasSize(6);
	}
	
	@Test
	public void testPostUserShouldFailForInvalidEmail() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> entity = new HttpEntity<>(new User("Tests", "Testdomain.com"), headers);

		Assertions.assertThat(restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.POST, entity, new ParameterizedTypeReference<User>() {
	       }).getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
