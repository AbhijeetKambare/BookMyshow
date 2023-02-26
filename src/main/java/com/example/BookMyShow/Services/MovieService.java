package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converts.MovieConverter;
import com.example.BookMyShow.EntryDtos.MovieEntryDto;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Repositories.MovieRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRpository movieRpository;
    public String createMovie(MovieEntryDto movieEntryDto)throws Exception{
        MovieEntity movieEntity= MovieConverter.convertEntryToMovie(movieEntryDto);
        movieRpository.save(movieEntity);
        return "Movie added Successfully";
    }
}
