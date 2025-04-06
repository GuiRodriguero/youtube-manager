package br.com.youtubemanager.core;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class YoutubeUtils {

    public static YouTube getYouTubeService() {
        try {
            return new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                    .setApplicationName("youtube-manager")
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Failed to create YouTube service", e);
        }
    }

}
