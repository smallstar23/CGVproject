package com.koreait.cgvproject.controller.user.page;

import com.koreait.cgvproject.dto.GiftDTO;
import com.koreait.cgvproject.entity.Gift;
import com.koreait.cgvproject.entity.GiftPayment;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.GiftPaymentRepository;
import com.koreait.cgvproject.repository.GiftRepository;
import com.koreait.cgvproject.repository.MemberRepository;
import com.koreait.cgvproject.service.KakaopayGiftService;
import com.koreait.cgvproject.service.user.store.UserStoreService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/culture-event")
@Slf4j
@AllArgsConstructor
public class UserStoreController {

    @Setter(onMethod_ = @Autowired)
    private KakaopayGiftService kakaopayGiftService;

    private UserStoreService userStoreService;
    private HttpSession session;
    private MemberRepository memberRepository;
    private GiftPaymentRepository giftPaymentRepository;
    private GiftRepository giftRepository;

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

        session.setAttribute("price", selectDTO.getPrice());
        session.setAttribute("title", selectDTO.getTitle());
        session.setAttribute("gcode", selectDTO.getGcode());


        return ROOT + "/purchase-confirm";
    }

    @GetMapping("/kakaoPay/store")
    public String kakaoPay(@RequestParam(value = "title") String title) {
        return "redirect:" + kakaopayGiftService.kakaoPayReady(title);
    }

    @GetMapping("/popcorn-store/payment-successcomplete")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpServletRequest request) throws Exception{
//        log.info("kakaoPaySuccess pg_token : " + pg_token);

        HttpSession session = request.getSession();
        String memberid = (String) session.getAttribute("userid");
        String price = (String) session.getAttribute("price");
        String title = (String) session.getAttribute("title");
        Long gcode = (Long) session.getAttribute("gcode");

        Member member =memberRepository.findByUserid(memberid);
        Gift gift =giftRepository.findById(gcode).orElse(null);

        GiftPayment giftpayment111 = new GiftPayment();
        giftpayment111.setGift(gift);
        giftpayment111.setMember(member);
        giftpayment111.setStatus("미사용");
        giftpayment111.setRegDate(LocalDateTime.now());

        GiftPayment giftPayment = giftPaymentRepository.save(giftpayment111);

        model.addAttribute("gpcode",giftpayment111);
        model.addAttribute("info", kakaopayGiftService.kakaoPayInfo(pg_token));
        session.removeAttribute("price");
        System.out.println(price);

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