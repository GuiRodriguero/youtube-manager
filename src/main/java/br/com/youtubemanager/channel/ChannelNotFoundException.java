package br.com.youtubemanager.channel;

import br.com.youtubemanager.core.YouTubeManagerException;

public class ChannelNotFoundException extends YouTubeManagerException {

	public ChannelNotFoundException(String message) {
		super(message);
	}

}
