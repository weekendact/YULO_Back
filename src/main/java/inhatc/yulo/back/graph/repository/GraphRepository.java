package inhatc.yulo.back.graph.repository;

import inhatc.yulo.back.graph.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends JpaRepository<Graph, Long> {
}
