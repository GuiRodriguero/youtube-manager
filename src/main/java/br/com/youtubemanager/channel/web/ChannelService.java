package br.com.youtubemanager.channel.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class ChannelService {

    @Value("${youtube.api-key}")
    String apiKey;

    public void findOne(String channelName) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet,contentDetails,statistics&forUsername=" + channelName + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

}
