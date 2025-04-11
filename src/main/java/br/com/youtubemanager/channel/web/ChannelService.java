package br.com.youtubemanager.channel.web;

import br.com.youtubemanager.channel.ChannelDTO;
import br.com.youtubemanager.channel.ChannelNotFoundException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
class ChannelService {

	private final YouTube youtube;

	public ChannelDTO findOne(String channelName) {
		try {
			List<Channel> channels = youtube.channels()
				.list("snippet,contentDetails,statistics")
				.setForUsername(channelName)
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
