package br.com.youtubemanager.channel.web;

import br.com.youtubemanager.channel.ChannelDTO;
import br.com.youtubemanager.channel.ChannelNotFoundException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ChannelSnippet;
import com.google.api.services.youtube.model.ChannelStatistics;
import com.google.api.services.youtube.model.Thumbnail;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static br.com.youtubemanager.core.YoutubeUtils.getYouTubeService;

@Service
class ChannelService {

	@Value("${youtube.api-key}")
	String apiKey;

	public ChannelDTO findOne(String channelName) {
		try {
			YouTube.Channels.List request = getYouTubeService().channels()
					.list("snippet,contentDetails,statistics")
					.setForUsername(channelName)
					.setKey(apiKey);

			ChannelListResponse response = request.execute();
			List<com.google.api.services.youtube.model.Channel> channels = response.getItems();

			if (CollectionUtils.isEmpty(channels)) {
				throw new ChannelNotFoundException("Channel not found");
			}

			return convertToMyChannel(channels.getFirst());
		} catch (IOException e) {
			throw new RuntimeException("Failed to get channel information", e);
		}
	}

	private ChannelDTO convertToMyChannel(com.google.api.services.youtube.model.Channel youtubeChannel) {
		ChannelSnippet snippet = youtubeChannel.getSnippet();
		ChannelStatistics statistics = youtubeChannel.getStatistics();
		Thumbnail thumbnail = snippet.getThumbnails().getDefault();

		return ChannelDTO.of(
				snippet.getTitle(),
				snippet.getCustomUrl(),
				snippet.getDescription(),
				snippet.getPublishedAt().toString(),
				thumbnail.getUrl(),
				snippet.getLocalized().getDescription(),
				snippet.getCountry(),
				statistics.getViewCount().toString(),
				statistics.getSubscriberCount().toString(),
				statistics.getVideoCount().toString()
		);
	}

}
