package com.example.notion_ex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // name of the book
    private String author; //author of the book
    private String type; // type of book
    private String status; //currently read, read or not read
    private int score; //personal rating of the book, on a scale from 1 to 5
    private LocalDate dateOfCompletion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    //to be solved
    /*@Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return null;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return null;
    }*/
}
