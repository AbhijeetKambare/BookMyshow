package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDtos.TickitEntryDto;
import com.example.BookMyShow.Services.TickitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickit")
public class TickitController {
    @Autowired
    TickitService tickitService;
    @PostMapping("/create")
    public ResponseEntity bookTickit(@RequestBody() TickitEntryDto tickitEntryDto){
        try {
            String response=tickitService.booktickit(tickitEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String respoonse ="not able genrate your tickit";
            return new ResponseEntity<>(respoonse, HttpStatus.BAD_REQUEST);
        }
    }
}
