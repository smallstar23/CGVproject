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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Hall{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_hall")
    private Long hcode;
    private Long tcode;
    private String hname;
    private String location;

    @CreatedDate
    private LocalDateTime regdate;

    @Builder
    public Hall(Long hcode, Long tcode, String hname, String location, LocalDateTime regdate){
        this.hcode = hcode;
        this.tcode = tcode;
        this.hname = hname;
        this.location = location;
        this.regdate = regdate;
    }
}
