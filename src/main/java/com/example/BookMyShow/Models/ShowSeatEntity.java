package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.Genre;
import com.example.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "showseats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean isBooked;
    private String seatNo;
    private int price;//seat price for perticular classic/gold/premium
    @Enumerated(value =EnumType.STRING)
    private SeatType seatType;
    private Date bookedAt;
    //this is child wrt showEntity
    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
