package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {
    @Query(value = "select * from shows where movie_entity_id=:movieId and theater_entity_id=:theaterId",
    nativeQuery = true)
    List<ShowEntity> getshowDetails(int movieId, int theaterId);
}
