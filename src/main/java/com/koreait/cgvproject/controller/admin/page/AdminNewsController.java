package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.Admin_News_DTO;
import com.koreait.cgvproject.entity.AdminNewsEntity;
import com.koreait.cgvproject.entity.MemberinfoEntity;
import com.koreait.cgvproject.repository.AdminNewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class AdminNewsController {

    @Autowired
    private AdminNewsRepository adminNewsRepository;

    @GetMapping("manage_news")//manage_news  페이지
    public  String manage_news(Model model){
        // 1. 모든 공지사항 가져오기
        List<AdminNewsEntity> adminNewsEntitiyList = adminNewsRepository.findAll();
        // 2. 가져온 공지사항 묶음을 뷰로 전달
        model.addAttribute("adminNewsList",adminNewsEntitiyList);
        // 3. 뷰 페이지를 설정
        return "/admin/news/manage_news";
    }

    @GetMapping("manage_news_detail/{id}")//manage_news 상세페이지
    public  String manage_news_detail(@PathVariable Long id, Model model){

        // 1. ID로 데이터를 가져옴!
        AdminNewsEntity adminNewsEntity = adminNewsRepository.findById(id).orElse(null);
//        Optional<MemberinfoEntity> memberinfoEntity = memberinfoRepository.findById(id);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("managenews",adminNewsEntity);
//        model.addAttribute("managenewsfirst",adminNewsEntity);

        // 3. 보여줄 페이지를 설정!
        return "/admin/news/manage_news_detail";
    }

    @GetMapping("manage_news_write")//manage_news  페이지
    public  String manage_news_write(){
        return "/admin/news/manage_news_write";
    }

    @GetMapping("manage_qna")//manage_qna  페이지
    public  String manage_qna(){
        return "/admin/news/manage_qna";
    }

    @GetMapping("manage_qna_detail")//manage_qna  페이지
    public  String manage_qna_detail(){
        return "/admin/news/manage_qna_detail";
    }

    @GetMapping("manage_qna_write")//manage_qna  페이지
    public  String manage_qna_write(){
        return "/admin/news/manage_qna_write";
    }

    // 공지사항 작성 post 매핑
    @PostMapping("manage_news_clear")
    public String manage_news_clear(Admin_News_DTO adminNewsDto){

        log.info(adminNewsDto.toString());

        AdminNewsEntity adminNewsEntity = adminNewsDto.toEntity();
        log.info(adminNewsEntity.toString());

        AdminNewsEntity saved = adminNewsRepository.save(adminNewsEntity);
        log.info(saved.toString());

        return "redirect:manage_news_detail/" + saved.getIdx();
    }
}
