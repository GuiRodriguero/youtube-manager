package br.com.youtubemanager.channel.web.component;

import br.com.youtubemanager.channel.Channel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Tag(value = "ym-channel-card")
public class ChannelCard extends Component {

    public ChannelCard(Channel channel) {
        Div card = new Div();
        card.setWidth("25%");
        card.addClassName("channel-card");
        card.add(channelAvatar(channel));
        card.add(channel.getDescription());
        card.add(channel.getPublishedAt());
    }

    private HorizontalLayout channelAvatar(Channel channel) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("avatar-layout");
        layout.setAlignItems(FlexComponent.Alignment.START);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        layout.add(new Avatar(channel.getTitle(), channel.getDefaultThumbnailUrl()));
        layout.add(new H3(channel.getTitle()));
        return layout;
    }

}
