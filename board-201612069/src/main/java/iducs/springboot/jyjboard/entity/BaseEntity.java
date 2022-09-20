package iducs.springboot.jyjboard.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//@Entity
@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
//여러 개의 Entity들 중 공통적인 부분을 가져올만 한 것들을 사용하려고 만듬
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modeDate;

}
