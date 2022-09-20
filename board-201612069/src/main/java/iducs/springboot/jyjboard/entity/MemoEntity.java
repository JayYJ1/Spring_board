package iducs.springboot.jyjboard.entity;

import lombok.*;

import javax.persistence.*;

// Spring Data 관련 Annotations
@Entity
@Table(name="tbl_memo")

// Lombok 관련 Annotations
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 50, nullable = false)
    private String memoText;
}

