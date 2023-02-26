package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converts.UserConverter;
import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String createUser(UserEntryDto userEntryDto)throws Exception{
        UserEntity user= UserConverter.convertDtotoEntity(userEntryDto);
        userRepository.save(user);
        return "user addedsuccesfully";
    }
}
