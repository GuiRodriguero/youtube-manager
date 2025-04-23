package br.com.youtubemanager.core.vaadin;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

	public MainLayout() {
		createHeader();
		createDrawer();
	}

	private void createHeader() {
		H1 logo = new H1("YouTube Manager");
		logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);

		HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo);
		header.setWidthFull();

		addToNavbar(header);
	}

	private void createDrawer() {
		SideNav sideNav = new SideNav();

		sideNav.addItem(new SideNavItem("Home", "home", VaadinIcon.HOME.create()));
		sideNav.addItem(channelGroup());
		sideNav.addItem(videoGroup());

		addToDrawer(sideNav);
	}

	private SideNavItem channelGroup() {
		SideNavItem channelGroup = new SideNavItem("Channels");
		channelGroup.setPrefixComponent(VaadinIcon.USERS.create());

		SideNavItem searchChannelItem = new SideNavItem("Search Channels", "channel", VaadinIcon.SEARCH.create());
		channelGroup.addItem(searchChannelItem);

		return channelGroup;
	}

	private SideNavItem videoGroup() {
		SideNavItem videoGroup = new SideNavItem("Videos");
		videoGroup.setPrefixComponent(VaadinIcon.PLAY_CIRCLE.create());

		SideNavItem famousByCountryItem = new SideNavItem("Most famous videos by country", "videos/country",
				VaadinIcon.FLAG.create());
		videoGroup.addItem(famousByCountryItem);

		return videoGroup;
	}

}
