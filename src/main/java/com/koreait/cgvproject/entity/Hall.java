package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.HallDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@SequenceGenerator(
        name = "seq_hall_hcode",
        sequenceName = "seq_hall_hcode",
        initialValue = 1,
        allocationSize = 1
)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Hall{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_hall")
    private Long hcode;
    private Long tcode;
    private String hname;
    private String location;

    @CreatedDate
    private LocalDateTime regdate;

}
