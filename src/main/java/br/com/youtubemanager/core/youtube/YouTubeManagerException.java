package br.com.youtubemanager.core.youtube;

public class YouTubeManagerException extends RuntimeException {

	public YouTubeManagerException() {
		super("An unexpected error occurred");
	}

	public YouTubeManagerException(String message) {
		super(message);
	}

	public YouTubeManagerException(String message, Throwable cause) {
		super(message, cause);
	}

}