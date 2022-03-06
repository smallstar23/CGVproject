package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserMovieRestController {

    @Autowired
    UserMovieService userMovieService;

    @GetMapping("/api")
    public List<MovieDTO> list(){
        return userMovieService.getList();
    }
}
