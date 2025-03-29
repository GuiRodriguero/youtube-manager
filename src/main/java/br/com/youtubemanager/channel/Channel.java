package br.com.youtubemanager.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Channel {

    private String title;

    private String description;

    private String publishedAt;

    private String defaultThumbnailUrl;

    private String localizedDescription;

    private String country;

    private String viewCount;

    private String subscriberCount;

    private String videoCount;

}
