package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
