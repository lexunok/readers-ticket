package com.coursework.readersticket.services;

import com.coursework.readersticket.models.dto.BookDTO;
import com.coursework.readersticket.models.dto.UsersBookDTO;
import com.coursework.readersticket.models.entity.Book;
import com.coursework.readersticket.models.entity.User;
import com.coursework.readersticket.models.entity.UsersBook;
import com.coursework.readersticket.repositories.BookRepository;
import com.coursework.readersticket.repositories.UserRepository;
import com.coursework.readersticket.repositories.UsersBookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UsersBookRepository usersBookRepository;
    private final ModelMapper mapper = new ModelMapper();

    public BookDTO saveBook(BookDTO requestBookDTO) {
        Book book = mapper.map(requestBookDTO, Book.class);
        book = bookRepository.save(book);
        return mapper.map(book, BookDTO.class);
    }
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> mapper.map(book, BookDTO.class)).toList();
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<UsersBookDTO> getAllUsersBook(String username) {
        return usersBookRepository.findAllByUserUsername(username).stream()
                .map(ub -> {
                    UsersBookDTO usersBookDTO = mapper.map(ub, UsersBookDTO.class);
                    usersBookDTO.setUserId(ub.getUser().getId());
                    return usersBookDTO;
                }).toList();
    }

    public void rentBook(String username, Long bookId) {
        UsersBook usersBook = new UsersBook();
        Book book = new Book();
        book.setId(bookId);
        usersBook.setBook(book);
        User user = userRepository.findByUsername(username);
        usersBook.setUser(user);
        usersBookRepository.save(usersBook);
    }

    public void returnBook(Long usersBookId) {
        UsersBook usersBook = usersBookRepository.findById(usersBookId).get();
        usersBook.setReturnedAt(LocalDate.now());
        usersBook.setIsRent(false);
        usersBookRepository.save(usersBook);
    }

}
