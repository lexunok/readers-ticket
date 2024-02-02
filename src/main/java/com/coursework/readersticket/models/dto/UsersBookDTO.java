package com.coursework.readersticket.models.dto;

import com.coursework.readersticket.models.entity.Book;
import com.coursework.readersticket.models.entity.User;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UsersBookDTO {
    private Long id;
    private Long userId;
    private Book book;
    private Boolean isRent;
    private LocalDate rentedAt;
    private LocalDate returnedAt;
}
