package com.coursework.readersticket;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.models.dto.UserDTO;
import com.coursework.readersticket.models.dto.UsersBookDTO;
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
class BookControllerTest {
	@Autowired
	private TestRestTemplate template;

	@Test
	void canRentAndReturnBook() {
		ResponseEntity<String> token = template
				.withBasicAuth("admin","password")
				.postForEntity("/api/v1/auth/login", null,String.class);
		assertNotNull(token.getBody());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token.getBody());

		BookDTO book = BookDTO.builder().name("book").description("description").build();
		template.exchange("/api/v1/admin/book/add", HttpMethod.POST,
				new HttpEntity<>(book, headers), BookDTO.class);

		ResponseEntity<List<BookDTO>> allBooks = template.exchange("/api/v1/book/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		BookDTO bookInList = allBooks.getBody().stream()
				.filter(u -> u.getName().equals(book.getName())).findAny().get();
		assertNotNull(bookInList);

		template.exchange("/api/v1/book/rent/" + bookInList.getId(), HttpMethod.POST,
				new HttpEntity<>(headers), BookDTO.class);

		ResponseEntity<List<UsersBookDTO>> allUsersBook = template.exchange("/api/v1/book/user/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		UsersBookDTO bookInUserList = allUsersBook.getBody().stream()
				.filter(u -> u.getBook().getId().equals(bookInList.getId())).findAny().get();
		assertNotNull(bookInUserList);
		assertTrue(bookInUserList.getIsRent());
		assertNull(bookInUserList.getReturnedAt());
		assertNotNull(bookInUserList.getRentedAt());

		template.exchange("/api/v1/book/return/" + bookInUserList.getId(), HttpMethod.PUT,
				new HttpEntity<>(headers), UsersBookDTO.class);

		allUsersBook = template.exchange("/api/v1/book/user/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		bookInUserList = allUsersBook.getBody().stream()
				.filter(u -> u.getBook().getId().equals(bookInList.getId())).findAny().get();
		assertNotNull(bookInUserList);
		assertFalse(bookInUserList.getIsRent());
		assertNotNull(bookInUserList.getReturnedAt());

	}

}
