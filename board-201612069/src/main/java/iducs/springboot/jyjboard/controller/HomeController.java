package iducs.springboot.jyjboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RestController // 자원의 상태(응답)을 Client에게 전달
// Restful (Reprenstational Satate)
@RequestMapping
public class HomeController {
    @GetMapping("")
    public String getHome(){
        return "index";
    }
    @GetMapping("members/simple") // url - loacalhost:8888/members/simple
    public  String getSimple(){
        return "members"; // resources/templates/members/members.html
    }
}
