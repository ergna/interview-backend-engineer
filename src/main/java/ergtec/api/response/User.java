package ergtec.api.response;

import ergtec.entities.UserEntity;

public class User {
    public final int id;
    public final String name;
    public final String email;
    public final Address address;

    public User(UserEntity user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        address = new Address(user.latitude, user.longitude);
    }

    public User(int id, String name, String email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
