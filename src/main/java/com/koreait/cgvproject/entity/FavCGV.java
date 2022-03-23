package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.FavCGVDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(
        name="seq_favCGV",
        sequenceName = "seq_favCGV",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@Table(name="favCGV")
public class FavCGV {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_favCGV")
    private Long idx;

    private Long memIdx;

    private String tname;

    private Long areacode;

    public FavCGVDTO toDTO(){
        return FavCGVDTO.builder().idx(idx).memIdx(memIdx).tname(tname).areacode(areacode).build();
    }

}
