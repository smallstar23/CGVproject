package com.koreait.cgvproject.service.user.moive;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.repository.TrailerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMovieService {

    private MovieRepository movieRepository;

    public List<MovieDTO> getList(){

        List<MovieDTO> movieDTOList=new ArrayList<>();
        List<Movie> movieList =movieRepository.findAll();

        for(Movie movie  : movieList){
            movieDTOList.add(movie.toDTO());
        }
        return movieDTOList;
    }


    public List<MovieDTO> getnow(String now){
        List<MovieDTO> movieDTOList=new ArrayList<>();
        List<Movie> nowmovie= movieRepository.findAllByMscreen(now);
        for(Movie movie: nowmovie){
            movieDTOList.add(movie.toDTO());
        }
        return movieDTOList;
    }

//    public TrailerDTO getTrailer(Long mcode){
//        Trailer trailer= trailerRepository.findByMcode(mcode);
//        TrailerDTO trailerDTO=trailer.toDTO();
//        return trailerDTO;
//    }


}
