package com.example.BookMyShow.Converts;

import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.Models.ShowEntity;

public class ShowConverter {
    public static ShowEntity convertDtoToEntity(ShowEntryDto entryDto){
        ShowEntity showEntity=ShowEntity.builder().showDate(entryDto.getLocalDate())
                .showTime(entryDto.getLocalTime())
                .showType(entryDto.getShowType()).build();
        return showEntity;
    }
}
