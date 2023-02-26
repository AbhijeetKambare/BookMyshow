package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDtos.TheaterEntryDto;
import com.example.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @PostMapping
    public ResponseEntity createTheater(@RequestBody()TheaterEntryDto theaterEntryDto){
        try{
            String response=theaterService.createThetaer(theaterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String response="unable to create theater";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
