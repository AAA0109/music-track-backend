package java_advance.spring_boot_spotify.model.track;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Track {
    @Id
    private String isrc;
    private String name;
    private String artistName;
    private String albumName;
    private String albumId;
    private boolean isExplicit;
    private int playbackSeconds;

    public void setExplicit(boolean isExplicit) {
        this.isExplicit = isExplicit;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setPlaybackSeconds(int playbackSeconds) {
        this.playbackSeconds = playbackSeconds;
    }
}
