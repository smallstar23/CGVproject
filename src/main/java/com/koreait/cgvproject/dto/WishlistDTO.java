package com.koreait.cgvproject.dto;


import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Wishlist;
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
