package ergtec.controller;

import ergtec.ExpectedResults;
import ergtec.InterviewBackendEngineer;
import ergtec.api.response.User;
import ergtec.api.response.UserNotFoundResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {InterviewBackendEngineer.class})
class IntegrationTest extends ExpectedResults {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetUser() throws Exception {
        // given
        URI uri = new URI("/v1/user/1");
        // when
        ResponseEntity<User> response = restTemplate.getForEntity(uri, User.class);
        // then
        compareUsers(response.getBody());
    }

    @Test
	void testGetAlbums() throws Exception {
		// given
		URI uri = new URI("/v1/user/1/albums");
		// when
		ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
		// then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

    @Test
    void testGetImagesDefaultPageable() throws Exception {
        // given
        URI uri = new URI("/v1/user/1/albums&images");
        // when
        ResponseEntity<Map> response = restTemplate.getForEntity(uri, Map.class);
        // then
        Map<String, Object> responseMap = response.getBody();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(0, responseMap.get("CurrentPage"));
        Assertions.assertEquals(1, responseMap.get("TotalPages"));
        Assertions.assertEquals(5, responseMap.get("TotalImages"));
    }

    @Test
    void testGetUserError() throws Exception {
        // given
        URI uri = new URI("/v1/user/1111");
        // when
        ResponseEntity<UserNotFoundResponse> response = restTemplate.getForEntity(uri, UserNotFoundResponse.class);
        // then
        compareNoUserFoundErrors(response.getBody().error);
    }

    @Test
    void testGetAlbumsError() throws Exception {
        // given
        URI uri = new URI("/v1/user/1111/albums");
        // when
        ResponseEntity<UserNotFoundResponse> response = restTemplate.getForEntity(uri, UserNotFoundResponse.class);
        // then
        compareNoUserFoundErrors(response.getBody().error);
    }

    @Test
    void testGetImagesError() throws Exception {
        // given
        URI uri = new URI("/v1/user/1111/albums&images");
        // when
        ResponseEntity<UserNotFoundResponse> response = restTemplate.getForEntity(uri, UserNotFoundResponse.class);
        // then
        compareNoUserFoundErrors(response.getBody().error);
    }

}
