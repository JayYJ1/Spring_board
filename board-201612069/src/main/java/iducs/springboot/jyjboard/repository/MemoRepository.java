package iducs.springboot.jyjboard.repository;

import iducs.springboot.jyjboard.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
    // Spring Data JPA 활용하여서 데이터 액세스를 담당하는 객체를 생성하는데 활용
}
