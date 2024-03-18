package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.user.User;

import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    // save
    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        System.out.println("save_test: " + board.getId()); // 영속객체가 되었기 때문에 아이디가 있는지 확인해보는 것

    }

    // findById
    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);
        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            System.out.println("findById_test: " + board.getTitle());
        }

        // then

    }

    // findByIdJoinUser
//    @Test
//    public void findByIdJoinUser_test() {
//        // given
//        int id = 1;
//
//        // when
//        Board board = boardJPARepository.findByIdJoinUser(id);
//
//        // then
//        System.out.println(board);
//
//    }
    // findAll(sort)

    // findAll(pageable)

    // deleteById

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);
        em.flush();
        
        // then

    }
}
