package br.com.youtubemanager.channel.web.component;

import br.com.youtubemanager.channel.Channel;
import br.com.youtubemanager.core.vaadin.component.TextOutputWithIcon;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoIcon;

public class ChannelCard extends VerticalLayout {

    public ChannelCard(Channel channel) {
        super();

        this.setSizeFull();
        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.add(getCard(channel));
    }

    private Div getCard(Channel channel) {
        Div card = new Div();
        card.setWidth("35%");
        card.addClassName("channel-card");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setAlignItems(Alignment.START);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        layout.add(channelAvatar(channel));
        layout.add(new TextOutputWithIcon(LumoIcon.CALENDAR.create(), channel.getFormattedPublishedAt(), "Channel creation date"));

        card.add(layout);
        card.add(channel.getDescription());
        card.add(footer(channel));
        return card;
    }

    private HorizontalLayout channelAvatar(Channel channel) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("avatar-layout");
        layout.setAlignItems(Alignment.START);
        layout.setJustifyContentMode(JustifyContentMode.START);
        layout.add(new Avatar(channel.getTitle(), channel.getDefaultThumbnailUrl()));
        layout.add(new H3(channel.getTitle()));
        return layout;
    }

    private HorizontalLayout footer(Channel channel) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("channel-footer");
        layout.setWidthFull();
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        layout.add(new TextOutputWithIcon(LumoIcon.PLAY.create(), channel.getVideoCount(), "Videos"));
        layout.add(new TextOutputWithIcon(LumoIcon.USER.create(), channel.getSubscriberCount(), "Subscribers"));
        layout.add(new TextOutputWithIcon(LumoIcon.EYE.create(), channel.getViewCount(), "Views"));
        return layout;
    }

}
