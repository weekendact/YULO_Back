package inhatc.yulo.back.board.repository;

import inhatc.yulo.back.board.entity.Comment;
import inhatc.yulo.back.board.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
