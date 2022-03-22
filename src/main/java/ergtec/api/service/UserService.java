package ergtec.api.service;

import ergtec.exceptions.UserNotFoundException;
import ergtec.api.response.*;
import ergtec.entities.AlbumEntity;
import ergtec.entities.UserEntity;
import ergtec.repository.IAlbumRepository;
import ergtec.repository.IImageRepository;
import ergtec.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IAlbumRepository albumRepository;
    private IImageRepository imageRepository;

    @Autowired
    public UserService(IUserRepository userRepository,
                       IAlbumRepository albumRepository,
                       IImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public User getUser(int userId) throws UserNotFoundException {
        UserEntity user = userRepository.findById(userId);
        if (user == null)
            throw new UserNotFoundException();

        return new User(user);
    }

    @Override
    public UserAlbumsAggregate getUserAndAlbums(int userId) throws UserNotFoundException {
        List<AlbumEntity> albumEntities = albumRepository.findByUserId(userId);
        if (albumEntities.isEmpty())
            throw new UserNotFoundException();

        List<Album> albums = new ArrayList<>();
        for (AlbumEntity albumEntity: albumEntities)
            albums.add(new Album(albumEntity.title));

        return new UserAlbumsAggregate(albumEntities.get(0).user, albums);
    }

    @Override
    public Map<String, Object> getUserAlbumsAndImages(int userId, Pageable pageable) throws UserNotFoundException {
        Page<ImageWithAlbumName> pageResult = imageRepository.getImagesWithAlbumTitle(userId, pageable);
        List<ImageWithAlbumName> images = pageResult.getContent();
        if (images.isEmpty())
            throw new UserNotFoundException();

        Map<String, Object> result = new HashMap<>();
        result.put("Images", images);
        result.put("CurrentPage", pageResult.getNumber());
        result.put("TotalImages", pageResult.getTotalElements());
        result.put("TotalPages", pageResult.getTotalPages());

        return result;
    }

}
