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
        name="seq_ticket",
        sequenceName = "seq_ticket",
        initialValue = 1,
        allocationSize = 1
)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket")
    private Long ticode;

    @ManyToOne
    @JoinColumn(name="schecode")
    private Schedule schecode;

    @ManyToOne
    @JoinColumn(name="stcode")
    private Seat stcode;

    @ManyToOne
    @JoinColumn(name="Member")
    private Member memIdx;

}
