package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.*;
import com.koreait.cgvproject.entity.Actor;
import com.koreait.cgvproject.entity.Director;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Trailer;
import com.koreait.cgvproject.service.admin.hall.AdminHallService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.koreait.cgvproject.service.admin.movie.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@Slf4j
public class AdminMovieController {

    @Autowired
    private MovieService movieService;

    private AdminHallService adminHallService;

//    private MovieService movieService;


    @GetMapping("manage_movieSchedule")//movie-schedule 페이지
    public String movie_schedule(){
        return "/admin/movie/manage_movieSchedule";
    }

    @GetMapping("/manage_movieSchedule_create")//movie-schedule 페이지
    public String movie_schedule_create(){
        return "/admin/movie/manage_movieSchedule_create";
    }

//    @PostMapping("/manage_movieSchedule_create")
//    public String writes(MovieDTO movieDTO){
//        movieService.insertPoint(movieDTO);
//        return "redirect:/";
//    }

    @GetMapping("/manage_ongoingmovies" )//movie-ongoing 페이지
    public String movie_ongoing(Model model){
        List<MovieDTO> movieDTOList = movieService.getMovieList();
        model.addAttribute("MovieList",movieDTOList);
        return "/admin/movie/manage_ongoingmovies";
    }

    @GetMapping("/manage_ongoingmovies_create")//movie-ongoing 페이지
    public String movie_ongoing_create(){
        return "/admin/movie/manage_ongoingmovies_create";
    }

    @PostMapping("/manage_ongoingmovies_create")
    public String write(MovieDTO movieDTO, Director director, Trailer trailer, Actor actor){
        movieService.insertPoint(movieDTO, director,trailer,actor);
        return "redirect:/manage_ongoingmovies";
    }

    @GetMapping("/manage_ongoingmovies/{mcode}")
    public  String view(@PathVariable("mcode") Long mcode, Model model){
        MovieDTO movieDTO =movieService.getPost(mcode);
        model.addAttribute("mcode",movieDTO);
        return  "admin/movie/manage_ongoingmovies_view";
    }
    @GetMapping("/manage_ongoingmovies/edit/{mcode}")
    public  String edit(@PathVariable("mcode") Long mcode, Model model){
        MovieDTO movieDTO =movieService.getPost(mcode);
        model.addAttribute("mcode",movieDTO);
        return "admin/movie/manage_ongoingmovies_edit";
    }
//    @PutMapping("/manage_ongoingmovies/edit/{mcode}")
//    public String  update(MovieDTO movieDTO){
//        movieService.insertPoint(movieDTO);
//        return "redirect:/manage_ongoingmovies";
//    }
//
//    @DeleteMapping("/manage_ongoingmovies/{mcode}")
//    public String delete(@PathVariable("mcode") Long mcode){
//        movieService.delete(mcode);
//        return  "redirect:/manage_ongoingmovies";
//    }


}
