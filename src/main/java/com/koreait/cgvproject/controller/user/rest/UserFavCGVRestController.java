package com.koreait.cgvproject.controller.user.rest;

import com.koreait.cgvproject.dto.FavCGVDTO;
import com.koreait.cgvproject.entity.FavCGV;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.repository.FavCGVRepository;
import com.koreait.cgvproject.service.user.mycgv.UserFavCGVService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserFavCGVRestController {
    private UserFavCGVService userFavCGVService;
    private HttpSession session;
    private FavCGVRepository favCGVRepository;

    @GetMapping("api/favCGV/delete")
    public void favDelete(@RequestParam String tname){
        session.removeAttribute("favCGV");
        userFavCGVService.delete((Long) session.getAttribute("idx"), tname);
    }

    @GetMapping("api/favCGV/common/{member}")
    public String listFavCGV(Model model, @PathVariable("member") Long member){
        System.out.println(session.getAttribute("idx"));
        List<FavCGVDTO> favCGVDTOS =new ArrayList<>();
        List<FavCGV> favCGVList = favCGVRepository.findAllByMemIdx(member);
        System.out.println(favCGVList);
        for(FavCGV favCGV : favCGVList){
            favCGVDTOS.add(favCGV.toDTO());
        }
        model.addAttribute("favCGV",favCGVDTOS);
        System.out.println(favCGVDTOS);
        return "/user/mycgv/fragment/common";
    }
}
