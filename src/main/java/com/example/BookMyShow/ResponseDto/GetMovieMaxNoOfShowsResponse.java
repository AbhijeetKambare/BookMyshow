package com.example.BookMyShow.ResponseDto;

import com.example.BookMyShow.Models.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetMovieMaxNoOfShowsResponse {
    private MovieEntity movie;
    private String movieName;

}
