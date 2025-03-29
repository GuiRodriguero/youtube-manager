package br.com.youtubemanager.channel.web;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class ChannelService {

    private void findOne(String channelName, String apiKey) {
        RestTemplate restTemplate = new RestTemplate();

        apiKey = "";
        String url = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&forUsername=" + channelName + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

}
