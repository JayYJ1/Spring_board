package iducs.springboot.jyjboard.service;

import iducs.springboot.jyjboard.domain.Memo;
import iducs.springboot.jyjboard.entity.MemoEntity;
import iducs.springboot.jyjboard.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemoServiceImpl implements MemoService {
    final MemoRepository memoRepository;
    // Injection using Constructor vs. @Autowired
    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public void create(Memo memo) {
        // DTO(Controller -> Service) -> Entity (Service -> Repository 에서 동작)
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();
        // new MemoEntity(mno, memoText);
        memoRepository.save(entity);
    }

    @Override
    public Memo readById(Long mno) {
        Memo memo = null;
        Optional<MemoEntity> result = memoRepository.findById(mno);
        if(result.isPresent()) {
            memo = Memo.builder()
                    .mno(result.get().getMno())
                    .memoText(result.get().getMemoText())
                    .build();
        }
        return memo;
    }

    @Override
    public List<Memo> readAll() {
        return null;
    }

    @Override
    public void update(Memo memo) {
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();
        memoRepository.save(entity);
    }

    @Override
    public void delete(Memo memo) {

    }
}
