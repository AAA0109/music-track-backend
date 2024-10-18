package java_advance.spring_boot_spotify.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java_advance.spring_boot_spotify.model.album.Album;
import java_advance.spring_boot_spotify.model.album.AlbumResponse;
import java_advance.spring_boot_spotify.model.track.Track;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Component
public class SpotifyApiClient {

    @Value("${spotify.clientId}")
    private String clientId;

    @Value("${spotify.clientSecret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    @Autowired
    public SpotifyApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Track getTrackByIsrc(String isrc) {
        String urlString = "https://api.spotify.com/v1/search?q=" + isrc + "&type=track";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            connection.setRequestProperty("Accept", "application/json");

            // Handle response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper objectMapper = new ObjectMapper();

                JSONObject jsonObject = new JSONObject(response.toString());

                Track result = new Track();

                JSONObject item = jsonObject.getJSONObject("tracks").getJSONArray("items").getJSONObject(0);
                result.setIsrc(item.getJSONObject("external_ids").getString("isrc"));
                result.setName(item.getString("name"));
                result.setAlbumName(item.getJSONObject("album").getString("name"));
                result.setAlbumId(item.getJSONObject("album").getString("id"));
                result.setArtistName(item.getJSONArray("artists").getJSONObject(0).getString("name" ));
                result.setExplicit(item.getBoolean("explicit"));
                result.setPlaybackSeconds(item.getInt("duration_ms") / 1000);

                return result;
            } else {
                return null;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Album getAlbumById(String albumId) {

        String urlString = "https://api.spotify.com/v1/albums/" + albumId;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            connection.setRequestProperty("Accept", "application/json");

            // Handle response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                AlbumResponse albumResponse = new AlbumResponse();

                JSONObject jsonObject = new JSONObject(response.toString());

                Album result = new Album();

                result.setId(jsonObject.getString("id"));
                result.setName(jsonObject.getString("name"));

                JSONArray items = jsonObject.getJSONArray("images");
                List<Album.CoverImage> setImages = new ArrayList<>();
                for (int i = 0; i < items.length(); i++) {
                    Album.CoverImage coverImage = new Album.CoverImage();

                    coverImage.setUrl(items.getJSONObject(i).getString("url"));
                    coverImage.setHeight(items.getJSONObject(i).getInt("height"));
                    coverImage.setWidth(items.getJSONObject(i).getInt("width"));

                    setImages.add(coverImage);
                }
                result.setCoverImage(setImages);

                return result;
            } else {
                return null;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }

//        HttpHeaders headers = createHeaders(getAccessToken());
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<Album> response = restTemplate.exchange(
//                "https://api.spotify.com/v1/albums/{albumId}",
//                HttpMethod.GET,
//                entity,
//                Album.class,
//                albumId
//        );
//
//        return response.getBody();
    }

    private String getAccessToken() {

        try {
            String url = "https://accounts.spotify.com/api/token";
            String auth = clientId + ":" + clientSecret;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            String body = "grant_type=client_credentials";
            try (OutputStream os = connection.getOutputStream()) {
                os.write(body.getBytes("UTF-8"));
                os.flush();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Extract the token from the response JSON
            String jsonResponse = response.toString();
            String accessToken = jsonResponse.split("\"access_token\":\"")[1].split("\"")[0];
            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        String auth = clientId + ":" + clientSecret;
//        String encodedAuth = Base64Utils.encodeToString(auth.getBytes(StandardCharsets.UTF_8));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Authorization", "Basic " + encodedAuth);
//
//        Map<String, String> body = new HashMap<>();
//        body.put("grant_type", "client_credentials");
//
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(
//                "https://accounts.spotify.com/api/token",
//                HttpMethod.POST,
//                entity,
//                Map.class
//        );

//        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//            return (String) response.getBody().get("access_token");
//        } else {
//            throw new IllegalStateException("Failed to obtain access token from Spotify");
//        }
    }

    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }
}