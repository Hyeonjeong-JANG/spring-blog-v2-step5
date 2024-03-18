package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IoC 등록
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public User 회원수정(int id, UserRequest.UpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다.")); // 예외처리
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    } // 더티체킹

    public User 회원수정폼(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다.")); // 예외처리
        return user;
    }

    public User 로그인(UserRequest.LoginDTO reqDTO) {

        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다.")); // orElseThrow: 값이 널이면

        return sessionUser;
    }

    @Transactional // 트랜잭셔널을 JPARepository가 들고 있으면 안 돼!!!
    public void 회원가입(UserRequest.JoinDTO reqDTO) {
        // 1. 유효성 검사(컨트롤러 책임)

        // 2. 유저네임 중복검사(서비스기 체크) DM연결이 필요해서 서비스가 해아함
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());

        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네임입니다.");
        }

        userJPARepository.save(reqDTO.toEntity());
    }
}

/**
 * 두 코드는 같지만 다르다.
 * 1. 못 찾았을 때 날리기
 * User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
 * .orElseThrow(() -> new Exception401("인증되지 않았습니다.")); // orElseThrow: 값이 널이면
 * <p>
 * 2. 찾았을 때 날리기
 * Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
 * <p>
 * if (userOP.isPresent()) {
 * throw new Exception400("중복된 유저네임입니다.");
 * }
 */