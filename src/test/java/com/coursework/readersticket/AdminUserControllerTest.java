package com.coursework.readersticket;

import com.coursework.readersticket.models.dto.UserDTO;
import com.coursework.readersticket.models.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AdminUserControllerTest {
	@Autowired
	private TestRestTemplate template;

	@Test
	void canRegisterGetDeleteUser() {
		ResponseEntity<String> token = template
				.withBasicAuth("admin","password")
				.postForEntity("/api/v1/auth/login", null,String.class);
		assertNotNull(token.getBody());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token.getBody());
		UserDTO user = UserDTO.builder()
				.role(User.Role.ADMIN)
				.username("testname")
				.password("testpassword")
				.build();
		template.exchange("/api/v1/admin/user/register", HttpMethod.POST,
				new HttpEntity<>(user, headers), UserDTO.class);
		ResponseEntity<List<UserDTO>> users = template.exchange("/api/v1/admin/user/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		UserDTO userInList = users.getBody().stream().filter(u -> u.getUsername().equals(user.getUsername())).findAny().get();
		assertNotNull(userInList);
		template.exchange("/api/v1/admin/user/delete/" + userInList.getId(), HttpMethod.DELETE,
				new HttpEntity<>(user, headers), UserDTO.class);
		users = template.exchange("/api/v1/admin/user/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		assertFalse(users.getBody().stream().anyMatch(u -> u.getUsername().equals(user.getUsername())));
	}

}
