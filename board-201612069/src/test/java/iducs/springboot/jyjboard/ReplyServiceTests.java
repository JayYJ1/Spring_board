package iducs.springboot.jyjboard;

import iducs.springboot.jyjboard.entity.BoardEntity;
import iducs.springboot.jyjboard.entity.ReplyEntity;
import iducs.springboot.jyjboard.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyServiceTests {
    @Autowired
    ReplyRepository replyRepository;

    //@Transactional
    @Test
    public void testReplyInt(){
        IntStream.rangeClosed(1,10).forEach(i ->{
            Long seqLong = Long.valueOf(new Random().nextInt(10));
            seqLong = (seqLong==0) ? 1: seqLong;
            BoardEntity boardEntity = BoardEntity.builder()
                    .bno(seqLong)
                    .build();
            ReplyEntity replyEntity = ReplyEntity.builder()
                    .text("title" + i)
                    .replier("Content...")
                    .board(boardEntity)
                    .build();
            replyRepository.save(replyEntity);
        });
    }
}

