package ergtec.repository;

import ergtec.InterviewBackendEngineer;
import ergtec.entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = InterviewBackendEngineer.class)
class UserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    void userByIdRepoTest() {
        UserEntity user = userRepository.findById(1);
        Assertions.assertEquals(1, user.id);
        Assertions.assertEquals("Leanne Graham", user.name);
        Assertions.assertEquals("Sincere@april.biz", user.email);
    }

    @Test
    void allUsersRepoTest() {
        List<UserEntity> users = userRepository.findAllUsers();
        Assertions.assertEquals(3, users.size());
    }

    @Test
    void nonExistingUserRepoTest() {
        UserEntity user = userRepository.findById(1111);
        Assertions.assertNull(user);
    }

}
