package com.koreait.cgvproject.model.entity;

import com.koreait.cgvproject.model.dto.DirectorDTO;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "movie")
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

    private String dnameKo;
    private String dnameEn;
    private String dphoto;

    public DirectorDTO toDTO(){
        return  DirectorDTO.builder().idx(idx).movie(movie.toDTO()).dnameKo(dnameKo).dnameEn(dnameEn)
                .dphoto(dphoto).build();
    }
}
