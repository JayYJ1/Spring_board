package iducs.springboot.jyjboard;

import iducs.springboot.jyjboard.domain.Board;
import iducs.springboot.jyjboard.domain.PageRequestDTO;
import iducs.springboot.jyjboard.domain.PageResultDTO;
import iducs.springboot.jyjboard.entity.BoardEntity;
import iducs.springboot.jyjboard.repository.BoardRepository;
import iducs.springboot.jyjboard.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Test
    public void testRegister() {
        IntStream.rangeClosed(1, 47).forEach(i -> {
            Long seqLong = Long.valueOf(new Random().nextInt(50));
            seqLong = (seqLong == 0) ? 1: seqLong;
            Board dto = Board.builder()
                    .title("title" + i)
                    .content("content...")
                    .writerSeq(seqLong) //member entity seq에 값이 존재해야 함
                    .build();
            Long bno = boardService.register(dto);
        });
    }

    // @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(3); //현재페이지를 설정 1->3 설정
        pageRequestDTO.setSize(4); //47- 8 = 39
        //pageRequestDTO.setPage(3); //현재 페이지를 3 페이지로 이동 findAll(pageable)
        PageResultDTO<Board, Object[]> result = boardService.getList(pageRequestDTO);
        for(Board dto : result.getDtoList())
            System.out.println(dto.getBno() + " : " + dto.getTitle());
    }

   // @Transactional
   // @Test
    public void testLazyloading(){
        Optional<BoardEntity> result = boardRepository.findById(10L);
        BoardEntity boardEntity = result.get();
        System.out.println(boardEntity.getTitle());
        System.out.println(boardEntity.getContent());
        System.out.println(boardEntity.getWriter());
    }

    //@Test
    public void testDeleteWithRepliesById(){
        Long bno = 3L;
        boardService.deleteWithRepliesById(bno);
    } //12 21 37
}
