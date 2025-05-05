package br.com.youtubemanager.channel;

import br.com.youtubemanager.core.youtube.YouTubeManagerException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ChannelService {

	private final YouTube youtube;

	public ChannelDTO findOne(String channelName) {
		List<Channel> channels;
		try {
			channels = youtube.channels()
				.list("snippet,contentDetails,statistics")
				.setForUsername(channelName)
				.execute()
				.getItems();
		}
		catch (IOException e) {
			throw new YouTubeManagerException();
		}

		if (CollectionUtils.isEmpty(channels)) {
			throw new ChannelNotFoundException("Channel not found");
		}

		return ChannelDTO.of(channels.getFirst());
	}

}
