package br.com.youtubemanager.video.web;

import br.com.youtubemanager.core.vaadin.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Find Videos By Country")
@Route(value = "videos/country", layout = MainLayout.class)
class FamousVideosByCountryView extends VerticalLayout {

	public FamousVideosByCountryView() {
		H1 title = new H1("Find the most famous videos by coutry");
		add(title);
	}

}
