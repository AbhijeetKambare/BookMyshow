package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converts.TickitConverter;
import com.example.BookMyShow.EntryDtos.TickitEntryDto;
import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.Models.ShowSeatEntity;
import com.example.BookMyShow.Models.TicketEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TickitRepository;
import com.example.BookMyShow.Repositories.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TickitService {
    @Autowired
    TickitRepository tickitRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;
    public String booktickit(TickitEntryDto tickitEntryDto)throws Exception{
        TicketEntity ticket= TickitConverter.convrtDtoToEntry(tickitEntryDto);


        //we will do validation if requested is avialble or not
        boolean isValidSeats=checkvailabilty(tickitEntryDto);
        if(isValidSeats==false){
            throw new Exception("Seats are not vailable");
        }

        //calculating the amaount for setiing attributes
        ShowEntity show=showRepository.findById(tickitEntryDto.getShowId()).get();
        List<ShowSeatEntity> showSeatEntityList=show.getListOfShowSeats();
        List<String> reqstedseats=tickitEntryDto.getRequestedSeats();
        int totalAmount=0;
        for(ShowSeatEntity showSeat:showSeatEntityList){
            if(reqstedseats.contains(showSeat.getSeatNo())){
                totalAmount+=showSeat.getPrice();
                //we are setiting attributes for ShowSeat which are present in showseat entity
                showSeat.setIsBooked(true);
                showSeat.setBookedAt(new Date());
            }
        }
        //we want list for allote dseats and needs to be set
        String allotedSeats=getAllotedseatsfromShowseat(tickitEntryDto);

        //setting the attributes of tickits
        ticket.setBookedSeats(allotedSeats);

        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setMovieName(show.getMovieEntity().getMovieName());
        ticket.setShowTime(show.getShowTime());
        ticket.setShowDate(show.getShowDate());
        ticket.setTheaterName(show.getTheaterEntity().getTheaterName());
        ticket.setTotalAmount(totalAmount);

        //setting forign key atrributes
        UserEntity userEntity=userRepository.findById(tickitEntryDto.getUserId()).get();
        ticket.setUserEntity(userEntity);
        ticket.setShowEntity(show);


        tickitRepository.save(ticket);
        //setting parent attributes

        //there are forgin key attri in there prospective entity they have List<BookedLikitis> that we need to update
        //always update in list
        List<TicketEntity> ticketEntityList=userEntity.getBookedTicketList();
        ticketEntityList.add(ticket);
        userEntity.setBookedTicketList(ticketEntityList);
        userRepository.save(userEntity);//saving parent

        List<TicketEntity> ticketEntityList1=show.getListOfBookedTickets();
        ticketEntityList1.add(ticket);
        show.setListOfBookedTickets(ticketEntityList1);
        showRepository.save(show);

        //this is for email inegration
        String body="Hi thank You for confirming your Tickets "+ticket.getTicketId()+" with seat Number "+allotedSeats+"\n"+
                "For show "+show.getShowDate() +" at "+ show.getShowTime()+ " in "+show.getShowType();

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
        messageHelper.setFrom("isoabhikambare@gmail.com");
        messageHelper.setTo(userEntity.getEmail());
        messageHelper.setText(body);
        messageHelper.setSubject("Confirming your tickits");
        return "tickit generated successfully";
    }
    private  String getAllotedseatsfromShowseat(TickitEntryDto entryDto){
        //in this we are just converting th e list to string
        String result="";
        for(String s: entryDto.getRequestedSeats()){
            result=result+s+", ";
        }
        return result;
    }
    private boolean checkvailabilty(TickitEntryDto tickitEntryDto){
        List<ShowSeatEntity> showSeatEntityList=showRepository.findById(tickitEntryDto.getShowId()).get().getListOfShowSeats();
        List<String> requestedSeats=tickitEntryDto.getRequestedSeats();
        for (ShowSeatEntity showSeat:showSeatEntityList){
            if(requestedSeats.contains(showSeat.getSeatNo()))
                if (showSeat.getIsBooked()==true)
                    return false; //here we are return even if single seat is booked among requested seats
        }
        return true;//allseats are available
    }
}
