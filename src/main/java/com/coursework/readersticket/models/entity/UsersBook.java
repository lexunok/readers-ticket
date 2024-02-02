package com.coursework.readersticket.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UsersBook {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    private Boolean isRent = true;
    private LocalDate rentedAt = LocalDate.now();
    private LocalDate returnedAt;
}