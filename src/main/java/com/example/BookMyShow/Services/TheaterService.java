package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converts.TheaterConverter;
import com.example.BookMyShow.EntryDtos.TheaterEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.TheaterEntity;
import com.example.BookMyShow.Models.TheaterSeatEnity;
import com.example.BookMyShow.Repositories.TheaterRepository;
import com.example.BookMyShow.Repositories.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatRepository seatRepository;
    public String createThetaer(TheaterEntryDto theaterEntryDto){
        TheaterEntity theaterEntity= TheaterConverter.TheaterDtoEntry(theaterEntryDto);
        List<TheaterSeatEnity> seatEnities=createThetaerseats(theaterEntryDto,theaterEntity);
        return "Theatre is created";
    }
    public List<TheaterSeatEnity> createThetaerseats(TheaterEntryDto entryDto,TheaterEntity theater){
        int noofClassicSeats=entryDto.getClassicSeatCount();
        int noOfPremiumSeatCount= entryDto.getPremiumSeatCount();
        List<TheaterSeatEnity> seatEnityList=new ArrayList<>();

        for (int count=1;count<=noofClassicSeats;count++){
            TheaterSeatEnity theaterSeatEnity=TheaterSeatEnity.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C").build();
            seatEnityList.add(theaterSeatEnity);
        }
        for (int count=1;count<=noofClassicSeats;count++){
            TheaterSeatEnity theaterSeatEnity=TheaterSeatEnity.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C").build();
            seatEnityList.add(theaterSeatEnity);
        }
          seatRepository.saveAll(seatEnityList);
        return seatEnityList;
    }
}
