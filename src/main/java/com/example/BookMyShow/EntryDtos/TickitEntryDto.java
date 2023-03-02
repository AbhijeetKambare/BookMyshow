package com.example.BookMyShow.EntryDtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TickitEntryDto {
    private int showId;
    private int userId;
    private List<String> requestedSeats=new ArrayList<>();


}
