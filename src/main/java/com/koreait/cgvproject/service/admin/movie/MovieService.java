package com.koreait.cgvproject.service.admin.movie;


import com.koreait.cgvproject.dto.ActorDTO;
import com.koreait.cgvproject.dto.DirectorDTO;
import com.koreait.cgvproject.dto.TrailerDTO;
import com.koreait.cgvproject.entity.Actor;
import com.koreait.cgvproject.entity.Director;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Trailer;
import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.repository.ActorRepository;
import com.koreait.cgvproject.repository.DiretorRepository;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.repository.TrailerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MovieService {

    private MovieRepository movieRepository;
    private TrailerRepository trailerRepository;
    private DiretorRepository diretorRepository;
    private ActorRepository actorRepository;

    @Transactional
    public void insertPoint(MovieDTO movieDTO , Director director, Trailer trailer, Actor actor){
         movieRepository.save(movieDTO.toEntity());

         diretorRepository.save(director);
         trailerRepository.save(trailer);
         actorRepository.save(actor);
         log.info(movieDTO.toString());
         log.info(director.toString());
         log.info(trailer.toString());
         log.info(actor.toString());

    }

    public List<MovieDTO> getMovieList(){
        List<Movie> movieEntityList =movieRepository.findAll();
        List<MovieDTO> movieDTOList=new ArrayList<>();

        for(Movie movie : movieEntityList){
            MovieDTO movieDTO = MovieDTO.builder()
                    .mcode(movie.getMcode())
                    .mrank(movie.getMrank())
                    .titleKo(movie.getTitleKo())
                    .titleEn(movie.getTitleEn())
                    .genre(movie.getGenre())
                    .country(movie.getCountry())
                    .movieRating(movie.getMovieRating())
                    .runtime(movie.getRuntime())
                    .launchDate(movie.getLaunchDate())
                    .regDate(movie.getRegDate())
                    .poster(movie.getPoster())
                    .build();
             movieDTOList.add(movieDTO);
        }
        return movieDTOList;
    }


    @Transactional
    public  MovieDTO getPost(Long mcode){
        Movie movie =movieRepository.findById(mcode).get();

        MovieDTO movieDTO =MovieDTO.builder()
                .mcode(movie.getMcode())
                .mrank(movie.getMrank())
                .titleKo(movie.getTitleKo())
                .titleEn(movie.getTitleEn())
                .genre(movie.getGenre())
                .country(movie.getCountry())
                .movieRating(movie.getMovieRating())
                .runtime(movie.getRuntime())
                .launchDate(movie.getLaunchDate())
                .regDate(movie.getRegDate())
                .poster(movie.getPoster())
                .build();

        return movieDTO;
    }

    @Transactional
    public  void delete(Long mcode){
        movieRepository.deleteById(mcode);
    }
}
