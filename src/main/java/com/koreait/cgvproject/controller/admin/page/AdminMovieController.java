package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.ActorDTO;
import com.koreait.cgvproject.dto.DirectorDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.TrailerDTO;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.service.admin.actor.AdminActorService;
import com.koreait.cgvproject.service.admin.director.AdminDirectorService;
import com.koreait.cgvproject.service.admin.hall.AdminHallService;
import com.koreait.cgvproject.service.admin.trailer.AdminTrailerService;
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
    private MovieRepository movieRepository;
    private AdminTrailerService adminTrailerService;
    private AdminDirectorService adminDirectorService;
    private AdminActorService adminActorService;

//    private MovieService movieService;


//    @PostMapping("/manage_movieSchedule_create")
//    public String writes(MovieDTO movieDTO){
//        movieService.insertPoint(movieDTO);
//        return "redirect:/";
//    }


    @GetMapping("manage_pricem")//movie-pricem 페이지
        public String movie_pricem(){
        return "/admin/movie/manage_pricem";
    }

    @GetMapping("manage_pricerm_create")//movie-pricem 페이지
    public String movie_pricem_create(){
        return "/admin/movie/manage_pricerm_create";
    }





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
    public String write(MovieDTO movieDTO){
        movieService.insertPoint(movieDTO);
        return "redirect:/manage_ongoingmovies";
    }

    @GetMapping("/manage_ongoingmovies/{mcode}")
    public  String view(@PathVariable("mcode") Long mcode, Model model){
        MovieDTO movieDTO =movieService.getPost(mcode);
        model.addAttribute("mcode",movieDTO);
        return  "admin/movie/manage_ongoingmovies_view";
    }


    @GetMapping("/manage_ongoingmovies/create/{mcode}")
    public  String view_cre(@PathVariable("mcode") Long mcode, Model model){
        MovieDTO movie =movieRepository.findByMcode(mcode).toDTO();
        System.out.println(movie);
        model.addAttribute("mcode",mcode);
        return  "admin/movie/manage_ongoingmovies_create_test";
    }

    @PostMapping("/manage_ongoingmovies_creates")
    public  String view_create(@ModelAttribute TrailerDTO trailerDTO, DirectorDTO directorDTO, ActorDTO actorDTO, Model model){
        Long trailerint =trailerDTO.getMcode();
        if (movieService.creatTrailer(trailerDTO) == 1) {
            movieService.creatDiretor(directorDTO);
            movieService.creatActor(actorDTO);
            return "redirect:/manage_ongoingmovies/"+trailerint;
        }
        return  "admin/movie/manage_ongoingmovies_create_test";
    }

    @GetMapping("/manage_ongoingmovies/detail/{mcode}")
    public  String view_detail(@PathVariable("mcode") Long mcode, Model model){
         model.addAttribute("mcode",mcode);
         TrailerDTO trailerDTO =adminTrailerService.findTrailer(mcode);
         model.addAttribute("trailer",trailerDTO);
         DirectorDTO directorDTO=adminDirectorService.findDiretor(mcode);
         model.addAttribute("director",directorDTO);
         ActorDTO actorDTO =adminActorService.findActor(mcode);
         model.addAttribute("actor",actorDTO);

        return  "admin/movie/manage_ongoingmovies_detail";
    }


    @GetMapping("/manage_ongoingmovies/edit/{mcode}")
    public  String edit(@PathVariable("mcode") Long mcode, Model model){
        model.addAttribute("mcode",mcode);
        MovieDTO movieDTO =movieService.findMovie(mcode);
        model.addAttribute("movie",movieDTO);
        return "admin/movie/manage_ongoingmovies_edit";
    }


//    @DeleteMapping("/manage_ongoingmovies/{mcode}")
//    public String delete(@PathVariable("mcode") Long mcode){
//        movieService.delete(mcode);
//        return  "redirect:/manage_ongoingmovies";
//    }


}
