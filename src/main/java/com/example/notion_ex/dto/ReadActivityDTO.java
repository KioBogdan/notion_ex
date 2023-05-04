package com.example.notion_ex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReadActivityDTO {
    private Long id;

    private String name; // name of the book
    private String author; //author of the book
    private String type; // type of book
    private String status; //currently read, read or not read
    private int score; //personal rating of the book, on a scale from 1 to 5
    private LocalDate dateOfCompletion;
}
