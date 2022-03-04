package com.koreait.cgvproject.controller.user.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserMovieController {

    @GetMapping("movies")
    public String movies(Model model){
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
