package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardJPARepository extends JpaRepository<Board, Integer> { //  Board는 엔티티 클래스, Integer는 이 엔티티의 기본 키(primary key)의 데이터 타입
    @Query("select b from Board b join fetch b.user u where b.id=:id")
    Board findByIdJoinUser(@Param("id") int id);
}
