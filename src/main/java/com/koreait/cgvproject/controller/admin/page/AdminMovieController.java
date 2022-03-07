package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.HallDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.service.admin.hall.AdminHallService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.koreait.cgvproject.service.admin.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;




import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class AdminMovieController {

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

    @PostMapping("/manage_movieSchedule_create")
    public String writes(MovieDTO movieDTO){
        movieService.insertPoint(movieDTO);
        return "redirect:/";
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
    public String movie_theaters(Model model){

        List<HallDTO> hallDTOList = adminHallService.getHallList();
        model.addAttribute("HallList",hallDTOList);

        return "/admin/movie/manage_theaters";
    }

    @GetMapping("/manage_theaters_create")//movie-theaters 페이지
    public String movie_theaters_create(){
        return "/admin/movie/manage_theaters_create";
    }

    @PostMapping("/manage_theaters_create")
    public String movie_theaters_write(@ModelAttribute HallDTO hallDTO, Model model){
        log.info(hallDTO.toString());
        adminHallService.savePost(hallDTO);

        List<HallDTO> hallDTOList = adminHallService.getHallList();
        model.addAttribute("HallList",hallDTOList);


        return "redirect:/manage_theaters";
    }

    @GetMapping("manage_ongoingmovies")//movie-ongoing 페이지
    public String movie_ongoing(Model model){
        List<MovieDTO> movieDTOList =movieService.getMovieList();
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
        return "redirect:/";
    }
}
