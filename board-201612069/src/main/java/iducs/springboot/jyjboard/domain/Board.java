package iducs.springboot.jyjboard.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
//Board Entity, Member Entity, Reply Entity로 만들 수 있음
public class Board {
    private Long bno; // <- BoardEntity
    private String title; // <- BoardEntity
    private String content; // <- BoardEntity

    private Long writerSeq; // <- MemberEntity
    private String writerId; // <- MemberEntity
    private String writerName; // <- MemberEntity
    private String writerEmail; // <- MemberEntity

    private LocalDateTime regDate; // <- BaseEntity <- 게시글 등록일자 매핑에 따라 달라진다.(Base Entity)
    private LocalDateTime modDate; // <- BaseEntity <- 게시글 수정일자

    private int replyCount; // 추가적인 필드 : 게시물의 댓글 수
}
