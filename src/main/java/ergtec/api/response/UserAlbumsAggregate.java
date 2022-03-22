package ergtec.api.response;

import ergtec.entities.UserEntity;

import java.util.List;

public class UserAlbumsAggregate extends User {

    public final List<Album> albums;

    public UserAlbumsAggregate(UserEntity user, List<Album> albums) {
        super(user);
        this.albums = albums;
    }
}
