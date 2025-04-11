package br.com.youtubemanager.core.vaadin.component;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import static com.vaadin.flow.component.notification.Notification.Position.MIDDLE;

public class YouTubeNotification {

	public static void show(String errorMessage, NotificationVariant theme) {
		Notification errorNotification = Notification.show(errorMessage, 3000, MIDDLE);
		errorNotification.addThemeVariants(theme);
	}

}
