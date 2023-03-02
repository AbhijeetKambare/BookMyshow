package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickitRepository extends JpaRepository<TicketEntity,Integer> {
}
