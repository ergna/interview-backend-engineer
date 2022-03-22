package ergtec.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "USERS")
public class UserEntity {
    @Id
    @Column(name = "ID")
    public int id;

    @Column(name = "NAME")
    public String name;

    @Column(name = "EMAIL")
    public String email;

    @Column(name = "ADDRESS_GEO_LAT")
    public float latitude;

    @Column(name = "ADDRESS_GEO_LNG")
    public float longitude;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public List<AlbumEntity> albumEntities;

    public String toString() {
        return "user: " + id + " " + name + " albums: " + albumEntities.size();
    }

    public UserEntity() {
    }

    public UserEntity(int id, String name, String email,
                      float latitude, float longitude,
                      List<AlbumEntity> albumEntities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.albumEntities = albumEntities;
    }
}