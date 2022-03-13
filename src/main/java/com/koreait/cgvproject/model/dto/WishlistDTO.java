package com.koreait.cgvproject.model.dto;


import com.koreait.cgvproject.model.entity.Member;
import com.koreait.cgvproject.model.entity.Movie;
import com.koreait.cgvproject.model.entity.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistDTO {
    private Long idx;
    private Member mem_idx;
    private Movie mcode;

    public Wishlist  toEntity(){
       return  Wishlist.builder()
               .idx(idx).mcode(mcode).mem_idx(mem_idx).build();
    }
}
