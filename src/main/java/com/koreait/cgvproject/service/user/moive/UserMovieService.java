package com.koreait.cgvproject.service.user.moive;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.entity.Gift;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMovieService {
    private MovieRepository movieRepository;

    public List<MovieDTO> getList(){
        List<MovieDTO> movieDTOList=new ArrayList<>();
        List<Movie> movieList=movieRepository.findAll();
        for(Movie movie: movieList){
            movieDTOList.add(movie.toDTO());
        }
        return movieDTOList;
    };


}
