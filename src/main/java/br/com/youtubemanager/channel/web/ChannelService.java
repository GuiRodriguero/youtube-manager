package br.com.youtubemanager.channel.web;

import br.com.youtubemanager.channel.ChannelDTO;
import br.com.youtubemanager.channel.ChannelNotFoundException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static br.com.youtubemanager.core.NumberUtils.formatNumber;
import static br.com.youtubemanager.core.YoutubeUtils.getYouTubeService;

@Service
class ChannelService {

	@Value("${youtube.api-key}")
	String apiKey;

	public ChannelDTO findOne(String channelName) {
		try {
			List<Channel> channels = getYouTubeService().channels()
				.list("snippet,contentDetails,statistics")
				.setForUsername(channelName)
				.setKey(apiKey)
				.execute()
				.getItems();

			if (CollectionUtils.isEmpty(channels)) {
				throw new ChannelNotFoundException("Channel not found");
			}

			return ChannelDTO.of(channels.getFirst());
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to get channel information", e);
		}
	}

}
