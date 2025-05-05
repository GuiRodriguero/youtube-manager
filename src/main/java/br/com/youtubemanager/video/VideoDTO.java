package br.com.youtubemanager.video;

import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static br.com.youtubemanager.core.NumberUtils.formatNumber;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE, staticName = "of")
public class VideoDTO {

	private String publishedAt;

	private String channelId;

	private String channelTitle;

	private String title;

	private String description;

	private String defaultThumbnailUrl;

	private String viewCount;

	private String likeCount;

	private String commentCount;

	public static VideoDTO of(Video video) {
		VideoSnippet snippet = video.getSnippet();
		VideoStatistics statistics = video.getStatistics();
		Thumbnail thumbnail = snippet.getThumbnails().getDefault();

		return VideoDTO.of(snippet.getPublishedAt().toString(), snippet.getChannelId(), snippet.getChannelTitle(),
				snippet.getTitle(), snippet.getDescription(), thumbnail.getUrl(),
				formatNumber(statistics.getViewCount()), formatNumber(statistics.getLikeCount()),
				formatNumber(statistics.getCommentCount()));
	}

}
