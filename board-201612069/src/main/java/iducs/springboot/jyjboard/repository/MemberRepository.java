package iducs.springboot.jyjboard.repository;

import iducs.springboot.jyjboard.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends
        JpaRepository<MemberEntity, Long>, QuerydslPredicateExecutor<MemberEntity> {
    @Query("select m from MemberEntity  m where m.email = :email and m.pw = :pw")
    Object getMemberByEmail(@Param("email")String email, @Param("pw")String pw);
    // CRUD가 모두 구현되어 있음
    // JDBC 프로그래밍 순서
    // 사용할 자원 선언(Connection 선언, Statement 선언, ResultSet 선언)
    // Connection 생성 - 연결 설정 (application.properties)
    // Statement 생성 (Statement, PreparedStatement, CallableStatement)
    // ResultSet 생성 (entity - entities : records)
    // ResultSet -> DTO로 변환후 반환 / 영향받은 record 수를 반환
    // (List<DTO> - readList, DTO - readOne, int (row 수) - create, update, delete)
}
