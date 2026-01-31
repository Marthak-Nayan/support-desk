package com.minisupportdesk.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Comment {

    private Long id;

    private String commentDesc;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "commByUser_Id")
    private User commentedBy;
}
