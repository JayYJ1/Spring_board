package iducs.springboot.jyjboard.controller;

import iducs.springboot.jyjboard.domain.Member;
import iducs.springboot.jyjboard.domain.PageRequestDTO;
import iducs.springboot.jyjboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("regform")
    public String getRegform(Model model) {
        // 정보를 전달받을 객체를 보냄
        model.addAttribute("member", Member.builder().build());
        return "/members/regform";
    }

    @PostMapping("")
    public String postMember(@ModelAttribute("member") Member member, Model model) {
        memberService.create(member);
        model.addAttribute("member", member);
        return "redirect:/members";
    }

    @GetMapping("/{idx}")
    public  String getMember(@PathVariable("idx") Long seq, Model model){
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/member";
    }// '/members/일련번호' - @pathVariable 매핑해서 접근


    @GetMapping("")
    public String getMembers(PageRequestDTO pageRequestDTO, Model model) {
        //PageRequestDTO.builder().build() or new PageRequestDTO()가 PageRequestDTO 매개변수에 배정
        //정보를 전달받을 빈(Empty) 객체를 보냄
        //List<Member> members = memberService.readAll();
        //model.addAttribute("list", memberService);
        model.addAttribute("list", memberService.readListBy(pageRequestDTO));
        return "/members/members"; // view resolving : members.html
        //return "/pages/tables/simple"; // view name
    }

    @GetMapping("/{idx}/upform")
    public String getUpform(@PathVariable("idx") Long seq, Model model){
        // 정보를 전달받을 객체를 보냄
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/upform";
    }
    @PutMapping("/{idx}")
    public String putMember(@ModelAttribute("member") Member member, Model model) {
        //html에서 model 객체를 전달받음 : member(애트리뷰트 명, th:object 이름)
        memberService.update(member);
        model.addAttribute(member);
        return "/members/member";//View resolving : updated info 확인
    }

    @GetMapping("/{idx}/delform")
    public String getdelform(@PathVariable("idx") Long seq, Model model){
        // 정보를 전달받을 객체를 보냄
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/delform";
    }

    @DeleteMapping("/{idx}")
    public String DelMember(@ModelAttribute("member") Member member, Model model) {
        //html에서 model 객체를 전달받음 : member(애트리뷰트 명, th:object 이름)
        memberService.delete(member);
        //model.addAttribute(member);
        return "redirect:/members";//View resolving : updated info 확인
    }

    @GetMapping("/login")
    public String getLoginform(Model model) {
        model.addAttribute("member", Member.builder().build());
        return "/members/login";
    }
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("member") Member member, HttpServletRequest request){
        Member dto = null;
        if((dto = memberService.loginByEmail(member)) != null){
            HttpSession session = request.getSession();
            session.setAttribute("login", dto);
            if(dto.getId().contains("admin"))
                session.setAttribute("isadmin", dto.getId());
            return "redirect:/";
        }
        else
        return "/members/loginfail";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/th")
    public String getThymeleaf() {
        return "thymeleaf";
    }
//
//    @GetMapping({"/", "/index"})
//    public String getIndex(Model model) {
//        List<MemberEntity> list = memberService.readAll();
//        model.addAttribute("members", list);
//        return "index";
//    }
//
//    @GetMapping("/{idx}")
//    public String getMember(@PathVariable("idx") Long seq, Model model) {
//        Optional<MemberEntity> member = memberService.readById(seq);
//        MemberEntity m = member.get();
//        model.addAttribute("member", m);
//        return "charts";
//    }

//    @GetMapping("/buttons")
//    public String getButtons() {
//        return "buttons";
//    }
//
//    @GetMapping("/cards")
//    public String getCards() {
//        return "cards";
//    }
//
//    @GetMapping("/utilities-color")
//    public String getUtilitiesColor() {
//        return "utilities-color";
//    }
//
//    @GetMapping("/utilities-border")
//    public String getUtilitiesBorder() {
//        return "utilities-border";
//    }
//
//    @GetMapping("/utilities-animation")
//    public String getUtilitiesAnimation() {
//        return "utilities-animation";
//    }
//
//    @GetMapping("/utilities-other")
//    public String getUtilitiesOther() {
//        return "utilities-other";
//    }
//
//    @GetMapping("/register")
//    public String getRegister() {
//        return "register";
//    }
//
//    @GetMapping("/forgot-password")
//    public String getForgotPassword() {
//        return "forgot-password";
//    }
//
//    @GetMapping("/404")
//    public String get404() {
//        return "404";
//    }
//
//    @GetMapping("/blank")
//    public String getBlank() {
//        return "blank";
//    }
//
//    @GetMapping("/charts")
//    public String getCharts() {
//        return "charts";
//    }
//
//    @GetMapping("/tables")
//    public String getTables() {
//        return "tables";
//    }
}




