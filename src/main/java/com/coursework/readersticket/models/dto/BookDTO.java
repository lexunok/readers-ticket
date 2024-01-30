package com.coursework.readersticket.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private Long id;
    private String name;
    private String description;
    private String publisher;
    private String yearOfPublishing;
    private List<String> genres;
    private List<String> authors;
    private String isbn;
    private Integer countOfPages;

}
