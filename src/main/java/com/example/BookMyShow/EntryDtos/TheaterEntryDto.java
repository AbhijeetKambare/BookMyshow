package com.example.BookMyShow.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class TheaterEntryDto {
    private String theaterName;
    private String location;
    private int classicSeatCount;
    private int premiumSeatCount;
}
