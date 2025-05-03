package br.com.youtubemanager.core;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class YouTubeConfiguration {

	@Value("${youtube.api-key}")
	private String apiKey;

	@Bean
	public YouTube youTubeService() {
		try {
			return new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(),
					JacksonFactory.getDefaultInstance(), null)
				.setApplicationName("youtube-manager")
				.setYouTubeRequestInitializer(new YouTubeRequestInitializer(apiKey))
				.build();
		}
		catch (GeneralSecurityException | IOException e) {
			throw new YouTubeManagerException(
					"Failed to initialize YouTube service. Please check your internet connection and try again.", e);
		}
	}

}