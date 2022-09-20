package iducs.springboot.jyjboard.domain;

import lombok.*;

@Data // @Getter, @Setter, @EqualsAndHash, @RequiredArgsConstructor 가 모두 내포
@Builder
//Client -> Controller -> Service 에서 교환됨
//Client <- (view | data) <- Controller <- Service //view가 없으면 rest api이다.
//리팩토링이란 프로젝트 전체 구조를 일괄 변경함으로써 구조나 형태를 지켜줄 수 있고 유지보수를 향상시켜주는 것을 의미한다.
public class Member { // DTO(data transfer object) Client ↔ Controller ↔ service
    private Long seq;
    private String id;
    private String name;
    private String pw;
    private String email;
    private String phone;
    private String address;
    //숫자, 불리언, Date : 날짜 시간 관련
}
