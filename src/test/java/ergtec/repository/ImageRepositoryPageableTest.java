package ergtec.repository;

import ergtec.ExpectedResults;
import ergtec.InterviewBackendEngineer;
import ergtec.api.response.ImageWithAlbumName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest(classes = InterviewBackendEngineer.class)
class ImageRepositoryPageableTest extends ExpectedResults {

    @Autowired
    private IImageRepository imageRepository;

    /**
     * page size parameter direction
     * 0    5    album     asc
     * 1    3    album     asc
     * 0    5    album     desc
     * 0    5    image     asc
     * 0    5    image     desc
     */

    @Test
    void imagesPageable05AlbumAsc() {
        Pageable pageable = getPageable(0,5, getSortAlbumAsc());
        Page<ImageWithAlbumName> imagePage = imageRepository.getImagesWithAlbumTitle(1, pageable);

        Assertions.assertEquals(0, imagePage.getNumber());
        Assertions.assertEquals(1, imagePage.getTotalPages());
        Assertions.assertEquals(5, imagePage.getTotalElements());

        ImageWithAlbumName image = imagePage.getContent().get(0);

        Assertions.assertTrue(image.id == 4849 || image.id == 4850);

    }

    @Test
    void imagesPageable13AlbumAsc() {
        Pageable pageable = getPageable(1,3, getSortAlbumAsc());
        Page<ImageWithAlbumName> imagePage = imageRepository.getImagesWithAlbumTitle(1, pageable);

        Assertions.assertEquals(1, imagePage.getNumber());
        Assertions.assertEquals(2, imagePage.getTotalPages());
        Assertions.assertEquals(5, imagePage.getTotalElements());

        ImageWithAlbumName image = imagePage.getContent().get(0);

        Assertions.assertTrue(image.id == 4851 || image.id == 4852);

    }

    @Test
    void imagesPageable05AlbumDesc() {
        Pageable pageable = getPageable(0,5, getSortAlbumDesc());
        Page<ImageWithAlbumName> imagePage = imageRepository.getImagesWithAlbumTitle(1, pageable);

        Assertions.assertEquals(0, imagePage.getNumber());
        Assertions.assertEquals(1, imagePage.getTotalPages());
        Assertions.assertEquals(5, imagePage.getTotalElements());

        ImageWithAlbumName image = imagePage.getContent().get(0);

        Assertions.assertTrue(image.id == 4851 || image.id == 4852 || image.id == 4853);

    }

    @Test
    void imagesPageable05ImageAsc() {
        Pageable pageable = getPageable(0,5, getSortImageAsc());
        Page<ImageWithAlbumName> imagePage = imageRepository.getImagesWithAlbumTitle(1, pageable);

        Assertions.assertEquals(0, imagePage.getNumber());
        Assertions.assertEquals(1, imagePage.getTotalPages());
        Assertions.assertEquals(5, imagePage.getTotalElements());

        ImageWithAlbumName image = imagePage.getContent().get(0);

        Assertions.assertTrue(image.id == 4851 || image.id == 4852 || image.id == 4853);

    }

    @Test
    void imagesPageable05ImageDesc() {
        Pageable pageable = getPageable(0,5, getSortImageDesc());
        Page<ImageWithAlbumName> imagePage = imageRepository.getImagesWithAlbumTitle(1, pageable);

        Assertions.assertEquals(0, imagePage.getNumber());
        Assertions.assertEquals(1, imagePage.getTotalPages());
        Assertions.assertEquals(5, imagePage.getTotalElements());

        ImageWithAlbumName image = imagePage.getContent().get(0);

        Assertions.assertTrue(image.id == 4849 || image.id == 4850);

    }

}
