package com.koreait.cgvproject.entity;

import com.koreait.cgvproject.dto.GiftDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@SequenceGenerator(
        name = "seq_gift",
        sequenceName = "seq_gift",
        initialValue = 1,
        allocationSize = 1
)
@Data @Entity @NoArgsConstructor @AllArgsConstructor @Builder
public class Gift{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_gift")
    private Long gcode;
    private String category;
    private String title;
    private String gname;
    private String content;
    private String price;
    private String gfile;
    private String endMonth;
    private Integer mainon;
    private String mainonString;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gift")
    private List<GiftPayment> giftPayments;

    public GiftDTO toDTO(){
        return GiftDTO.builder().gcode(gcode).category(category).title(title).gname(gname)
                .content(content).price(price).gfile(gfile).endMonth(endMonth).mainon(mainon).mainonString(mainonString).build();
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
//    private List<GiftPayment> giftPaymentList;
}
