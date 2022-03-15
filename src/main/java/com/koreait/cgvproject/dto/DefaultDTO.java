package com.koreait.cgvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class DefaultDTO {
    // 이 클래스는 RequestParam에 값이 없을 경우 Model객체에 넣어줄 대 DTO입니다.
    // 필요한 필드를 final로 선언해서 사용하시면 될 것 같습니다.
    private final String tcode = "none";
    private final String areacode = "none";
}
