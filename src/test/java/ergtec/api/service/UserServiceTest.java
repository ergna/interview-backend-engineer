package ergtec.api.service;

import ergtec.ExpectedResults;
import ergtec.api.response.User;
import ergtec.api.response.UserAlbumsAggregate;
import ergtec.entities.AlbumEntity;
import ergtec.entities.UserEntity;
import ergtec.exceptions.UserNotFoundException;
import ergtec.repository.IAlbumRepository;
import ergtec.repository.IImageRepository;
import ergtec.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest extends ExpectedResults {

    private IUserRepository mockUserRepository = mock(IUserRepository.class);
    private IAlbumRepository mockAlbumRepository = mock(IAlbumRepository.class);
    private IImageRepository mockImageRepository = mock(IImageRepository.class);

    private IUserService userService = new UserService(mockUserRepository, mockAlbumRepository, mockImageRepository);

    @Test
    void testGetUser() {
        when(mockUserRepository.findById(1)).thenReturn(createMockUserEntity());
        try {
            User actualUser = userService.getUser(1);
            User expectedUser = createExpectedUser();
            Assertions.assertEquals(expectedUser.id, actualUser.id);
            Assertions.assertEquals(expectedUser.name, actualUser.name);
            Assertions.assertEquals(expectedUser.email, actualUser.email);
        } catch (UserNotFoundException e) {
            Assertions.fail();
        }
    }

    @Test
    void testGetAlbums() {
        when(mockAlbumRepository.findByUserId(1)).thenReturn(createMockAlbumEntities());

        try {
            UserAlbumsAggregate userWithAlbums = userService.getUserAndAlbums(1);
            Assertions.assertEquals(2, userWithAlbums.albums.size());
            Assertions.assertEquals("Leanne Graham", userWithAlbums.name);
            Assertions.assertEquals("3 sunt qui excepturi placeat culpa", userWithAlbums.albums.get(0).title);
        } catch (UserNotFoundException e) {
            Assertions.fail();
        }
    }

    @Test
    void testGetNonExistingUser() {
        when(mockUserRepository.findById(1)).thenReturn(null);
        try {
            userService.getUser(1);
        } catch (UserNotFoundException e) {
            compareNoUserFoundErrors(e.getMessage());
        }
    }

    @Test
    void testGetExistingUserWithNoAlbums() {
        when(mockAlbumRepository.findByUserId(3)).thenReturn(new ArrayList<>());

        try {
            userService.getUserAndAlbums(3);
        } catch (UserNotFoundException e) {
            compareNoUserFoundErrors(e.getMessage());
        }
    }

    @Test
    void testGetNonExistingUsersAlbums() {
        when(mockAlbumRepository.findByUserId(0)).thenReturn(new ArrayList<>());
        try {
            userService.getUserAndAlbums(0);
        } catch (UserNotFoundException e) {
            compareNoUserFoundErrors(e.getMessage());
        }
    }

    @Test
    void testGetNonExistingUsersImages() {
        when(mockImageRepository.getImagesWithAlbumTitle(1,getPageable())).thenReturn(Mockito.mock(Page.class));
        try {
            userService.getUserAlbumsAndImages(1, getPageable());
        } catch (UserNotFoundException e) {
            compareNoUserFoundErrors(e.getMessage());
        }
    }

    private UserEntity createMockUserEntity() {
        return new UserEntity(1, "Leanne Graham", "Sincere@april.biz", -37.3158f, 81.1496f, new ArrayList<>());
    }

    private List<AlbumEntity> createMockAlbumEntities() {
        return Arrays.asList(
                new AlbumEntity(1, createMockUserEntity(),"3 sunt qui excepturi placeat culpa"),
                new AlbumEntity(2, createMockUserEntity(),"6 sunt qui excepturi placeat culpa")
        );
    }

}
