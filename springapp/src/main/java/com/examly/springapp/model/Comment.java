package com.examly.springapp.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    private Long commentId;

    private String content;

    private Date createdDate;

    @ManyToOne
    private Ticket ticket;
}
