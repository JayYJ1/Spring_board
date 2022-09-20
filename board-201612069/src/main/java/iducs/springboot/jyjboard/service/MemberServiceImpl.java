package iducs.springboot.jyjboard.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import iducs.springboot.jyjboard.domain.Member;
import iducs.springboot.jyjboard.domain.PageRequestDTO;
import iducs.springboot.jyjboard.domain.PageResultDTO;
import iducs.springboot.jyjboard.entity.MemberEntity;
import iducs.springboot.jyjboard.entity.QMemberEntity;
import iducs.springboot.jyjboard.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자 주입
        this.memberRepository = memberRepository;
    }

    private MemberEntity dtoToEntity(Member member) {
        MemberEntity memberEntity =  MemberEntity.builder()
                .seq(member.getSeq())
                .id(member.getId())
                .pw(member.getPw())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();
        return memberEntity;
    }

    private Member entityToDto(MemberEntity entity) {
        Member member = Member.builder()
                .seq(entity.getSeq())
                .id(entity.getId())
                .pw(entity.getPw())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();

        return member;

    }
    @Override
    public void create(Member member) {
        MemberEntity entity = dtoToEntity(member);
        memberRepository.save(entity);
    }

    @Override
    public Member readById(Long seq) {
        Member member = null;
        MemberEntity result = memberRepository.getById(seq);
        member = entityToDto(result);

//         Optional<MemberEntity> result = memberRepository.findById(seq);
//         if(result.isPresent()){
//           member = entityToDto(result.get());
//         }

        return member;
    }

    @Override
    public List<Member> readAll() {
        List<Member> members = new ArrayList<>(); //반환 리스트 객체
        //JpaRepository 구현체의 메소드 findAll
        List<MemberEntity> entities = memberRepository.findAll(); //entity들
        for(MemberEntity entity : entities){
            Member member = entityToDto(entity);
            members.add(member);
        }
        return members;
    }

    @Override
    public void update(Member member) {
        MemberEntity entity = dtoToEntity(member);
        memberRepository.save(entity);
    }

    @Override
    public void delete(Member member) {
        MemberEntity entity = dtoToEntity(member);
        //memberRepository.delete(entity);
        memberRepository.deleteById(entity.getSeq());
    }

    @Override
    public Member readByName(Member member) {
        return null;
    }

    @Override
    public Member readByEmail(String email) {
        return null;
    }

    @Override
    public PageResultDTO<Member, MemberEntity> readListBy(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("seq").descending());
        //이부분을 참고하라? if문 이용..
         BooleanBuilder booleanBuilder = findByCondition(pageRequestDTO);
         Page<MemberEntity> result = memberRepository.findAll(booleanBuilder, pageable);

      //  Page<MemberEntity> result = memberRepository.findAll(pageable);
        Function<MemberEntity, Member> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Member loginByEmail(Member member) {
        Member memberDTO = null;
        Object result = memberRepository.getMemberByEmail(member.getEmail(), member.getPw());
        if(result != null){
            memberDTO = entityToDto((MemberEntity) result);
        }
        return memberDTO;
    }

    private BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;
        BooleanExpression expression = qMemberEntity.seq.gt(0L); // where seq > 0 and title == "ti"
        booleanBuilder.and(expression); // where seq > 0
//      booleanBuilder.and(expression1); // where seq > 0 and name like 'name'

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }
        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("e")) // email로 검색
            conditionBuilder.or(qMemberEntity.email.contains(keyword));
        if(type.contains("p")) // phone로 검색
            conditionBuilder.or(qMemberEntity.phone.contains(keyword));
        if(type.contains("a")) // address로 검색
            conditionBuilder.or(qMemberEntity.address.contains(keyword));
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder; // 완성된 조건 or 술어(predicate)
    }

}
