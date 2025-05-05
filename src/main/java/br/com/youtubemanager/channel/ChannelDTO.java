package br.com.youtubemanager.channel;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelSnippet;
import com.google.api.services.youtube.model.ChannelStatistics;
import com.google.api.services.youtube.model.Thumbnail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static br.com.youtubemanager.core.DateUtils.formatDate;
import static br.com.youtubemanager.core.NumberUtils.formatNumber;
import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE, staticName = "of")
public class ChannelDTO {

	private String title;

	private String customUrl;

	private String description;

	private String publishedAt;

	private String defaultThumbnailUrl;

	private String localizedDescription;

	private String country;

	private String viewCount;

	private String subscriberCount;

	private String videoCount;

	public String getFormattedPublishedAt() {
		return formatDate(this.publishedAt);
	}

	public String getFormattedCustomUrl() {
		return this.customUrl.substring(1);
	}

	public static ChannelDTO of(Channel channel) {
		ChannelSnippet snippet = channel.getSnippet();
		ChannelStatistics statistics = channel.getStatistics();
		Thumbnail thumbnail = snippet.getThumbnails().getDefault();

		return ChannelDTO.of(snippet.getTitle(), snippet.getCustomUrl(), snippet.getDescription(),
				snippet.getPublishedAt().toString(), thumbnail.getUrl(), snippet.getLocalized().getDescription(),
				snippet.getCountry(), formatNumber(statistics.getViewCount()),
				formatNumber(statistics.getSubscriberCount()), formatNumber(statistics.getVideoCount()));
	}

}
