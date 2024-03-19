package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void deleteById_test() {
        //given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);

        // then
        em.flush();

    }

    @Test
    public void findAll_test() {
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // when
        List<Board> boardList = boardJPARepository.findAll(sort);

        // then
        System.out.println("findAll_test: " + boardList.size());

    }

    @Test
    public void findById_test() {
        //given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        // then
        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            System.out.println("findById_test: " + board);
        }

    }

    @Test
    public void save_test() {
        //given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("title5")
                .content("content5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        System.out.println("save_test: " + board);

    }

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
