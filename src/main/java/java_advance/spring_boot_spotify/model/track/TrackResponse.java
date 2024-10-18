package java_advance.spring_boot_spotify.model.track;

import lombok.Data;
import java.util.List;

@Data
public class TrackResponse {

    private Album album;
    private List<Artist> artists;
    private List<String> availableMarkets;
    private Integer discNumber;
    private Integer durationMs;
    private Boolean explicit;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private Boolean isPlayable;
    private LinkedFrom linkedFrom;
    private Restrictions restrictions;
    private String name;
    private Integer popularity;
    private String previewUrl;
    private Integer trackNumber;
    private String type;
    private String uri;
    private Boolean isLocal;

    @Data
    public static class Album {
        private String albumType;
        private Integer totalTracks;
        private List<String> availableMarkets;
        private ExternalUrls externalUrls;
        private String href;
        private String id;
        private List<Image> images;
        private String name;
        private String releaseDate;
        private String releaseDatePrecision;
        private Restrictions restrictions;
        private String type;
        private String uri;
        private List<Artist> artists;

        @Data
        public static class Image {
            private String url;
            private Integer height;
            private Integer width;
        }
    }

    @Data
    public static class Artist {
        private ExternalUrls externalUrls;
        private String href;
        private String id;
        private String name;
        private String type;
        private String uri;
    }

    @Data
    public static class ExternalIds {
        private String isrc;
        private String ean;
        private String upc;
    }

    @Data
    public static class ExternalUrls {
        private String spotify;
    }

    @Data
    public static class LinkedFrom {
        // Assuming the linked_from object has the same structure as Artist
        // If the structure is unknown or variable, handle appropriately
    }

    @Data
    public static class Restrictions {
        private String reason;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public String getIsrc() {
        return getExternalIds().isrc;
    }

    public String getName() {
        return name;
    }

    public Album getAlbum() {
        return album;
    }

    public String getAlbumName() {
        return getAlbum().name;
    }

    public String getId() {
        return getAlbum().id;
    }

    public boolean getExplicit() {
        return explicit;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public String getArtistName() {
        return getArtists().get(0).name;
    }
}