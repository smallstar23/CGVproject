package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.DirectorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq_director",
        sequenceName="seq_director",
        initialValue = 1,
        allocationSize = 1
)
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_director")
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "mcode")
    private Movie  movie;

    private String nameKo;
    private String nameEn;
    private String photo;

    public DirectorDTO toDTO(){
        return  DirectorDTO.builder().idx(idx).mcode(movie.getMcode()).nameKo(nameKo).nameEn(nameEn)
                .photo(photo).build();
    }
}
