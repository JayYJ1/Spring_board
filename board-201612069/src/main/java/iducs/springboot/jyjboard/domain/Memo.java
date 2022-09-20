package iducs.springboot.jyjboard.domain;


import lombok.Builder;
import lombok.Data;

@Data // @Getter, @Setter, @EqualsAndHash, @RequiredArgsConstructor 가 모두 내포
@Builder // builder.build() 로 객체를 생성, 초기화 할 수 있도록 함
public class Memo {
    private Long mno;
    private String memoText;
}
