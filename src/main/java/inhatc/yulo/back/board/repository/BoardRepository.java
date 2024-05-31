package inhatc.yulo.back.board.repository;

import inhatc.yulo.back.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 제목에 포함된 키워드
    List<Board> findByTitleContaining(String title);

    // 작성자에 포함된 키워드
    List<Board> findByUser_UserNameContaining(String userName);

    boolean existsByIdAndUser_UserId(Long id, Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Board b SET b.heartCount = b.heartCount + 1 WHERE b.id = :boardId")
    void incrementLikeCount(Long boardId);

    @Modifying
    @Transactional
    @Query("UPDATE Board b SET b.heartCount = b.heartCount - 1 WHERE b.id = :boardId")
    void decrementLikeCount(Long boardId);
}