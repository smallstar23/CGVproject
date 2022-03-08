package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.HallDTO;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hall{

    @Id
    private Long hcode;

    private Long tcode;
    private String hname;
    private String location;
}
