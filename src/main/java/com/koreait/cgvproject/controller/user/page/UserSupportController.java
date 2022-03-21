package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.entity.Notification;
import com.koreait.cgvproject.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class UserSupportController {

    private Long x;
    private final String ROOT = "user/support";

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/support")
    public String support(){
        return ROOT + "/support";
    }

    @GetMapping("/support/faq/support-faq-detail-view")
    public String support_faq_detail_view(){
        return  ROOT + "/faq/support-faq-detail-view";
    }

    @GetMapping("/support/support-faq")
    public String support_faq(){
        return ROOT + "/faq/support-faq";
    }

    @GetMapping("/support/support-lease")
    public String support_lease(){
        return ROOT + "/lease/support-lease";
    }

    @GetMapping("/support/support-lost")
    public String support_lost(){
        return ROOT + "/lost/support-lost";
    }

    @GetMapping("/support/support-news")
    public String support_news(@PageableDefault(size = 1, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable, Model model){

        // 페이지
        Page<Notification> notifications = notificationRepository.findAll(pageable);
        boolean prev = notifications.hasPrevious();
        boolean next = notifications.hasNext();
        int totalPage = notifications.getTotalPages();
        int nowPage = notifications.getNumber();
        int size = notifications.getSize();


//        // 공지 리스트 가져오기
//        List<Notification> notificationList = notificationRepository.findAll();
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("pageNext",next);
        model.addAttribute("pagePrev",prev);
        model.addAttribute("newsList",notifications);
        model.addAttribute("size",size);

        return ROOT + "/news/support-news";
    }

    @GetMapping("support/news/support-news-detail-view/{id}")
    public String support_news_detail_view(@PathVariable Long id,Model model){

        // 1. ID로 데이터를 가져옴! (1~200) 가져옴
        Notification notification_user = notificationRepository.findById(id).orElse(null);

        // 1-1. 조회수 증가
        notification_user.setHit(notification_user.getHit() + 1);

        Notification updatehit = notificationRepository.save(notification_user);

        Notification realnotification_user = notificationRepository.findById(id).orElse(null);

        model.addAttribute("supportnews", realnotification_user);
//        log.info(realnotification_user.toString());
        // 1-2. 이전 글, 다음 글

        Notification notificationprev_user = notificationRepository.findById(id - 1L).orElse(null);
        Notification notificationnext_user = notificationRepository.findById(id + 1L).orElse(null);
        Notification notification = new Notification();
        notification.setRegDate(null);
        notification.setTitle("글이 존재하지 않습니다.");

//        log.info(realnotification_user.toString());
//        log.info(notification.toString());

        // 2. 가져온 데이터를 모델에 등록


        if(notificationprev_user == null){
            model.addAttribute("supportnews_prev", notification);
        } else if(notificationprev_user != null){
            model.addAttribute("supportnews_prev", notificationprev_user);
        }
        if(notificationnext_user == null){
            model.addAttribute("supportnews_next", notification);
        } else if(notificationnext_user != null){
            model.addAttribute("supportnews_next",notificationnext_user);
        }

        return ROOT + "/news/support-news-detail-view";
    }

    @GetMapping("/support/support-qna")
    public String support_qna(){
        return ROOT + "/qna/support-qna";
    }
}
