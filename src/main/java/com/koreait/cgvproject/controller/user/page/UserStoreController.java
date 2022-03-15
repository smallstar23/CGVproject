package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.GiftDTO;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.service.KakaopayGiftService;
import com.koreait.cgvproject.service.user.store.UserStoreService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/culture-event")
@Slf4j
public class UserStoreController {

    @Setter(onMethod_ = @Autowired)
    private KakaopayGiftService kakaopayGiftService;

    @Autowired
    private UserStoreService userStoreService;

    private MemberRepository memberRepository;

    private final String ROOT = "user/culture-event/popcorn-store";

    @GetMapping("/popcorn-store")
    public String main(Model model){
        Map<String, List<GiftDTO>> MDLMap = userStoreService.getMainDTOList();
        model.addAttribute("movieDTOList", MDLMap.get("movie"))
                .addAttribute("comboDTOList", MDLMap.get("combo"))
                .addAttribute("popcornDTOList", MDLMap.get("popcorn"))
                .addAttribute("drinkDTOList", MDLMap.get("drink"))
                .addAttribute("snackDTOList", MDLMap.get("snack"));

        return ROOT;
    }

    @GetMapping("/popcorn-store/store-category")
    public String category(@RequestParam(value = "cno", required = false, defaultValue = "1") String cno, Model model){
        if(cno.equals("1")) cno = "2";
        List<GiftDTO> giftDTOList = userStoreService.getGiftDTOList(cno);
        model.addAttribute("giftDTOList", giftDTOList);
        return ROOT+"/store-category";
    }

    @GetMapping("/popcorn-store/product-detail")
    public String detail(@RequestParam(value = "gcode", required = false) Long gcode, Model model){
        if(gcode == null) return "user/culture-event/no-gift";
        GiftDTO selectDTO = userStoreService.getGiftDTO(gcode);
        model.addAttribute("giftDTO", selectDTO);
        return ROOT + "/product-detail";
    }

    @GetMapping("/popcorn-store/purchase-confirm")
    public String directPay(@RequestParam(value = "gcode", required = false) Long gcode, @RequestParam(value = "totalCount") String total, Model model){
        if(gcode == null) return "user/culture-event/no-gift";
//        log.info(String.valueOf(total));
        GiftDTO selectDTO = userStoreService.getGiftDTO(gcode);
        model.addAttribute("giftDTO", selectDTO);



        return ROOT + "/purchase-confirm";
    }

    @GetMapping("/kakaoPay/store")
    public String kakaoPay(@RequestParam(value = "title") String title) {
        return "redirect:" + kakaopayGiftService.kakaoPayReady(title);
    }

    @GetMapping("/popcorn-store/payment-successcomplete")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpServletRequest request) throws Exception{
        log.info("kakaoPaySuccess pg_token : " + pg_token);


//        log.info(giftPayment.toString());
//        HttpSession session = request.getSession();
//        String userid = (String) session.getAttribute("userid");
//
//        log.info(userid);
//        Member member = memberRepository.findByUserid(userid);
//        log.info(member.toString());

        // 세션으로 처리할꺼임
//        GiftPaymentDTO giftPaymentDTO = new GiftPaymentDTO();
//
//
//        giftPaymentDTO.setGcode(4);
//        giftPaymentDTO.setStatus("미사용");
//        giftPaymentDTO.setRegDate(LocalDateTime.now());
//        giftPaymentDTO.setGpcode(10L);
//        giftPaymentDTO.setMemIdx(member.getIdx());
//
//        GiftPaymentDTO save = giftPaymentDTORepository.save(giftPaymentDTO);


        model.addAttribute("info", kakaopayGiftService.kakaoPayInfo(pg_token));

        return "user/culture-event/popcorn-store/payment-successcomplete";

    }

    @GetMapping("/popcorn-store/user-cart")
    public String userCart(){
        return ROOT + "/user-cart";
    }

    @GetMapping("/popcorn-store/user-gift")
    public String userGift(){
        return ROOT + "/user-gift";
    }

    @GetMapping("/popcorn-store/store-payment")
    public String giftPay(){
        return ROOT + "/store-payment";
    }

    @GetMapping("/popcorn-store/payment-failcomplete")
    public String fail(){
        return ROOT + "/payment-failcomplete";
    }
}
