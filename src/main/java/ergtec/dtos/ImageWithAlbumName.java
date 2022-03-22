package ergtec.dtos;

public class ImageWithAlbumName {
    public final int id;
    public final String albumTitle;
    public final String title;
    public final String url;

    public ImageWithAlbumName(int id, String title, String url, String albumTitle) {
        this.id = id;
        this.albumTitle = albumTitle;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageWithAlbumName{" +
                "id=" + id +
                ", albumTitle='" + albumTitle + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
