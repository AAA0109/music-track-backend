package java_advance.spring_boot_spotify.model.album;

import lombok.Data;
import java.util.List;

@Data
public class AlbumResponse {

    private String albumType;
    private Integer totalTracks;
    private List<String> availableMarkets;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private List<Album.CoverImage> images;
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private Restrictions restrictions;
    private String type;
    private String uri;
    private List<Artist> artists;
    private Tracks tracks;
    private List<Copyright> copyrights;
    private ExternalIds externalIds;
    private List<String> genres;
    private String label;
    private Integer popularity;

    @Data
    public static class ExternalUrls {
        private String spotify;
    }

    @Data
    public static class Restrictions {
        private String reason;
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
    public static class Tracks {
        private String href;
        private Integer limit;
        private String next;
        private Integer offset;
        private String previous;
        private Integer total;
        private List<Item> items;

        @Data
        public static class Item {
            private List<Artist> artists;
            private List<String> availableMarkets;
            private Integer discNumber;
            private Integer durationMs;
            private Boolean explicit;
            private ExternalUrls externalUrls;
            private String href;
            private String id;
            private Boolean isPlayable;
            private LinkedFrom linkedFrom;
            private Restrictions restrictions;
            private String name;
            private String previewUrl;
            private Integer trackNumber;
            private String type;
            private String uri;
            private Boolean isLocal;

            @Data
            public static class LinkedFrom {
                private ExternalUrls externalUrls;
                private String href;
                private String id;
                private String type;
                private String uri;
            }

            @Data
            public static class Restrictions {
                private String reason;
            }
        }
    }

    @Data
    public static class Copyright {
        private String text;
        private String type;
    }

    @Data
    public static class ExternalIds {
        private String isrc;
        private String ean;
        private String upc;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Album.CoverImage> getImages() {
        return images;
    }
}