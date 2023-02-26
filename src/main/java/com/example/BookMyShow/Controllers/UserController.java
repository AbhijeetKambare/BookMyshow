package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserEntryDto entryDto){
        try{
            String response=userService.createUser(entryDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            String repsonse="User unable to add";
            return new ResponseEntity<>(repsonse,HttpStatus.BAD_REQUEST);
        }
    }
}
