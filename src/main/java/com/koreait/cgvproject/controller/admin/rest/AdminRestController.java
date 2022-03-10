package com.koreait.cgvproject.controller.admin.rest;

import com.koreait.cgvproject.dto.TheaterDTO;
import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.repository.TheaterRepository;
import com.koreait.cgvproject.service.admin.AdminService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
public class AdminRestController {

    private AdminService adminService;

    @GetMapping("/findTheater/{areacode}")
    // 영화 지역코드로 찾기 모듈화 해야됨
    public List<TheaterDTO> findTheater(@PathVariable Long areacode) {
        return null;
    }
}
