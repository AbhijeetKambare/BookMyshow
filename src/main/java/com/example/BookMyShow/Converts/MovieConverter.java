package com.example.BookMyShow.Converts;

import com.example.BookMyShow.EntryDtos.MovieEntryDto;
import com.example.BookMyShow.Models.MovieEntity;

public class MovieConverter {
    public static MovieEntity convertEntryToMovie(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity = MovieEntity.builder()
                .movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage()).rating(movieEntryDto.getRating()).build();

        return movieEntity;
    }
}
