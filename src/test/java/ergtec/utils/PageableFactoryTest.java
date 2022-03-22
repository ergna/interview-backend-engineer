package ergtec.utils;

import ergtec.ExpectedResults;
import ergtec.api.utils.PageableFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

class PageableFactoryTest extends ExpectedResults {

    @Test
    void testPageable05AlbumAsc() {
        Pageable paActual = PageableFactory.getPageable(0,5, "album,asc");
        Pageable paExpected = getPageable(0, 5, getSortAlbumAsc());
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageable3_15AlbumAsc() {
        Pageable paActual = PageableFactory.getPageable(3,15, "album,asc");
        Pageable paExpected = getPageable(3, 15, getSortAlbumAsc());
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageable05AlbumDesc() {
        Pageable paActual = PageableFactory.getPageable(0,5, "album,desc");
        Pageable paExpected = getPageable(0, 5, getSortAlbumDesc());
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageable05ImageAsc() {
        Pageable paActual = PageableFactory.getPageable(0,5, "image,asc");
        Pageable paExpected = getPageable(0, 5, getSortImageAsc());
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageable05ImageDesc() {
        Pageable paActual = PageableFactory.getPageable(0,5, "image,desc");
        Pageable paExpected = getPageable(0, 5, getSortImageDesc());
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageableSortByMissingComma() {
        Pageable paActual = PageableFactory.getPageable(0,10, "something");
        Pageable paExpected = getPageable();
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageableSortByCriteriaWrong() {
        Pageable paActual = PageableFactory.getPageable(0,10, "something,asc");
        Pageable paExpected = getPageable(0, 10, getSortAlbumAsc());
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageableSortByDirectionWrong() {
        Pageable paActual = PageableFactory.getPageable(0,10, "album,something");
        Pageable paExpected = getPageable(0, 10, getSortAlbumAsc());
        Assertions.assertEquals(paExpected, paActual);
    }

    @Test
    void testPageableSortByCriteriaAndDirectionWrong() {
        Pageable paActual = PageableFactory.getPageable(0,10, "something,anywhere");
        Pageable paExpected = getPageable(0, 10, getSortAlbumAsc());
        Assertions.assertEquals(paExpected, paActual);
    }

}
