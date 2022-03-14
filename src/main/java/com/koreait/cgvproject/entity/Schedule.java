package com.koreait.cgvproject.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "seq_schedule",
        sequenceName = "seq_schedule",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@ToString(exclude = {"movie","hall"})
public class Schedule {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "seq_schedule"
    )
    private Long schecode;

    @ManyToOne
    @JoinColumn(name = "mcode")
    private Movie movie;
    // private Long mcode;

    @ManyToOne
    @JoinColumn(name = "hcode")
    private Hall hall;

    //private Long hcode;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scdate;


}
