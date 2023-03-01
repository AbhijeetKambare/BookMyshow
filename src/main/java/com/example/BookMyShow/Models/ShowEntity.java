package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.ShowType;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalTime showTime;
    private LocalDate showDate;
    @Enumerated(EnumType.STRING)
    private ShowType showType;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
    //this is child wrt movie entity
    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;
    //this is chlid wrt theater
    @ManyToOne
    @JoinColumn
    private TheaterEntity theaterEntity;
    //this is parent wrt tickets
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> ListOfBookedTickets=new ArrayList<>();
    //this is parent wrt showseatEnity
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> ListOfShowSeats=new ArrayList<>();
}
