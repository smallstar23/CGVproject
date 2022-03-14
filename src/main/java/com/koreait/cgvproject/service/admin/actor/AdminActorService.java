package com.koreait.cgvproject.service.admin.actor;


import com.koreait.cgvproject.dto.ActorDTO;
import com.koreait.cgvproject.entity.Actor;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.repository.ActorRepository;
import com.koreait.cgvproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AdminActorService {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;



    @Transactional
    public ActorDTO findActor(Long mcode){
        Movie movie =movieRepository.findByMcode(mcode);
        Actor actor =actorRepository.findByMovie(movie);

        if(actor!= null){
            ActorDTO actorDTO =new ActorDTO();
            actorDTO.setAnameKo(actor.getAnameKo());
            actorDTO.setAnameEn(actor.getAnameEn());
            actorDTO.setAphoto(actor.getAphoto());
//          actorDTO.setMcode(actor.getMovie().getMcode());
            actorDTO.setMovie(movie.toDTO());
            actorDTO.setActoridx(actor.getIdx());

            return actorDTO;
        }
        return null;
    }

    public List<ActorDTO> getActorList(Long mcode){
        Movie movie =movieRepository.findByMcode(mcode);
        List<ActorDTO> actorDTOList = new ArrayList<>();
        if (movie!= null){
            movie.getActors().stream().forEach(actor -> {
                actorDTOList.add(actor.toDTO());
            });
        }
        return actorDTOList;
    }

}
