package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

// 자동 컴퍼넌트 스캔이 된다.
public interface UserJPARepository extends JpaRepository<User, Integer> { // 인터페이스는 뉴 할 수 없기 때문에 클래스가 인터페이스를 상속하게 해서 그것을 띄운다.
    // public 안 써도 됨. 인터페이스 안에 잇었

    // 간단한 쿼리를 아래처럼 저어도 되지만 복잡한 쿼리는 엔티티명QueryRepository 만들어서 거기서 관리해라.
    // @Query("select u from User u where u.username =:username and u.password=:password")
    // 내가 아래의 메서드 이름을 login 이렇게 하려면 위의 어노테이션을 달아줘야함. 그게 아니라면 아래처럼만 불러오면 기본 메서드이기 때문에 구현된다.
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}



