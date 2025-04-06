package br.com.youtubemanager.channel.web;

import br.com.youtubemanager.channel.Channel;
import br.com.youtubemanager.channel.ChannelNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ChannelSnippet;
import com.google.api.services.youtube.model.ChannelStatistics;
import com.google.api.services.youtube.model.Thumbnail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static br.com.youtubemanager.core.YoutubeUtils.getYouTubeService;

@Service
class ChannelService {

	@Value("${youtube.api-key}")
	String apiKey;

	public Channel findOne(String channelName) {
		try {
			YouTube.Channels.List request = getYouTubeService().channels()
					.list("snippet,contentDetails,statistics")
					.setForUsername(channelName)
					.setKey(apiKey);

			ChannelListResponse response = request.execute();
			List<com.google.api.services.youtube.model.Channel> channels = response.getItems();

			if(CollectionUtils.isEmpty(channels)) {
				throw new ChannelNotFoundException("Channel not found");
			}

			return convertToMyChannel(channels.getFirst());
		} catch (IOException e) {
			throw new RuntimeException("Failed to get channel information", e);
		}
	}

	private Channel convertToMyChannel(com.google.api.services.youtube.model.Channel youtubeChannel) {
		ChannelSnippet snippet = youtubeChannel.getSnippet();
		ChannelStatistics statistics = youtubeChannel.getStatistics();
		Thumbnail thumbnail = snippet.getThumbnails().getDefault();

		return Channel.of(
				snippet.getTitle(),
				snippet.getCustomUrl(),
				snippet.getDescription(),
				snippet.getPublishedAt().toString(),
				thumbnail.getUrl(),
				snippet.getLocalized().getDescription(),
				snippet.getCountry(),
				statistics.getViewCount().toString(),
				statistics.getSubscriberCount().toString(),
				statistics.getVideoCount().toString()
		);
	}

	private String stubResponse() {
		return """
				{
				  "kind": "youtube#channelListResponse",
				  "etag": "YuxJcvslLXOjAt28OO46lJ7V3Ck",
				  "pageInfo": {
				    "totalResults": 1,
				    "resultsPerPage": 5
				  },
				  "items": [
				    {
				      "kind": "youtube#channel",
				      "etag": "m0sLU58RoHgE2EcPxQYCXgJ0sAE",
				      "id": "UCY8iijN1AkyDCh1Z9akcqUA",
				      "snippet": {
				        "title": "Funky Black Cat",
				        "description": "Meu nome é Guilherme, mas aqui e na internet em geral me chamam de Funky.\\nEu comecei meu canal em 2011 jogando e comentando partidas de Call of Duty, mas logo meu conteúdo abrangeu games em geral. O foco aqui é em séries de let's plays, mas de vez em quando rola algo diferente e até vlogs de assuntos aleatórios.\\n\\n\\"Funky Black Cat\\" era minha gamertag na Xbox Live antes de abrir o canal, que começou com o nome \\"Comentando COD\\".\\nObviamente esse nome era terrível, então eu decidi renomeá-lo pra minha gamertag ainda no começo do canal.\\n\\nO nome \\"Funky\\" vem da música \\"Funky Monks\\" do Red Hot Chili Peppers, e também do estilo musical da banda.\\n\\"Black Cat\\" completa o nome pra soar parecido com \\"Jumping Jack Flash\\", música dos Rolling Stones. O gato preto de quebra acabou se tornando mascote do canal.\\n",
				        "customUrl": "@funkyblackcat",
				        "publishedAt": "2011-07-05T21:51:51Z",
				        "thumbnails": {
				          "default": {
				            "url": "https://yt3.ggpht.com/ytc/AIdro_mPQNjEjSY1qUziqUrjyJQww4Xr8tAVV_2zngc1U7RXHxRa=s88-c-k-c0x00ffffff-no-rj",
				            "width": 88,
				            "height": 88
				          },
				          "medium": {
				            "url": "https://yt3.ggpht.com/ytc/AIdro_mPQNjEjSY1qUziqUrjyJQww4Xr8tAVV_2zngc1U7RXHxRa=s240-c-k-c0x00ffffff-no-rj",
				            "width": 240,
				            "height": 240
				          },
				          "high": {
				            "url": "https://yt3.ggpht.com/ytc/AIdro_mPQNjEjSY1qUziqUrjyJQww4Xr8tAVV_2zngc1U7RXHxRa=s800-c-k-c0x00ffffff-no-rj",
				            "width": 800,
				            "height": 800
				          }
				        },
				        "localized": {
				          "title": "Funky Black Cat",
				          "description": "Meu nome é Guilherme, mas aqui e na internet em geral me chamam de Funky.\\nEu comecei meu canal em 2011 jogando e comentando partidas de Call of Duty, mas logo meu conteúdo abrangeu games em geral. O foco aqui é em séries de let's plays, mas de vez em quando rola algo diferente e até vlogs de assuntos aleatórios.\\n\\n\\"Funky Black Cat\\" era minha gamertag na Xbox Live antes de abrir o canal, que começou com o nome \\"Comentando COD\\".\\nObviamente esse nome era terrível, então eu decidi renomeá-lo pra minha gamertag ainda no começo do canal.\\n\\nO nome \\"Funky\\" vem da música \\"Funky Monks\\" do Red Hot Chili Peppers, e também do estilo musical da banda.\\n\\"Black Cat\\" completa o nome pra soar parecido com \\"Jumping Jack Flash\\", música dos Rolling Stones. O gato preto de quebra acabou se tornando mascote do canal.\\n"
				        },
				        "country": "BR"
				      },
				      "contentDetails": {
				        "relatedPlaylists": {
				          "likes": "",
				          "uploads": "UUY8iijN1AkyDCh1Z9akcqUA"
				        }
				      },
				      "statistics": {
				        "viewCount": "910179164",
				        "subscriberCount": "3210000",
				        "hiddenSubscriberCount": false,
				        "videoCount": "3050"
				      }
				    }
				  ]
				}

				""";
	}

	private String stubNotFound() {
		return """
				{
				  "kind" : "youtube#channelListResponse",
				  "etag" : "RuuXzTIr0OoDqI4S0RU6n4FqKEM",
				  "pageInfo" : {
				    "totalResults" : 0,
				    "resultsPerPage" : 5
				  }
				}
				""";
	}

}
