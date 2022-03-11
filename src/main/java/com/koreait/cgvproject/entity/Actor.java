package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.ActorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thymeleaf.spring5.expression.Mvc;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq_actor",
        sequenceName="seq_actor",
        initialValue = 1,
        allocationSize = 1
)
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_actor")
    private Long idx;
    @ManyToOne
    @JoinColumn(name = "mcode")
    private Movie movie;
    private String nameKo;
    private String nameEn;
    private String photo;

    public ActorDTO toDTO(){
        return  ActorDTO.builder().idx(idx).mcode(movie.getMcode()).nameKo(nameKo).nameEn(nameEn)
                .photo(photo).build();
    }

}
