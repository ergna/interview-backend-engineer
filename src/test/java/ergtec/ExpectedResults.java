package ergtec;

import ergtec.api.response.Address;
import ergtec.api.response.User;
import ergtec.api.response.UserAlbumsAggregate;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    protected void compareUsers(User actualUser) {
        User expectedUser = createExpectedUser();

        Assertions.assertEquals(expectedUser.id, actualUser.id);
        Assertions.assertEquals(expectedUser.name, actualUser.name);
        Assertions.assertEquals(expectedUser.email, actualUser.email);
    }

    protected void compareNoUserFoundErrors(String actualErrorMessage) {
        Assertions.assertEquals(expectedErrorMessage(), actualErrorMessage);
    }

    protected void compareUserAlbums(UserAlbumsAggregate actualUserWithAlbums) {
        Assertions.assertEquals(2, actualUserWithAlbums.albums.size());
        Assertions.assertEquals("Leanne Graham", actualUserWithAlbums.name);
        Assertions.assertEquals("3 sunt qui excepturi placeat culpa", actualUserWithAlbums.albums.get(0).title);
    }

    protected Pageable getPageable(int pageNo, int pageSize, Sort sortBy) {
        return PageRequest.of(pageNo, pageSize, sortBy);
    }

    protected Sort getSortAlbumAsc() {
        return Sort.by("album").ascending();
    }

    protected Sort getSortAlbumDesc() {
        return Sort.by("album").descending();
    }

    protected Sort getSortImageAsc() {
        return Sort.by("title").ascending();
    }

    protected Sort getSortImageDesc() {
        return Sort.by("title").descending();
    }

}
