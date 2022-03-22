package ergtec.repository;

import ergtec.InterviewBackendEngineer;
import ergtec.api.response.ImageWithAlbumName;
import ergtec.entities.ImageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

@SpringBootTest(classes = InterviewBackendEngineer.class)
class ImageRepositoryTest {

    @Autowired
    private IImageRepository imageRepository;

    @Test
    void imagesByIdRepoTest() {
        ImageEntity image = imageRepository.findImage(4849);
        Assertions.assertEquals(4849, image.id);
        Assertions.assertEquals(1, image.album.id);
        Assertions.assertEquals("h recusandae provident modi vitae ipsa rerum", image.title);
        Assertions.assertEquals("https://via.placeholder.com/600/4e5c31", image.url);
    }

    @Test
    void imageByNonExistingId() {
        ImageEntity image = imageRepository.findImage(0);
        Assertions.assertNull(image);
    }

    @Test
    void allImagesRepoTest() {
        List<ImageEntity> images = imageRepository.findAllImages();
        Assertions.assertEquals(10, images.size());
    }

    @Test
    void imagesByAlbumIdRepoTest() {
        List<ImageEntity> images = imageRepository.findByAlbumId(1);
        Assertions.assertEquals(2, images.size());
        Assertions.assertEquals(4849, images.get(0).id);
        Assertions.assertEquals(4850, images.get(1).id);
    }

    @Test
    void imagesWithAlbumTitlesByUserIdSortImageTitleAscTest() {
        Order order = new Order(Sort.Direction.ASC, "title");
        ImageWithAlbumName image = getFirstElementOfResultSet(1, order);
        Assertions.assertEquals(4853, image.id);
    }

    @Test
    void imagesWithAlbumTitlesByUserIdSortImageTitleDescTest() {
        Order order = new Order(Sort.Direction.DESC, "title");
        ImageWithAlbumName image = getFirstElementOfResultSet(1, order);
        Assertions.assertEquals(4850, image.id);
    }

    @Test
    void imagesWithAlbumTitlesByUserIdSortAlbumTitleAscTest() {
        Order order = new Order(Sort.Direction.ASC, "album.title");
        ImageWithAlbumName image = getFirstElementOfResultSet(1, order);

        Assertions.assertTrue(image.id == 4849 || image.id == 4850);
    }

    @Test
    void imagesWithAlbumTitlesByUserIdSortAlbumTitleDescTest() {
        Order order = new Order(Sort.Direction.DESC, "album.title");
        ImageWithAlbumName image = getFirstElementOfResultSet(1, order);

        Assertions.assertTrue(image.id == 4851 || image.id == 4852 || image.id == 4853);
    }

    @Test
    void imagesByNonExistingUserIdRepoTest() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<ImageWithAlbumName> result = imageRepository.getImagesWithAlbumTitle(11111, pageable);
        List<ImageWithAlbumName> images = result.getContent();
        Assertions.assertEquals(0, images.size());
    }

    private ImageWithAlbumName getFirstElementOfResultSet(int id, Order order) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(order));
        Page result = imageRepository.getImagesWithAlbumTitle(id, pageable);

        List<ImageWithAlbumName> images = result.getContent();
        return images.get(0);
    }

}
