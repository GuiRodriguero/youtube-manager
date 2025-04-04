package br.com.youtubemanager.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static br.com.youtubemanager.core.DateUtils.formatDate;

@Getter
@AllArgsConstructor(staticName = "of")
public class Channel {

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
}
