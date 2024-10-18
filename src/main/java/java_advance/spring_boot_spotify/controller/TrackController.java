package java_advance.spring_boot_spotify.controller;

import java_advance.spring_boot_spotify.model.api.CreateTrackResponse;
import java_advance.spring_boot_spotify.model.api.GetCoverResponse;
import java_advance.spring_boot_spotify.model.api.GetTrackMetaDataResponse;
import java_advance.spring_boot_spotify.model.track.Track;
import java_advance.spring_boot_spotify.service.TrackService;
import java_advance.spring_boot_spotify.utils.GlobalUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java_advance.spring_boot_spotify.model.ResponseStatus;

@RestController
@RequestMapping("/codechallenge")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("/createTrack")
    public ResponseEntity<String> createTrack(@RequestParam("isrc") String isrc) {
        try {
            CreateTrackResponse response = trackService.createTrack(isrc);
            return ResponseEntity.ok(GlobalUtils.JsonStringify(response));
        } catch (Exception e) {
            CreateTrackResponse response = new CreateTrackResponse();
            response.setStatus(ResponseStatus.Status.FAILED);
            response.setMessage("failed");
            return ResponseEntity.ok(GlobalUtils.JsonStringify(response));
        }
    }

    @GetMapping("/getTrackMetadata")
    public ResponseEntity<String> getTrackMetadata(@RequestParam("isrc") String isrc) {
        Track track = trackService.getTrackMetadata(isrc);
        GetTrackMetaDataResponse response = new GetTrackMetaDataResponse();

        GetTrackMetaDataResponse.Data data = new GetTrackMetaDataResponse.Data();
        data.setTrack(track);
        response.setData(data);
        if (track == null) {
            response.setStatus(ResponseStatus.Status.NOT_FOUND);
            response.setMessage("Track not found");
            return ResponseEntity.ok(GlobalUtils.JsonStringify(response));
        }
        else {
            response.setStatus(ResponseStatus.Status.SUCCESS);
            response.setMessage("success");
            return ResponseEntity.ok(GlobalUtils.JsonStringify(response));
        }
    }

    @GetMapping("/getCover")
    public ResponseEntity<String> getCover(@RequestParam("isrc") String isrc) {

        GetCoverResponse response = new GetCoverResponse();
        response.setStatus(ResponseStatus.Status.NOT_FOUND);
        try {
            GetCoverResponse.Data data = new GetCoverResponse.Data();

            data.setAlbum(trackService.getAlbum(isrc));

            if (data.getAlbum() != null) {
                response.setData(data);
                response.setStatus(ResponseStatus.Status.SUCCESS);
                response.setMessage("success");
            } else {
                response.setMessage("Track not found");
            }
        } catch (Exception e) {
            response.setMessage("failed");
        }
        return ResponseEntity.ok(GlobalUtils.JsonStringify(response));
    }
}
