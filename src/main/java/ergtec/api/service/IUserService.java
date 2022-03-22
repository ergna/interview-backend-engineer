package ergtec.api.service;

import ergtec.exceptions.UserNotFoundException;
import ergtec.api.response.User;
import ergtec.api.response.UserAlbumsAggregate;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IUserService {
    User getUser(int userID) throws UserNotFoundException;
    UserAlbumsAggregate getUserAndAlbums(int userId) throws UserNotFoundException;
    Map<String, Object> getUserAlbumsAndImages(int userId, Pageable pageable) throws UserNotFoundException;
}