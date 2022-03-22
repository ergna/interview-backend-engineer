package ergtec;

import ergtec.api.response.Address;
import ergtec.api.response.User;
import ergtec.api.response.UserAlbumsAggregate;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ExpectedResults {

    protected User createExpectedUser() {
        return new User(1, "Leanne Graham", "Sincere@april.biz",
                new Address(-37.3158f, 81.1496f));
    }

    protected Pageable getPageable() {
        return PageRequest.of(0, 10);
    }

    protected String expectedErrorMessage() {
        return "No user found";
    }

    protected void compareNoUserFoundErrors(String actualErrorMessage) {
        Assertions.assertEquals(expectedErrorMessage(), actualErrorMessage);
    }

    protected void compareUserAlbums(UserAlbumsAggregate actualUserWithAlbums) {
        Assertions.assertEquals(2, actualUserWithAlbums.albums.size());
        Assertions.assertEquals("Leanne Graham", actualUserWithAlbums.name);
        Assertions.assertEquals("3 sunt qui excepturi placeat culpa", actualUserWithAlbums.albums.get(0).title);
    }

}
