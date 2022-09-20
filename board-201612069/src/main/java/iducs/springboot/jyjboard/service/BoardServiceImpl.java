package iducs.springboot.jyjboard.service;

import iducs.springboot.jyjboard.domain.Board;
import iducs.springboot.jyjboard.domain.PageRequestDTO;
import iducs.springboot.jyjboard.domain.PageResultDTO;
import iducs.springboot.jyjboard.entity.BoardEntity;
import iducs.springboot.jyjboard.entity.MemberEntity;
import iducs.springboot.jyjboard.repository.BoardRepository;
import iducs.springboot.jyjboard.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    public BoardServiceImpl(BoardRepository boardRepository, ReplyRepository replyRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public Long register(Board dto){
        log.info("board register : " + dto);
        BoardEntity boardEntity = dtoToEntity(dto);
        boardRepository.save(boardEntity);
        return boardEntity.getBno();
    }

    @Override
    public PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(">>>>>" + pageRequestDTO);
        // entities - Object[], dto - Board
        Function<Object[], Board> fn =
                (entity -> entityToDto((BoardEntity) entity[0],
                        (MemberEntity) entity[1], (Long) entity[2]));
        Page<Object[]> result =
                boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        return new PageResultDTO<>(result, fn);


    }

    @Override
    public Board getById(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] en = (Object[]) result;
        return entityToDto((BoardEntity) en[0],
                (MemberEntity) en[1], (Long) en[2]); //BoardDTO
    }

//    @Override
//    public Long modify(Board dto) {
//        Optional<BoardEntity> result = boardRepository.findById(dto.getBno());
//        BoardEntity boardEntity = null;
//        if(result.isPresent()) {
//            boardEntity = (BoardEntity) result.get();
//            boardEntity.changeTitle(dto.getTitle());
//            boardEntity.changeContent(dto.getContent());
//            boardRepository.save(boardEntity);
//        }
//        return boardEntity.getBno();
//    }
    @Override
    public void modify (Board board) {
        BoardEntity entity = dtoToEntity(board);
        boardRepository.save(entity);
    }

//    @Transactional
//    @Override
//    public void deleteWithRepliesById(Long bno) {
//        replyRepository.deleteByBno(bno);
//        boardRepository.deleteById(bno); //board 레코드를 삭제
//    }
    @Override
    public void delete (Board board) {
        BoardEntity entity = dtoToEntity(board);
        boardRepository.deleteById(entity.getBno());
    }
}
/*log.info(">>>>>" + pageRequestDTO);
        Function<Object[], Board> fn =
        (entity -> entityToDto((BoardEntity) entity[0],
        (MemberEntity) entity[1], (Long) entity[2]));
        Page<Object[]> result =
        boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        return new PageResultDTO<>(result, fn);
*/
