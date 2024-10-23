package org.example;

import org.example.services.SpotifyService;
import se.michaelthelin.spotify.model_objects.specification.Artist;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Let's discover some music");
        Scanner scanner = new Scanner(System.in);
        SpotifyService service = new SpotifyService();

        System.out.print("Enter an artist name: ");
        String name = scanner.nextLine();
        Artist artist = service.getArtist(name);
        System.out.println(artist.getName());
        System.out.println("Number of followers: " + artist.getFollowers());
        System.out.println("Genres: " + artist.getGenres());

    }
}