package com.coursework.readersticket;

import com.coursework.readersticket.models.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerTest {
	@Autowired
	private TestRestTemplate template;

	@Test
	void canLoginAndGetProfile() {
		ResponseEntity<String> token = template
				.withBasicAuth("admin","password")
				.postForEntity("/api/v1/auth/login", null,String.class);
		assertNotNull(token.getBody());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token.getBody());
		ResponseEntity<UserDTO> profile = template
				.exchange("/api/v1/auth/profile", HttpMethod.GET,
						new HttpEntity<>(headers), UserDTO.class);
		assertTrue(profile.getStatusCode().is2xxSuccessful());
		assertNotNull(profile.getBody());
	}

}
