package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.ActorDTO;
import com.koreait.cgvproject.dto.DirectorDTO;
import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.dto.TrailerDTO;
import com.koreait.cgvproject.entity.Actor;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.repository.ActorRepository;
import com.koreait.cgvproject.repository.MovieRepository;
import com.koreait.cgvproject.service.user.moive.UserMovieService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Controller
public class UserMovieController {

    @Autowired
    private UserMovieService userMovieService;
    @Autowired
    private MovieRepository movieRepository;


    @GetMapping("movies")
    public String movies(@PageableDefault(size = 10,sort = "mcode",direction = Sort.Direction.DESC) Pageable pageable, Model model){
        List<MovieDTO> movieDTOList=userMovieService.getList();
        model.addAttribute("MovieList", movieDTOList);
        return "/user/movies/movies";
    }


    @GetMapping("/movies/detail-view/{mcode}")
    public String detailview(@PathVariable("mcode") Long mcode, Model model){
        Movie movie =movieRepository.findByMcode(mcode);
        model.addAttribute("movie",movie);
        return "user/movies/detail-view";
    }

    @GetMapping("movies/detail-view/cast/{mcode}")
    public String cast(@PathVariable("mcode")Long mcode, Model model){
        Movie movie=movieRepository.findByMcode(mcode);
        model.addAttribute("movie",movie);
        return "user/movies/cast";
    }

    @GetMapping("movies/detail-view/trailer/{mcode}")
    public String trailer(@PathVariable("mcode")Long mcode, Model model){
        Movie movie=movieRepository.findByMcode(mcode);
        model.addAttribute("movie",movie);
        return "user/movies/trailer";
    }
    @GetMapping("movies/detail-view/still-cut/{mcode}")
    public String stillcut(@PathVariable("mcode")Long mcode, Model model){
        Movie movie=movieRepository.findByMcode(mcode);
        model.addAttribute("movie",movie);
        return "user/movies/still-cut";
    }

    @GetMapping("movies/detail-view/detail-show-times/{mcode}")
    public String detailshowtime(@PathVariable("mcode")Long mcode, Model model){
        Movie movie=movieRepository.findByMcode(mcode);
        model.addAttribute("movie",movie);
        return "user/movies/detail-show-times";
    }


}
