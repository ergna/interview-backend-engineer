package ergtec.api.controller;

import ergtec.api.response.UserNotFoundResponse;
import ergtec.api.service.IUserService;
import ergtec.api.utils.PageableFactory;
import ergtec.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1")
@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @Tag(name = "Get User by user id")
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return getUserNotFoundErrorResponse(e.getMessage());
        }
    }

    @Tag(name = "Get User and Albums by user Id")
    @GetMapping("/user/{id}/albums")
    public ResponseEntity<Object> getUserAndAlbums(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userService.getUserAndAlbums(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return getUserNotFoundErrorResponse(e.getMessage());
        }
    }

    @Tag(name = "Get Albums and Images by user Id")
    @GetMapping("/user/{id}/albums&images")
    public ResponseEntity<Object> getUserAlbumsAndImages(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "album,asc") String sortBy) {
        try {
            Pageable pg = PageableFactory.getPageable(pageNo, pageSize, sortBy);
            return new ResponseEntity<>(
                    userService.getUserAlbumsAndImages(id, pg), HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return getUserNotFoundErrorResponse(e.getMessage());
        }
    }

    private ResponseEntity<Object> getUserNotFoundErrorResponse(String exceptionMessage) {
        return new ResponseEntity<>(new UserNotFoundResponse(HttpStatus.NOT_FOUND.value(), exceptionMessage), HttpStatus.NOT_FOUND);
    }

}
