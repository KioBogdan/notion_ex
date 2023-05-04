package com.example.notion_ex.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialActivityDTO {

    private Long id;
    private String expense; // name of the expense
    private int amount; //value of expense, based on Romanian leu
    private String category; //way to categorize the expenses
    private LocalDate date; //date of the expense
}
