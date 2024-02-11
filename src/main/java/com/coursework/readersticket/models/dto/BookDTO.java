package com.coursework.readersticket.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
