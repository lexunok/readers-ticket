package com.coursework.readersticket;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.models.dto.UserDTO;
import com.coursework.readersticket.models.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AdminBookControllerTest {
	@Autowired
	private TestRestTemplate template;

	@Test
	void canEditBooks() {
		ResponseEntity<String> token = template
				.withBasicAuth("admin","password")
				.postForEntity("/api/v1/auth/login", null,String.class);
		assertNotNull(token.getBody());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token.getBody());

		BookDTO book = BookDTO.builder().name("test").description("description").build();
		template.exchange("/api/v1/admin/book/add", HttpMethod.POST,
				new HttpEntity<>(book, headers), BookDTO.class);

		ResponseEntity<List<BookDTO>> allBooks = template.exchange("/api/v1/book/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		BookDTO bookInList = allBooks.getBody().stream()
				.filter(u -> u.getName().equals(book.getName())).findAny().get();
		assertNotNull(bookInList);
		Long id = bookInList.getId();

		BookDTO updatedBook = BookDTO.builder().name("updated").description("description").build();
		template.exchange("/api/v1/admin/book/update/" + id, HttpMethod.PUT,
				new HttpEntity<>(updatedBook, headers), BookDTO.class);

		allBooks = template.exchange("/api/v1/book/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		BookDTO updatedBookInList = allBooks.getBody().stream()
				.filter(u -> u.getId().equals(id)).findAny().get();
		assertNotNull(bookInList);
        assertEquals(updatedBookInList.getName(), updatedBook.getName());

		template.exchange("/api/v1/admin/book/delete/" + id, HttpMethod.DELETE,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});

		allBooks = template.exchange("/api/v1/book/all", HttpMethod.GET,
				new HttpEntity<>(headers), new ParameterizedTypeReference<>(){});
		assertFalse(allBooks.getBody().stream().anyMatch(u -> u.getId().equals(id)));
	}

}
