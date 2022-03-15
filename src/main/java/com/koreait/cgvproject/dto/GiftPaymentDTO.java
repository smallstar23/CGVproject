package com.koreait.cgvproject.dto;

import com.koreait.cgvproject.entity.Gift;
import com.koreait.cgvproject.entity.GiftPayment;
import com.koreait.cgvproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class GiftPaymentDTO {

    private Long gpcode;
    private Long memIdx;
    private int gcode;
    private LocalDateTime regDate;
    private String status;

//    public GiftPayment toEntity(GiftPaymentDTO giftPaymentDTO){
//        return new GiftPayment(null, Member.builder().build(), Gift.builder().build(),regDate.now(),status);
//    }
}
