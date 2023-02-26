package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDtos.MovieEntryDto;
import com.example.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/create")
    public ResponseEntity createMovie(@RequestBody() MovieEntryDto movieEntryDto){
        try {
            String response=movieService.createMovie(movieEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String response="movie not able to create";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
