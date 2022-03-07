package com.koreait.cgvproject.controller.admin.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {



//    @GetMapping("text-basic")
//    public String textBasic(Model model){
//        model.addAttribute("data","Hello spring 타임리프");
//        return  "admin/basic/text-basic";
//    }

    @GetMapping("admin-login")
    public ModelAndView login(){
    return new ModelAndView("/admin/login");
}


    @GetMapping("admin-main")
    public ModelAndView main(){
        return new ModelAndView("/admin/main");
    }


}
