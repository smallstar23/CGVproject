package com.koreait.cgvproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminMovieController {

    @GetMapping("manage_movieSchedule")//movie-schedule 페이지
    public String movie_schedule(){
        return "/admin/movie/manage_movieSchedule";
    }

    @GetMapping("manage_movieSchedule_create")//movie-schedule 페이지
    public String movie_schedule_create(){
        return "/admin/movie/manage_movieSchedule_create";
    }

    @GetMapping("manage_pricem")//movie-pricem 페이지
        public String movie_pricem(){
        return "/admin/movie/manage_pricem";
    }

    @GetMapping("manage_pricerm_create")//movie-pricem 페이지
    public String movie_pricem_create(){
        return "/admin/movie/manage_pricerm_create";
    }

    @GetMapping("/manage_theaters")//movie-theaters 페이지
    public String movie_theaters(){
        return "/admin/movie/manage_theaters";
    }

    @GetMapping("/manage_theaters_create")//movie-theaters 페이지
    public String movie_theaters_create(){
        return "/admin/movie/manage_theaters_create";
    }

    @GetMapping("manage_ongoingmovies")//movie-ongoing 페이지
    public String movie_ongoing(){
        return "/admin/movie/manage_ongoingmovies";
    }

    @GetMapping("manage_ongoingmovies_create")//movie-ongoing 페이지
    public String movie_ongoing_create(){
        return "/admin/movie/manage_ongoingmovies_create";
    }
}
