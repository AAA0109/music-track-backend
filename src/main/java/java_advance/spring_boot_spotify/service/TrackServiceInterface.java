package java_advance.spring_boot_spotify.service;

import java_advance.spring_boot_spotify.model.album.Album;
import java_advance.spring_boot_spotify.model.track.Track;

import java.util.List;

public interface TrackServiceInterface {

    void createTrack(String isrc);
    Track getTrackMetadata(String isrc);
    List<Album.CoverImage> getCoverImage(String isrc);
}
