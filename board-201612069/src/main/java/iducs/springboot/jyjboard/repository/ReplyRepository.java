package iducs.springboot.jyjboard.repository;

import iducs.springboot.jyjboard.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//JpaRepository의 Generic은 Entity와 Long으로 이뤄져 있다.

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    @Modifying
    @Query("delete from ReplyEntity r where r.board.bno = :bno")
    void deleteByBno(@Param("bno") Long bno);
}
