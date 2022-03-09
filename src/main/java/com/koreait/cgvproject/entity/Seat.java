package com.koreait.cgvproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "seq_seat",
        sequenceName = "seq_seat",
        initialValue = 1,
        allocationSize = 1
)
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_seat")
    private Long stcode;
    private Long hcode;
    private String stNum;
    private String stName;
    private Integer disabled;
}
