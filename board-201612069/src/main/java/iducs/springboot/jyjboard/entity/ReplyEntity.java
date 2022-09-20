package iducs.springboot.jyjboard.entity;

import lombok.*;

import javax.persistence.*;

//댓글
// Spring Data 관련 Annotations
@Entity
@Table(name="tb_reply201612069")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class ReplyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;
    private String replier;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board; //연관관계 지정 -> 게시글 하나에 댓글이 여러개가 될 수 있다.
}