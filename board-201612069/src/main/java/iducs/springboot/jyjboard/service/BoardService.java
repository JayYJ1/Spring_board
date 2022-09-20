package iducs.springboot.jyjboard.service;

import iducs.springboot.jyjboard.domain.Board;
import iducs.springboot.jyjboard.domain.PageRequestDTO;
import iducs.springboot.jyjboard.domain.PageResultDTO;
import iducs.springboot.jyjboard.entity.BoardEntity;
import iducs.springboot.jyjboard.entity.MemberEntity;

public interface BoardService {
    Long register(Board dto); //Board : DTO or Domain, create
    PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO); //read List
    Board getById(Long bno);
    //Long modify(Board dto);
    void modify(Board board);
    //void deleteWithRepliesById(Long bno); //리플라이를 가진 보드를 찾아 델리트 하겠다.
    void delete(Board board);
    default BoardEntity dtoToEntity(Board dto){ // interface default 메소드
        MemberEntity member = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();
        BoardEntity boardEntity = BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member) //Member Entity에 Seq에 해당되는 값이 넘어간다 그것을 위해 정의했음.
                .build();

        return boardEntity;
    }

    default Board entityToDto(BoardEntity entity, MemberEntity member, Long replyCount){ // interface default 메소드
        Board dto = Board.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerSeq(member.getSeq())
                .writerId(member.getId())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModeDate())
                .replyCount(replyCount.intValue())
                .build();

        return dto;
    }

}
