package com.koreait.cgvproject.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name="seq_Wishlist",
        sequenceName ="seq_Wishlist",
        initialValue = 1,
        allocationSize = 1
)
@Entity
@Builder
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_Wishlist")
    private  Long idx;


    @ManyToOne
    private  Movie mcode;
    @ManyToOne
    private  Member mem_idx;


}
