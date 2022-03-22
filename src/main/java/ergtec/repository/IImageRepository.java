package ergtec.repository;

import ergtec.dtos.ImageWithAlbumName;
import ergtec.entities.AlbumEntity;
import ergtec.entities.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IImageRepository extends JpaRepository<AlbumEntity, Integer> {
    @Query(value = "select i from image i where i.album.id = ?1")
    List<ImageEntity> findByAlbumId(int id);

    @Query(value = "select i from image i")
    List<ImageEntity> findAllImages();

    @Query(value = "select i from image i where i.id = ?1")
    ImageEntity findImage(int id);

    @Query(value = "select new ergtec.dtos.ImageWithAlbumName(i.id, i.title, i.url, a.title) from" +
            " image i inner join album a on i.album.id = a.id where a.user.id = ?1")
    Page<ImageWithAlbumName> getImagesWithAlbumTitle(int userId, Pageable pageable);

}
