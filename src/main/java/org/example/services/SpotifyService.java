package org.example.services;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.net.URI;

public class SpotifyService {

    // private final String API_URL = "https://api.spotify.com/v1/search?q=artist:";
    private final String CLIENT_ID = "YOUR CLIENT ID HERE";
    private final String CLIENT_SECRET = "YOUR CLIENT SECRET HERE";

    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(URI.create("http://localhost:9000"))
            .build();

    private final ClientCredentialsRequest CLIENT_CREDENTIAL_REQUEST =
            spotifyApi.clientCredentials().build();
    public SpotifyService() {
        getClientCredentials();
    }

    public Artist getArtist(String artistName){
        System.out.println(artistName);
        SearchArtistsRequest searchArtistsRequest =
                spotifyApi.searchArtists(artistName).build();
        try {
            final Paging<Artist> artistPaging = searchArtistsRequest.execute();

            return artistPaging.getItems()[0];
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private void getClientCredentials(){
        try {
            ClientCredentials clientCredentials =
                    CLIENT_CREDENTIAL_REQUEST.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
