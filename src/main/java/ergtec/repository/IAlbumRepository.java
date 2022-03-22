package ergtec.repository;

import ergtec.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlbumRepository extends JpaRepository<AlbumEntity, Integer> {
    @Query(value = "select a from album a where a.user.id = ?1")
    List<AlbumEntity> findByUserId(int id);

    @Query(value = "select a from album a")
    List<AlbumEntity> findAllAlbums();
}