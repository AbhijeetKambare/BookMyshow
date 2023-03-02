package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converts.ShowConverter;
import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repositories.MovieRpository;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TheaterRepository;
import com.example.BookMyShow.RequestDtos.getspecificShowsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRpository movieRpository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto)
    {
        //1. Create a showEntity
        ShowEntity showEntity = ShowConverter.convertDtoToEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        MovieEntity movieEntity = movieRpository.findById(movieId).get();
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();


        //Setting the attribute of foreignKe
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        //Pending attributes the listOfShowSeatsEnity

        List<ShowSeatEntity> seatEntityList = createShowSeatEntity(showEntryDto,showEntity);

        showEntity.setListOfShowSeats(seatEntityList);

        //Now we  also need to update the parent entities

        showEntity = showRepository.save(showEntity);

        movieEntity.getShowEntityList().add(showEntity);
        theaterEntity.getShowEntityList().add(showEntity);


        movieRpository.save(movieEntity);

        theaterRepository.save(theaterEntity);

        return "The show has been added successfully";

    }

    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto,ShowEntity showEntity){

        //Now the goal is to create the ShowSeatEntity
        //We need to set its attribute

        TheaterEntity theaterEntity = showEntity.getTheaterEntity();

        List<TheaterSeatEnity> theaterSeatEntityList = theaterEntity.getTheaterSeatEnityList();

        List<ShowSeatEntity> seatEntityList = new ArrayList<>();

        for(TheaterSeatEnity theaterSeatEntity : theaterSeatEntityList){

            ShowSeatEntity showSeatEntity = new ShowSeatEntity();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC))
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());

            else
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());

            showSeatEntity.setIsBooked(false);
            showSeatEntity.setShowEntity(showEntity); //parent : foreign key for the showSeat Entity

            seatEntityList.add(showSeatEntity); //Adding it to the list
        }

        return  seatEntityList;
    }

    public String getListOFshows(getspecificShowsDto getspecificShowsDto)throws Exception{
        int movieID= getspecificShowsDto.getMovieId();
        int theaterID= getspecificShowsDto.getTheaterId();
        String ans=" ";
        List<ShowEntity> showEntities=showRepository.getshowDetails(movieID, theaterID);
        int count=1;
        for(ShowEntity show:showEntities){
            ans=ans+count+" "+show.getShowDate()+" "+show.getShowTime()+"\n";
            count++;
        }
        return ans;
    }
}
