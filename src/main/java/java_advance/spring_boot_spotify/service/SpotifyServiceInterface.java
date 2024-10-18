package java_advance.spring_boot_spotify.service;

import java_advance.spring_boot_spotify.model.album.Album;
import java_advance.spring_boot_spotify.model.track.Track;

public interface SpotifyServiceInterface {

    Track fetchTrackByIsrc(String isrc);
    Album fetchAlbumById(String albumId);
}
