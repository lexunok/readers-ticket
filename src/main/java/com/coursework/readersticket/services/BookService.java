package com.coursework.readersticket.services;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.models.entity.Book;
import com.coursework.readersticket.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public BookDTO saveBook(BookDTO requestBookDTO) {
        Book book = mapper.map(requestBookDTO, Book.class);
        book = repository.save(book);
        return mapper.map(book, BookDTO.class);
    }
    public List<BookDTO> getAllBooks() {
        return repository.findAll().stream()
                .map(book -> mapper.map(book, BookDTO.class)).toList();
    }
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

}
