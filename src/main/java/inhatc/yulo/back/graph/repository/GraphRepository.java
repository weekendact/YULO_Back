package inhatc.yulo.back.graph.repository;

import inhatc.yulo.back.graph.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphRepository extends JpaRepository<Graph, Long> {
    @Query("SELECT g " +
            "FROM Graph g " +
            "WHERE g.user.userId = :userId ")
    List<Graph> findGraphListByUserId(Long userId);
}
