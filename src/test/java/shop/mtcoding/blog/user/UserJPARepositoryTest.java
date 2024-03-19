package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void findByUsernameAndPassword_test() {
        // given
        String username = "ssar";
        String password = "1234";

        // when
        Optional<User> userOP = userJPARepository.findByUsernameAndPassword(username, password);
        // then
        System.out.println("findByUsernameAndPassword_test: " + userOP.get().getId());
    }

    @Test
    public void findByUsername_test() {
        // given
        String username = "cos";

        // when
        Optional<User> userOP = userJPARepository.findByUsername(username);

        // then
        Assertions.assertThat(userOP.get().getEmail()).isEqualTo("cos@nate.com");

    }
}
