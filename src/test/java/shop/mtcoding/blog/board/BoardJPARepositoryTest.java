package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {
    private BoardJPARepository boardJPARepository;

    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findByIdJoinUser(id);

            System.out.println("findByIdJoinUser_test: " + boardOP);
        // then

    }
}
