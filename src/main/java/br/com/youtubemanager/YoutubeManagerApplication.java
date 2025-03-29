package br.com.youtubemanager;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "youtube-manager")
public class YoutubeManagerApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeManagerApplication.class, args);
	}

}
