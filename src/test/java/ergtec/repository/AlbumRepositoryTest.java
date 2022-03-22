package ergtec.repository;

import ergtec.InterviewBackendEngineer;
import ergtec.entities.AlbumEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = InterviewBackendEngineer.class)
class AlbumRepositoryTest {

    @Autowired
    private IAlbumRepository albumRepository;

    @Test
    void albumByUserIdRepoTest() {
        List<AlbumEntity> albums = albumRepository.findByUserId(1);
        Assertions.assertEquals(2, albums.size());

        Assertions.assertEquals(1, albums.get(0).id);
        Assertions.assertEquals(2, albums.get(1).id);
    }

    @Test
    void allAlbumsRepoTest() {
        List<AlbumEntity> albums = albumRepository.findAllAlbums();
        Assertions.assertEquals(5, albums.size());
    }

    @Test
    void albumByExistingUserIdWithNoAlbumsRepoTest() {
        List<AlbumEntity> albums = albumRepository.findByUserId(3);
        System.out.println(albums);
        System.out.println(albums.size());
        Assertions.assertEquals(0, albums.size());
    }

    @Test
    void albumByNonExistingUserIdRepoTest() {
        List<AlbumEntity> albums = albumRepository.findByUserId(1111);
        System.out.println(albums);
        System.out.println(albums.size());
        Assertions.assertEquals(0, albums.size());
    }

}
