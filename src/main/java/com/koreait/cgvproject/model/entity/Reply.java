package com.koreait.cgvproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq_reply",
        sequenceName="seq_reply",
        initialValue = 1,
        allocationSize = 1
)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_reply")
    private Long idx;

    @ManyToOne
    private Movie movie;
    //private Long mcode;
    private Long memIdx;
    private String content;
    private Long reLike;
    private LocalDateTime regDate;


}
