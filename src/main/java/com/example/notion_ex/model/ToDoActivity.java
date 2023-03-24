package com.example.notion_ex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoActivity /*extends People*/{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskName; // name of the task
    //@OneToMany
    @Transient
    private ArrayList<People> peopleAssigned; //people assigned to the task presented
    private String due; //date of the expense

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
