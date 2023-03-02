package com.example.BookMyShow.Converts;

import com.example.BookMyShow.EntryDtos.TickitEntryDto;
import com.example.BookMyShow.Models.TicketEntity;

public class TickitConverter {
    public static TicketEntity convrtDtoToEntry(TickitEntryDto entryDto){
        TicketEntity ticket=new TicketEntity();
        return ticket;
    }
}
