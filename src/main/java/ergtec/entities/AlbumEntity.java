package ergtec.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "album")
@Table(name = "ALBUMS")
public class AlbumEntity {
    @Id
    @Column(name = "ID")
    public int id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public UserEntity user;

    @Column(name = "TITLE")
    public String title;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    public List<ImageEntity> imageEntities;

    public String toString() {
        return "album: " + id + " user name: " + user.name
                + " album title: " + title + " images: " + imageEntities.size();
    }

    public AlbumEntity() {
    }

    public AlbumEntity(int id, UserEntity user, String title) {
        this.id = id;
        this.user = user;
        this.title = title;
    }
}
