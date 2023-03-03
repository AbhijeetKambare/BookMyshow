package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.RequestDtos.getspecificShowsDto;
import com.example.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/create")
    public ResponseEntity createShow(@RequestBody() ShowEntryDto showEntryDto){
        try {
            String response=showService.addShow(showEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String response="unable to create show";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("getShows")
    public ResponseEntity getShows(@RequestBody() getspecificShowsDto getspecificShows){
        try {
            String response= showService.getListOFshows(getspecificShows);
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }catch (Exception e){
            String string="no shows is present";
            return new ResponseEntity<>(string,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/GetmaxShowsMovie")
    public ResponseEntity getMovieName(){
        try{
            String response=showService.getmaxnoofShowsinallTheater();
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("Not showing record",HttpStatus.NOT_FOUND);
        }
    }
}
