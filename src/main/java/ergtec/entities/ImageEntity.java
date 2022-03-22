package ergtec.entities;

import javax.persistence.*;

@Entity(name = "image")
@Table(name = "IMAGES")
public class ImageEntity {
    @Id
    @Column(name = "ID")
    public int id;

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    public AlbumEntity album;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "URL")
    public String url;

    public String toString() {
        return "image: " + id + " album title: " + album.title;
    }

}