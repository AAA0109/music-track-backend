package java_advance.spring_boot_spotify.service;

import java_advance.spring_boot_spotify.model.ResponseStatus;
import java_advance.spring_boot_spotify.model.album.Album;
import java_advance.spring_boot_spotify.model.api.CreateTrackResponse;
import java_advance.spring_boot_spotify.model.track.Track;
import java_advance.spring_boot_spotify.repository.TrackRepository;
import java_advance.spring_boot_spotify.utils.GlobalUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrackService {

    private final TrackRepository trackRepository;
    private final SpotifyService spotifyService;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TrackService.class);
    @Autowired
    public TrackService(TrackRepository trackRepository, SpotifyService spotifyService) {
        this.trackRepository = trackRepository;
        this.spotifyService = spotifyService;
    }

    @Transactional
    public CreateTrackResponse createTrack(String isrc) {
        logger.info("Checking if track with ISRC {} exists", isrc);
        if (trackRepository.existsById(isrc)) {
            Track result = trackRepository.findById(isrc).orElseThrow(() -> new IllegalArgumentException("Track not found"));
            CreateTrackResponse response = new CreateTrackResponse();

            response.setStatus(ResponseStatus.Status.ALREADY);
            response.setMessage("Track already exists!");
            return response;
        }

        logger.info("Fetching track from Spotify service with ISRC {}", isrc);
        try {
            Track track = spotifyService.fetchTrackByIsrc(isrc);
            track.setIsrc(isrc);
            if (track != null) {
                logger.info("Track found, saving to the repository");
                trackRepository.save(track);
                CreateTrackResponse response = new CreateTrackResponse();

                response.setStatus(ResponseStatus.Status.SUCCESS);
                response.setMessage("Track saved!");
                return response;
            } else {
                logger.warn("Track not found for ISRC {}", isrc);
                CreateTrackResponse response = new CreateTrackResponse();
                response.setStatus(ResponseStatus.Status.FAILED);
                response.setMessage("Track not found!");
                return response;
            }
        } catch (Exception e) {
            logger.error("An error occurred while creating the track", e);
            CreateTrackResponse response = new CreateTrackResponse();
            response.setStatus(ResponseStatus.Status.FAILED);
            response.setMessage("An error occurred while creating the track!");
            return response;
        }
    }

    public Track getTrackMetadata(String isrc) {
        try {
            if (trackRepository.existsById(isrc)) {
                return trackRepository.findById(isrc).orElseThrow(() -> new IllegalArgumentException("Track not found"));
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    public Album getAlbum(String isrc) {
        try {
            if (!trackRepository.existsById(isrc)) {
                return null;
            }
            else {
                Track track = trackRepository.findById(isrc).orElseThrow(() -> new IllegalArgumentException("Track not found"));
                Album album = spotifyService.fetchAlbumById(track.getAlbumId());

                return album;
            }
        } catch (Exception e) {
            return null;
        }
    }
}