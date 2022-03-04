package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminMovieController {

    @GetMapping("/movie/manage_movieSchedule")//movie-schedule 페이지
    public String movie_schedule(){
        return "/admin/movie/manage_movieSchedule";
    }
    @GetMapping("/movie/manage_pricem")//movie-pricem 페이지
        public String movie_pricem(){
        return "/admin/movie/manage_pricem";
    }
    @GetMapping("/movie/manage_theaters")//movie-theaters 페이지
    public String movie_theaters(){
        return "/admin/movie/manage_theaters";
    }
    @GetMapping("/movie/manage_ongoingmovies")//movie-ongoing 페이지
    public String movie_ongoing(){
        return "/admin/movie/manage_ongoingmovies";
    }
}
