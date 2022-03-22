package com.koreait.cgvproject.service.user.moive;

import com.koreait.cgvproject.dto.ActorDTO;
import com.koreait.cgvproject.dto.DirectorDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.TrailerDTO;
import com.koreait.cgvproject.entity.Actor;
import com.koreait.cgvproject.entity.Director;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Trailer;
import com.koreait.cgvproject.repository.ActorRepository;
import com.koreait.cgvproject.repository.DiretorRepository;
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
    private TrailerRepository trailerRepository;
    private DiretorRepository diretorRepository;
    private ActorRepository actorRepository;


    public List<MovieDTO> getList(){

        List<MovieDTO> movieDTOList=new ArrayList<>();
        List<Movie> movieList =movieRepository.findAll();

        for(Movie movie  : movieList){
            movieDTOList.add(movie.toDTO());
        }
        return movieDTOList;
    }

    public  List<MovieDTO> getMovieinfo(Long mcode){

        List<MovieDTO> movieDTOList=new ArrayList<>();
        Movie movieList=movieRepository.findByMcode(mcode);

        movieDTOList.add(movieList.toDTO());
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

    public TrailerDTO getTrailer(Movie movie){
        Trailer trailer= trailerRepository.findByMovie(movie);
        TrailerDTO trailerDTO=trailer.toDTO();
        return trailerDTO;
    }

    public DirectorDTO getDirector(Movie movie){
        Director director=diretorRepository.findByMovie(movie);
        DirectorDTO directorDTO=director.toDTO();
        return directorDTO;
    }
    public ActorDTO getActor(Movie movie){
        Actor actor=actorRepository.findByMovie(movie);
        ActorDTO actorDTO =actor.toDTO();
        return  actorDTO;
    }


}
