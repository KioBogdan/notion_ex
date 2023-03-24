package com.example.notion_ex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialActivity /*extends DateFormat*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String expense; // name of the expense
    private int amount; //value of expense, based on Romanian leu
    private String category; //way to categorize the expenses
    private String date; //date of the expense

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
