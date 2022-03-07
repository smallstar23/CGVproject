package com.koreait.cgvproject.service.admin.movie;


import com.koreait.cgvproject.dto.MovieDTO;
import com.koreait.cgvproject.entity.Movie;
<<<<<<< HEAD:src/main/java/com/koreait/cgvproject/service/admin/service/MovieService.java
//import com.koreait.cgvproject.entity.MovieEntity;
=======
>>>>>>> 05cb7e02b6e1923f889dede347d9f8350449bc3e:src/main/java/com/koreait/cgvproject/service/admin/movie/MovieService.java
import com.koreait.cgvproject.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    @Transactional
    public void insertPoint(MovieDTO movieDTO){
         movieRepository.save(movieDTO.toEntity());
    }

    public List<MovieDTO> getMovieList(){
        List<Movie> movieEntityList =movieRepository.findAll();
        List<MovieDTO> movieDTOList=new ArrayList<>();

        for(Movie movie : movieEntityList){
            MovieDTO movieDTO = MovieDTO.builder()

                    .mcode(movie.getMcode())
                    .mrank(movie.getMrank())
                    .titleKo(movie.getTitleKo())
                    .titleEn(movie.getTitleEn())
                    .genre(movie.getGenre())
                    .country(movie.getCountry())
                    .movieRating(movie.getMovieRating())
                    .runtime(movie.getRuntime())
                    .launchDate(movie.getLaunchDate())
                    .regDate(movie.getRegDate())
                    .poster(movie.getPoster())
                    .build();
             movieDTOList.add(movieDTO);
        }
        return movieDTOList;
    }
}
