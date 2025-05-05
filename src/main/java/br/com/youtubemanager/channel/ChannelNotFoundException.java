package br.com.youtubemanager.channel;

import br.com.youtubemanager.core.youtube.YouTubeManagerException;

public class ChannelNotFoundException extends YouTubeManagerException {

	public ChannelNotFoundException(String message) {
		super(message);
	}

}
