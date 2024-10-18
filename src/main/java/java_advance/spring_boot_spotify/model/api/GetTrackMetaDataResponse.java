package java_advance.spring_boot_spotify.model.api;

import java_advance.spring_boot_spotify.model.track.Track;

public class GetTrackMetaDataResponse extends LoginResponse {
    private Data data;

    public static class Data {
        private Track track;

        public Data() {
            this.track = new Track();
        }

        public void setTrack(Track track) {
            this.track = track;
        }
        public Track getTrack() {
            return track;
        }
    }
    public GetTrackMetaDataResponse() {
        this.data = new Data();
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

}
