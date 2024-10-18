package java_advance.spring_boot_spotify.service;

import java_advance.spring_boot_spotify.model.album.Album;
import java_advance.spring_boot_spotify.model.track.Track;
import java_advance.spring_boot_spotify.utils.SpotifyApiClient;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {
    private final SpotifyApiClient spotifyApiClient;

    public SpotifyService(SpotifyApiClient spotifyApiClient) {
        this.spotifyApiClient = spotifyApiClient;
    }

    public Track fetchTrackByIsrc(String isrc) {
        try {
            LoggerFactory.getLogger("-----------------fetchTrackByIsrc------------------");
            Track track = spotifyApiClient.getTrackByIsrc(isrc);
            if (track == null) {
                return null;
            }
            else {
                return track;
            }

        }
        catch (Exception e) {
            LoggerFactory.getLogger("-----------------fetchTrack Error------------------");
            return null;
        }
    }

    public Album fetchAlbumById(String albumId) {
        return spotifyApiClient.getAlbumById(albumId);
    }
}