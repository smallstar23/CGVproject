package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import com.koreait.cgvproject.service.user.store.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserMovieController {

    @Autowired
    private UserMovieService userMovieService;

    @GetMapping("movies")
    public String movies(Model model){
        List<MovieDTO> movieDTOList=userMovieService.getList();
        model.addAttribute("MovieList", movieDTOList);
        return "/user/movies/movies";
    }
    @GetMapping("/movies/detail-view/")
    public String detailview(Model model){
        return "user/movies/detail-view";
    }

    @GetMapping("movies/detail-view/cast")
    public String cast(Model model){
        return "user/movies/cast";
    }

    @GetMapping("movies/detail-view/trailer")
    public String trailer(Model model){
        return "user/movies/trailer";
    }
    @GetMapping("movies/detail-view/still-cut")
    public String stillcut(Model model){
        return "user/movies/still-cut";
    }

    @GetMapping("movies/detail-view/detail-show-times")
    public String detailshowtime(Model model){
        return "user/movies/detail-show-times";
    }


}
