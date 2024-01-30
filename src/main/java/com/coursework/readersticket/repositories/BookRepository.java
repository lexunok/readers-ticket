package com.coursework.readersticket.repositories;

import com.coursework.readersticket.models.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
