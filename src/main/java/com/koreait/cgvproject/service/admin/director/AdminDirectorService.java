package com.koreait.cgvproject.service.admin.director;


import com.koreait.cgvproject.dto.DirectorDTO;
import com.koreait.cgvproject.entity.Director;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.repository.DiretorRepository;
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
public class AdminDirectorService {

    @Autowired
    private DiretorRepository diretorRepository;
    @Autowired
    private MovieRepository movieRepository;



    @Transactional
    public DirectorDTO findDiretor(Long mcode){
        Movie movie=movieRepository.findByMcode(mcode);
        Director director =diretorRepository.findByMovie(movie);
        if (director !=null){
            DirectorDTO directorDTO= new DirectorDTO();
            directorDTO.setMovie(movie.toDTO());
//          directorDTO.setMcode(director.getMovie().getMcode());
            directorDTO.setDnameKo(director.getDnameKo());
            directorDTO.setDnameEn(director.getDnameEn());
            directorDTO.setDphoto(director.getDphoto());
            directorDTO.setDirectoridx(director.getIdx());


            return  directorDTO;
        }
        return  null;
    }

    public List<DirectorDTO> getDirectorList(Long mcode){
        Movie movie =movieRepository.findByMcode(mcode);
        List<DirectorDTO> directorDTOList =new ArrayList<>();
        if(movie !=null){
            movie.getDirector().stream().forEach(director -> {
                directorDTOList.add(director.toDTO());
            });
        }
        return directorDTOList;
    }






}
