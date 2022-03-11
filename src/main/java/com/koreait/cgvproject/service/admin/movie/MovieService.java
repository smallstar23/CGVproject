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

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TrailerRepository trailerRepository;
    @Autowired
    private DiretorRepository diretorRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Transactional
    public void insertPoint(MovieDTO movieDTO ){
         movieRepository.save(movieDTO.toEntity());
         log.info(movieDTO.toString());
    }

    @Transactional
    public  int creatTrailer(TrailerDTO trailerDTO){
        Trailer trailer =new Trailer();
        Movie movie =movieRepository.findByMcode(trailerDTO.getMcode());
        log.info(String.valueOf(movie));
        trailer.setIdx(trailerDTO.getIdx());
        trailer.setMovie(movie);
        trailer.setDescription1(trailerDTO.getDescription1());
        trailer.setDescription2(trailerDTO.getDescription2());
        trailer.setDescription3(trailerDTO.getDescription3());
        trailer.setPhoto1(trailerDTO.getPhoto1());
        trailer.setPhoto2(trailerDTO.getPhoto2());
        trailer.setPhoto3(trailerDTO.getPhoto3());
        trailer.setPhoto4(trailerDTO.getPhoto4());
        trailer.setPhoto5(trailerDTO.getPhoto5());
        trailer.setTrailer1(trailerDTO.getTrailer1());
        trailer.setTrailer2(trailerDTO.getTrailer2());
        trailer.setTrailer3(trailerDTO.getTrailer3());
        trailerRepository.save(trailer);
        log.info(trailer.toString());

        return  1;
    }
    @Transactional
    public  int creatDiretor(DirectorDTO directorDTO){
        Director director=new Director();
        Movie movie =movieRepository.findByMcode(directorDTO.getMcode());
        director.setIdx(directorDTO.getIdx());
        director.setMovie(movie);
        director.setDnameKo(directorDTO.getDnameKo());
        director.setDnameEn(directorDTO.getDnameEn());
        director.setDphoto(directorDTO.getDphoto());
        diretorRepository.save(director);
        log.info(director.toString());
    return 1;
    }
    @Transactional
    public  int creatActor(ActorDTO actorDTO){
        Actor actor =new Actor();
        Movie movie =movieRepository.findByMcode(actorDTO.getMcode());
        actor.setIdx(actorDTO.getIdx());
        actor.setMovie(movie);
        actor.setAnameKo(actorDTO.getAnameKo());
        actor.setAnameEn(actorDTO.getAnameEn());
        actor.setAphoto(actorDTO.getAphoto());
        actorRepository.save(actor);
        log.info(actor.toString());
        return 1;
    }




    public List<MovieDTO> getMovieList(){
        List<Movie> movieEntityList =movieRepository.findAll();
        List<MovieDTO> movieDTOList=new ArrayList<>();

        for(Movie movie : movieEntityList){
            MovieDTO movieDTO = MovieDTO.builder()
                    .mcode(movie.getMcode())
                    .mscreen(movie.getMscreen())
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
                .mscreen(movie.getMscreen())
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


    public MovieDTO findMovie(Long mcode){
        Movie movie =movieRepository.findByMcode(mcode);
        if(movie!=null){
            MovieDTO movieDTO =new MovieDTO();
            movieDTO.setMcode(movie.getMcode());
            movieDTO.setMscreen(movie.getMscreen());
            movieDTO.setTitleKo(movie.getTitleKo());
            movieDTO.setTitleEn(movie.getTitleEn());
            movieDTO.setGenre(movie.getGenre());
            movieDTO.setCountry(movie.getCountry());
            movieDTO.setRuntime(movie.getRuntime());
            movieDTO.setMovieRating(movie.getMovieRating());
            movieDTO.setLaunchDate(movie.getLaunchDate());
            movieDTO.setRegDate(movie.getRegDate());
            movieDTO.setPoster(movie.getPoster());
            return movieDTO;

        }
        return null;
    }

    @Transactional
    public  void delete(Long mcode){
        movieRepository.deleteById(mcode);
    }
}
