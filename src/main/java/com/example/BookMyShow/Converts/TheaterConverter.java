package com.example.BookMyShow.Converts;

import com.example.BookMyShow.EntryDtos.TheaterEntryDto;
import com.example.BookMyShow.Models.TheaterEntity;
import lombok.Data;

@Data

public class TheaterConverter {
    public static TheaterEntity TheaterDtoEntry(TheaterEntryDto entryDto){
        return TheaterEntity.builder().theaterName(entryDto.getTheaterName()).location(entryDto.getLocation())
                .build();
    }
}
