package inhatc.yulo.back.board.repository;

import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.Heart;
import inhatc.yulo.back.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndBoard(User user, Board board);
}
