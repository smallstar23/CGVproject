package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.Admin_News_DTO;
import com.koreait.cgvproject.entity.AdminNewsEntity;
import com.koreait.cgvproject.entity.MemberinfoEntity;
import com.koreait.cgvproject.repository.AdminNewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.script.ScriptContext;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("manage_news_edit/{id}")//manage_news 수정페이지
    public  String manage_news_edit(@PathVariable Long id, Model model){

        // 1. ID로 데이터를 가져옴!
        AdminNewsEntity adminNewsEntity = adminNewsRepository.findById(id).orElse(null);
//        Optional<MemberinfoEntity> memberinfoEntity = memberinfoRepository.findById(id);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("edit",adminNewsEntity);
//        model.addAttribute("managenewsfirst",adminNewsEntity);

        // 3. 보여줄 페이지를 설정!
        return "/admin/news/manage_news_edit";
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

    // 공지사항 수정 메소드
    @PostMapping("manage_news_edit/manage_news_update")
    public String update(Admin_News_DTO adminNewsDto){
        log.info(adminNewsDto.toString());

        // 1: DTO를 Entity로 변환
        AdminNewsEntity adminNewsEntity = adminNewsDto.toEntity();
        log.info(adminNewsEntity.toString());
        // 2: 엔티티를 DB로 저장한다.
        // 2-1: DB에서 기존 데이터를 가져올 것임
        AdminNewsEntity target = adminNewsRepository.findById(adminNewsEntity.getIdx()).orElse(null);
//        Optional<AdminNewsEntity> target = adminNewsRepository.findById(adminNewsEntity.getIdx()); -> 옵셔널 잘모르니까 일단 위에껄로 할꺼임

        // 2-2: 기존 데이터가 존재한다면, 값을 갱신한다.
        if(target != null){
            adminNewsRepository.save(adminNewsEntity); // 엔티티가 db로 갱신됌!
        }

        // 3: 수정 결과 페이지로 리다이렉트
        return "redirect:/manage_news_detail/" + adminNewsEntity.getIdx();
    }

    // 공지사항 글 삭제 메소드
    @GetMapping("manage_news_delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");

        // 1: 삭제 대상을 가져옴
        AdminNewsEntity target = adminNewsRepository.findById(id).orElse(null);
        // 2: 대상을 삭제
        if(target != null){
            adminNewsRepository.delete(target);
            // Flash어트리뷰트로 설정해서 날리려했는데 일단 ..... 안됌지금
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다!");
        }

        // 3: 결과 페이지로 Redirect!
        return "redirect:/manage_news";
    }
}
