package java_advance.spring_boot_spotify.repository;

import java_advance.spring_boot_spotify.model.track.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, String> {
}
