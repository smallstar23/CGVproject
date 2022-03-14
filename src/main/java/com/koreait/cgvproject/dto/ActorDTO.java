package com.koreait.cgvproject.dto;


import com.koreait.cgvproject.entity.Actor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorDTO {
     private  Long actoridx;
     private  MovieDTO movie;
     private  Long mcode;
     private  String anameKo;
     private  String anameEn;
     private  String aphoto;

     public Actor toEntityCreate(){
          return Actor.builder()
                  .idx(actoridx).anameKo(anameKo)
                  .anameEn(anameEn).aphoto(aphoto)
                  .movie(movie.toEntity()).build();
     }

}
