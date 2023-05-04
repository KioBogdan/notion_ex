package com.example.notion_ex.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String task; // name of task
    private String status; //status of completion for the present task
    private LocalDate dateStart; //starting date of the project
    private LocalDate dateFinish; //ending date of the project
    private String projectName; //name of the project
    //@OneToMany
    //private ArrayList<People> peopleAssigned; //people assigned to the project

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
