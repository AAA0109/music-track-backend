package java_advance.spring_boot_spotify.model.api;

import java_advance.spring_boot_spotify.model.ResponseStatus;
import java_advance.spring_boot_spotify.model.album.Album;

public class GetCoverResponse extends LoginResponse {
    private Data data;

    public static class Data {
        private Album album;

        public Data() {
            this.album = new Album();
        }

        public void setAlbum(Album album) {
            this.album = album;
        }
        public Album getAlbum() {
            return this.album;
        }
    }
    public GetCoverResponse() {
        this.data = new Data();
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}
