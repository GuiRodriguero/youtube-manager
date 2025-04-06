package br.com.youtubemanager.channel.web;

import br.com.youtubemanager.channel.Channel;
import br.com.youtubemanager.channel.ChannelNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ChannelSnippet;
import com.google.api.services.youtube.model.ChannelStatistics;
import com.google.api.services.youtube.model.Thumbnail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static br.com.youtubemanager.core.YoutubeUtils.getYouTubeService;

@Service
class ChannelService {

	@Value("${youtube.api-key}")
	String apiKey;

	public Channel findOne(String channelName) {
		try {
			YouTube.Channels.List request = getYouTubeService().channels()
					.list("snippet,contentDetails,statistics")
					.setForUsername(channelName)
					.setKey(apiKey);

			ChannelListResponse response = request.execute();
			List<com.google.api.services.youtube.model.Channel> channels = response.getItems();

			if(CollectionUtils.isEmpty(channels)) {
				throw new ChannelNotFoundException("Channel not found");
			}

			return convertToMyChannel(channels.getFirst());
		} catch (IOException e) {
			throw new RuntimeException("Failed to get channel information", e);
		}
	}

	private Channel convertToMyChannel(com.google.api.services.youtube.model.Channel youtubeChannel) {
		ChannelSnippet snippet = youtubeChannel.getSnippet();
		ChannelStatistics statistics = youtubeChannel.getStatistics();
		Thumbnail thumbnail = snippet.getThumbnails().getDefault();

		return Channel.of(
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
