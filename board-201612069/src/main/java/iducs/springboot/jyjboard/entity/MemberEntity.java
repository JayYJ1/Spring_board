package iducs.springboot.jyjboard.entity;

import lombok.*;

import javax.persistence.*;

// Spring Data 관련 Annotations
@Entity
@Table(name = "tb_member201612069")

// Lombok 관련 Annotations
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor //모든 매개변수를 갖는 생성자
@AllArgsConstructor //디폴트 생성자 ( 아무 매개변수가 없다)
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY : MySQL, MariaDB Auto Increment
    private Long seq;
    @Column(length = 30, nullable = false)
    private String id;
    @Column(length = 30, nullable = false)
    private String pw;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 30, nullable = true)
    private String phone;
    @Column(length = 100)
    private String address;
}
