package com.example.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private LocalTime showTime;
    private LocalDate showDate;
    private int totalAmount;
    private String ticketId= UUID.randomUUID().toString();
    private String theaterName;
    private String bookedSeats;
    //this is child wrt user entity
    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    //this is child wrt show
    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
