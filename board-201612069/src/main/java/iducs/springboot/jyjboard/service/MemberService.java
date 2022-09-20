package iducs.springboot.jyjboard.service;

import iducs.springboot.jyjboard.domain.Member;
import iducs.springboot.jyjboard.domain.PageRequestDTO;
import iducs.springboot.jyjboard.domain.PageResultDTO;
import iducs.springboot.jyjboard.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    void create(Member member);
    Member readById(Long seq);
    List<Member> readAll();
    void update(Member member);
    void delete(Member member);

    Member readByName(Member member);
    Member readByEmail(String email);

    PageResultDTO<Member, MemberEntity> readListBy(PageRequestDTO pageRequestDTO);

    Member loginByEmail(Member member);
}
