package com.koreait.cgvproject.entity;

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

    //private Long mcode;
    @ManyToOne
    private Movie movie;

    private String nameKo;
    private String nameEn;
    private String photo;


}
