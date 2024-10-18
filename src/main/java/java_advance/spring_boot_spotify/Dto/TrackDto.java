package java_advance.spring_boot_spotify.Dto;

import lombok.Data;

@Data
public class TrackDto {
    private String name;
    private String artistName;
    private String albumName;
    private String albumId;
    private boolean isExplicit;
    private int playbackSeconds;

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setIsExplicit(boolean isExplicit) {
        this.isExplicit = isExplicit;
    }

    public void setPlaybackSeconds(int playbackSeconds) {
        this.playbackSeconds = playbackSeconds;
    }
}
