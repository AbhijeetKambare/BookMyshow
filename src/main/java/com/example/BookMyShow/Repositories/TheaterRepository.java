package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity,Integer> {
//    select distinct th.theater_name from theaters th,shows s,movies m where movie_entity_id=3
//    -> and th.id=s.theater_entity_id and s.movie_entity_id=m.id;
 //   logic for get theaters by searching movies name get vailable shows on all theaters
}
