package iducs.springboot.jyjboard.entity;

import lombok.*;

import javax.persistence.*;

//게시글
@Entity
@Table(name="tb_board201612069")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    // 게시글은 여러개 있을 수 있으며 게시글당 저자는 1명 (저자1명 게시글은 여러개일 수 있다)
    private MemberEntity writer; //연관관계 지정 -> 한 작성자가 여러개의 게시글을 생성할 수 있다. N : 1 관계성 연결 : Left Join

    public void changeTitle(String title){
        this.title = title;
    }
    public void changeContent(String content){
        this.content = content;
    }
}
