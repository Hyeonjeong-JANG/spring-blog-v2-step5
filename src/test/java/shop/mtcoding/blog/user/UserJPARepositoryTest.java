package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void findAll_test() {
        // given


        // when
        List<User> userList = userJPARepository.findAll();

        // then
        Assertions.assertThat(userList.size()).isEqualTo(3);
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Optional<User> userOptional = userJPARepository.findById(id);

        // then
        System.out.println("findById_test: " + userOptional);
    }

    @Test
    public void save_test() {
        // given
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();

        // when

        // then
        System.out.println("save_test: " + user);
    }

    @Test
    public void findByUsernameAndPassword_test() {
        // given
        String username = "ssar";
        String password = "1234";

        // when
       Optional<User> userOptional = userJPARepository.findByUsernameAndPassword(username, password);

        // then
        System.out.println("findByUsernameAndPassword_test: " + userOptional);
    }
}
