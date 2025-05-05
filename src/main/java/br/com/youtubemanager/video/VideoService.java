package br.com.youtubemanager.video;

import br.com.youtubemanager.core.youtube.YouTubeManagerException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService {

	private final YouTube youtube;

	public List<VideoDTO> findMostPopularVideosByCountry(VideoCountry country) {
		List<Video> videos;
		try {
			videos = youtube.videos()
				.list("snippet,contentDetails,statistics")
				.setChart(country.name())
				.execute()
				.getItems();
		}
		catch (IOException e) {
			throw new YouTubeManagerException();
		}

		return videos.stream().map(VideoDTO::of).toList();
	}

}
