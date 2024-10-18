package java_advance.spring_boot_spotify.repository;

import java_advance.spring_boot_spotify.model.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, String>{

}
