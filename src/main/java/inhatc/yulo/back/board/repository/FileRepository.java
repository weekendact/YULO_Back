package inhatc.yulo.back.board.repository;

import inhatc.yulo.back.board.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByBoardId(Long boardId);
    List<File> findByNoticeId(Long noticeId);

    Optional<File> findByOrigFilename(String origFilename);
}
