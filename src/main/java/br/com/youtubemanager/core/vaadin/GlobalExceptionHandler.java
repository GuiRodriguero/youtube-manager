package br.com.youtubemanager.core.vaadin;

import br.com.youtubemanager.core.YouTubeManagerException;
import br.com.youtubemanager.core.vaadin.component.YouTubeNotification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class GlobalExceptionHandler implements VaadinServiceInitListener {

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource()
			.addSessionInitListener(
					sessionInitEvent -> sessionInitEvent.getSession().setErrorHandler(new CustomErrorHandler()));
	}

	private static class CustomErrorHandler implements ErrorHandler {

		@Override
		public void error(ErrorEvent event) {
			Throwable throwable = event.getThrowable();
			while (throwable.getCause() != null) {
				throwable = throwable.getCause();
			}

			String errorMessage = throwable.getMessage();
			if (errorMessage == null || errorMessage.isEmpty() || !(throwable instanceof YouTubeManagerException)) {
				errorMessage = "An unexpected error occurred: " + throwable.getClass().getSimpleName();
			}

			YouTubeNotification.show(errorMessage, NotificationVariant.LUMO_ERROR);

			System.err.println("Uncaught UI exception: ");
			throwable.printStackTrace();
		}

	}

}
