package com.coursework.readersticket.repositories;

import com.coursework.readersticket.models.entity.UsersBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersBookRepository extends JpaRepository<UsersBook, Long> {
    List<UsersBook> findAllByUserUsername(String username);
}
