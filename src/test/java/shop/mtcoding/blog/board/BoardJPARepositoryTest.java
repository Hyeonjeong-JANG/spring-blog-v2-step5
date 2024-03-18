package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
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

    @Test
    public void findById_test() {
        // given
        int id = 7;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id); // 옵셔널을 쓰면 널처리가 됨
        if (boardOP.isPresent()) {
            Board board = boardOP.get(); //Optional.get() 메서드는 Optional 객체 안에 포함된 값을 반환합니다. 즉, Optional 객체가 비어있지 않을 때에만 호출할 수 있습니다. 만약 Optional 객체가 비어있는 경우에는 NoSuchElementException이 발생하므로, get() 메서드를 호출하기 전에 isPresent() 메서드로 옵셔널 객체에 값이 있는지 확인하는 것이 좋습니다.
            System.out.println("findById_test: " + board.getTitle());
        }

        // then


    }

    @Test
    public void save_test() {
        // given
        User sessinUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessinUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        System.out.println("save_test_board: :" + board);
        System.out.println("save_test_board: :" + board);
        System.out.println("save_test_board_user_id: :" + board.getUser().getId());

    }

    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;


        // when
        Optional<Board> board = boardJPARepository.findByIdJoinUser(id);

        // then
        System.out.println("findByIdJoinUser_test: " + board);

    }

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
