package inhatc.yulo.back.board.repository;

import inhatc.yulo.back.board.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
