package com.koreait.cgvproject.service.admin.trailer;


import com.koreait.cgvproject.dto.TrailerDTO;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Trailer;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.repository.TrailerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AdminTrailerService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TrailerRepository trailerRepository;

    @Transactional
    public TrailerDTO findTrailer(Long mcode){
        Movie movie = movieRepository.findByMcode(mcode);

        Trailer trailer =trailerRepository.findByMovie(movie);
        if (trailer!= null){
            TrailerDTO trailerDTO =new TrailerDTO();
            trailerDTO.setDescription1(trailer.getDescription1());
            trailerDTO.setDescription2(trailer.getDescription2());
            trailerDTO.setDescription3(trailer.getDescription3());
            trailerDTO.setPhoto1(trailer.getPhoto1());
            trailerDTO.setPhoto2(trailer.getPhoto2());
            trailerDTO.setPhoto3(trailer.getPhoto3());
            trailerDTO.setPhoto4(trailer.getPhoto4());
            trailerDTO.setPhoto5(trailer.getPhoto5());
            trailerDTO.setTrailer1(trailer.getTrailer1());
            trailerDTO.setTrailer2(trailer.getTrailer2());
            trailerDTO.setTrailer3(trailer.getTrailer3());
            trailerDTO.setMovieDTO(movie.toDTO());
//          trailerDTO.setMcode(trailer.getMovie().getMcode());
            trailerDTO.setTraileridx(trailer.getIdx());
            return trailerDTO;
        }

        return null;
    }


    public List<TrailerDTO> getTrailerList(Long mcode){
        Movie movie=movieRepository.findByMcode(mcode);
        List<TrailerDTO> trailerDTOList=new ArrayList<>();
        if(movie!= null){
            movie.getTrailer().stream().forEach( trailer -> {
                trailerDTOList.add(trailer.toDTO());
            });
        }
        return trailerDTOList;
    }

    @Transactional
    public void updateTrailerTest(TrailerDTO trailerDTO){
        trailerRepository.save(trailerDTO.toEntityForCreate());
    }



    @Transactional
    public  int updateTrailer(Trailer trailer){

        trailerRepository.save(trailer);
        log.info(trailer.toString());

        return  1;
    }
}
