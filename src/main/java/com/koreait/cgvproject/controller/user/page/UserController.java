package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@AllArgsConstructor
@Controller
@Slf4j
public class UserController {

    @Autowired
    HttpSession httpSession;
    private MovieRepository movieRepository;


    @GetMapping("/main")
    public String main(Model model){
        List<Movie> movieList=movieRepository.findAll();

        model.addAttribute("movieList",movieList);
        System.out.println(movieList);
        return "/user/main";
    }
}
