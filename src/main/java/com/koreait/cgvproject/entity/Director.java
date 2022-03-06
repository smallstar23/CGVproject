package com.koreait.cgvproject.entity;

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

    //private Long mcode;
    @ManyToOne
    private Movie movie;

    private String nameKo;
    private String nameEn;
    private String photo;

}
