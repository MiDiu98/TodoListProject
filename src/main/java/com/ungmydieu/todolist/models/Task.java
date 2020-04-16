package com.ungmydieu.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @Column(columnDefinition = "NOT NULL")
    private String title;

    private String description;

    private Date startTime;

    private Date endTime;

    private boolean status;
}
