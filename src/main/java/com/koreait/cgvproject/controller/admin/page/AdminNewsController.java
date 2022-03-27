package com.koreait.cgvproject.controller.admin.page;


import com.koreait.cgvproject.dto.NotificationDTO;
import com.koreait.cgvproject.entity.Notification;
import com.koreait.cgvproject.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class AdminNewsController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("manage_news")//manage_news  페이지
    public  String manage_news(Model model){
        // 1. 모든 공지사항 가져오기
        List<Notification> notificationList = notificationRepository.findAll();

        // 2. 가져온 공지사항 묶음을 뷰로 전달
        model.addAttribute("adminNewsList",notificationList);
        // 3. 뷰 페이지를 설정
        return "/admin/news/manage_news";
    }


    @GetMapping("manage_news_edit/{id}")//manage_news 수정페이지
    public  String manage_news_edit(@PathVariable Long id, Model model){

        // 1. ID로 데이터를 가져옴!
        Notification notification = notificationRepository.findById(id).orElse(null);
//        Optional<Member> memberinfoEntity = memberinfoRepository.findById(id);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("edit", notification);
//        model.addAttribute("managenewsfirst",notification);

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
    public String manage_news_clear(NotificationDTO adminNewsDto){

        log.info(adminNewsDto.toString());

        Notification notification = adminNewsDto.toEntity();
        log.info(notification.toString());

        Notification saved = notificationRepository.save(notification);
        log.info(saved.toString());

        return "redirect:manage_news_detail/" + saved.getIdx();
    }

    @GetMapping("manage_news_detail/{id}")//manage_news 상세페이지
    public  String manage_news_detail(@PathVariable Long id, Model model){

        // 1. ID로 데이터를 가져옴!
        Notification notification = notificationRepository.findById(id).orElse(null);

        log.info(notification.toString());
        // 1-2. 이전 글, 다음 글

        Notification notificationprev = notificationRepository.findById(id - 1L).orElse(null);

        Notification notificationnext = notificationRepository.findById(id + 1L).orElse(null);

        Notification notificationnull = notificationRepository.findById(id).orElse(null);
        notificationnull.setTitle("글이 존재하지 않습니다.");
        notificationnull.setRegDate(null);
//        if(notification != null){
//            model.addAttribute("managenews", notification);
//            if(notificationprev == null){
//                notificationprev.setTitle("글이 존재하지 않습니다.");
//                notificationprev.setRegDate(null);
//                model.addAttribute("managenews_prev", notificationprev);
//            } else if(notificationprev != null){
//                model.addAttribute("managenews_prev", notificationprev);
//            }
//            if(notificationnext == null){
//                notificationnext.setTitle("글이 존재하지 않습니다.");
//                notificationnext.setRegDate(null);
//                model.addAttribute("managenews_next", notificationnext);
//            } else if(notificationnext != null){
//                model.addAttribute("managenews_next",notificationnext);
//            }
//            Notification realnotification = notificationRepository.findById(id).orElse(null);
//            log.info(realnotification.toString());
//            log.info(notification.toString());
//            model.addAttribute("managenews", realnotification);
//        }
////        notificationnull.setTitle("글이 존재하지 않습니다.");
////        notificationnull.setRegDate(null);

         // 2. 가져온 데이터를 모델에 등록
        if(notificationprev == null){
            model.addAttribute("managenews_prev", notificationnull);
        } else if(notificationprev != null){
            model.addAttribute("managenews_prev", notificationprev);
        }
        if(notificationnext == null){
            model.addAttribute("managenews_next", notificationnull);
        } else if(notificationnext != null){
            model.addAttribute("managenews_next",notificationnext);
        }

        model.addAttribute("managenews", notification);
//        log.info(notificationnull.toString());
//        log.info(notification.toString());



        // 3. 보여줄 페이지를 설정!
        return "/admin/news/manage_news_detail";
    }

    // 공지사항 수정 메소드
    @PostMapping("manage_news_edit/manage_news_update")
    public String update(NotificationDTO adminNewsDto){
        // 1: DTO를 Entity로 변환
        Notification notification = adminNewsDto.toEntity();
        // 2: 엔티티를 DB로 저장한다.
        // 2-1: DB에서 기존 데이터를 가져올 것임
        Notification target = notificationRepository.findById(notification.getIdx()).orElse(null);
//        Optional<Notification> target = notificationRepository.findById(notification.getIdx()); -> 옵셔널 잘모르니까 일단 위에껄로 할꺼임

        // 2-2: 기존 데이터가 존재한다면, 값을 갱신한다.
        if(target != null){
            notificationRepository.save(notification); // 엔티티가 db로 갱신됌!
        }

        // 3: 수정 결과 페이지로 리다이렉트
        return "redirect:/manage_news_detail/" + notification.getIdx();
    }

    // 공지사항 글 삭제 메소드
    @GetMapping("manage_news_delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");

        // 1: 삭제 대상을 가져옴
        Notification target = notificationRepository.findById(id).orElse(null);
        // 2: 대상을 삭제
        if(target != null){
            notificationRepository.delete(target);
            // Flash어트리뷰트로 설정해서 날리려했는데 일단 ..... 안됌지금
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다!");
        }

        // 3: 결과 페이지로 Redirect!
        return "redirect:/manage_news";
    }
}
