package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.ActorDTO;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "movie")
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
    private String anameKo;
    private String anameEn;
    private String aphoto;

    public ActorDTO toDTO(){
        return  ActorDTO.builder().actoridx(idx).movie(movie.toDTO()).anameKo(anameKo).anameEn(anameEn)
                .aphoto(aphoto).build();
    }

}
