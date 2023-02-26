package com.example.BookMyShow.Converts;

import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Models.UserEntity;

public class UserConverter {
    public static UserEntity convertDtotoEntity(UserEntryDto entryDto){
        UserEntity userEntity=UserEntity.builder().age(entryDto.getAge()).mobNo(entryDto.getMobNo()).name(entryDto.getName()).email(entryDto.getEmail())
                .address(entryDto.getAddress()).build();
        return userEntity;
    }
}
