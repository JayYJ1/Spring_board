package iducs.springboot.jyjboard.controller;

import iducs.springboot.jyjboard.domain.Board;
import iducs.springboot.jyjboard.domain.Member;
import iducs.springboot.jyjboard.domain.PageRequestDTO;
import iducs.springboot.jyjboard.service.BoardService;
import iducs.springboot.jyjboard.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {
    public final BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    } //생성자 주입

    @GetMapping("/regform") //board의 regform -> 페이지 이동
    public String getRegform(Model model){
        //new로 Board(); 과 동일 -> @Builder Annotation
        model.addAttribute("dto", Board.builder().build()); //Board 객체를 생성, Empty
        return "/boards/regform";
    }

    @PostMapping("")
    public String postBoard(@ModelAttribute("dto") Board board){
        boardService.register(board);
        return "redirect:/boards"; //다시 Get방식으로 해당 URI로 요청 -> /boards 호출
    }

    @GetMapping("")
    public String getBoards(PageRequestDTO pageRequestDTO, Model model) {
        // ?page=nn&size=mm
        //만약 mm = 5페이지 단위로 nn = 3페이지를 접근
        // new PageRequest(3, 5) : 생성자를 호출하여 초기화
        // 11 ~ 15 번째의 레코드들을 접근함
        // pageRequestDTO.builder().build() or new PageRequestDTO()가 pageRequest..
        model.addAttribute("list", boardService.getList(pageRequestDTO));
        return "/boards/list";
    }

    @GetMapping("/{bno}")
    public String getBoard(@PathVariable("bno") Long bno, Model model) {
        model.addAttribute("dto", boardService.getById(bno));
        return "/boards/read";
    }

    @GetMapping("/{bno}/upform")
    public String getUpform(@PathVariable("bno") Long seq, Model model){
        // 정보를 전달받을 객체를 보냄
        Board board = boardService.getById(seq);
        //model.addAttribute("board", Board.builder().build());
        model.addAttribute("board", board);
      //  System.out.println("");
        return "/boards/upform";
    }

    @PutMapping("/{bno}")
    public String putBoard(@ModelAttribute("board") Board board, Model model) {
        //html에서 model 객체를 전달받음 : member(애트리뷰트 명, th:object 이름)
        boardService.modify(board);
        model.addAttribute(board);
        return "redirect:/boards";//View resolving : updated info 확인
    }

    @GetMapping("/{bno}/delform")
    public String getDelform(@PathVariable("bno") Long seq, Model model){
        // 정보를 전달받을 객체를 보냄
        Board board = boardService.getById(seq);
        //model.addAttribute("board", Board.builder().build());
        model.addAttribute("board", board);
        //  System.out.println("");
        return "/boards/delform";
    }

    @DeleteMapping("/{bno}")
    public String deleteBoard(@ModelAttribute("board") Board board, Model model) {
        //html에서 model 객체를 전달받음 : member(애트리뷰트 명, th:object 이름)
        //boardService.deleteWithRepliesById(board.getBno());
        boardService.delete(board);
        model.addAttribute(board);
        return "redirect:/boards";//View resolving : updated info 확인
    }
}
