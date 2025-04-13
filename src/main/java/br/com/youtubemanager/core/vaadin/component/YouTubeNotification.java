package br.com.youtubemanager.core.vaadin.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.AllArgsConstructor;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_END;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class YouTubeNotification {

	public static void show(String message, NotificationVariant theme) {
		Notification notification = Notification.show(message, 3000, TOP_END);

		Div notificationMessage = new Div(message);

		Button closeButton = new Button(new Icon("lumo", "cross"));
		closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
		closeButton.setAriaLabel("Close");
		closeButton.addClickListener(event -> notification.close());

		HorizontalLayout layout = new HorizontalLayout(notificationMessage, closeButton);
		layout.setAlignItems(FlexComponent.Alignment.CENTER);

		notification.add(layout);
		notification.addThemeVariants(theme);
	}

}
