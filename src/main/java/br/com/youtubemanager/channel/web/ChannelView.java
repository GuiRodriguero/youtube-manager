package br.com.youtubemanager.channel.web;

import br.com.youtubemanager.channel.Channel;
import br.com.youtubemanager.core.vaadin.component.SearchButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@AnonymousAllowed
@PageTitle("Channel")
@Route(value = "channel")
@org.springframework.stereotype.Component
class ChannelView extends VerticalLayout {

    private final TextField field;

    private final SearchButton button;

    @Autowired
    private ChannelService service;

    ChannelView() {
        field = new TextField();
        button = new SearchButton(false)
                .withClickListener(event -> {
                    Channel channel = service.findOne(field.getValue());
                    showChannelProperties(channel);
                });

        add(channelSearchComponents());
    }

    private VerticalLayout channelSearchComponents() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.add(channelTextInput(), button);

        layout.add(new H2("Search for a YouTube channel"));
        layout.add(horizontalLayout);

        return layout;
    }

    private Component channelTextInput() {
        field.setClassName("channel-text-input");
        field.setMaxLength(30);
        field.setWidth("300px");
        field.setI18n(new TextField.TextFieldI18n()
                .setMaxLengthErrorMessage("Maximum length is 30 characters"));
        field.setValueChangeMode(ValueChangeMode.EAGER);
        field.addValueChangeListener(event -> button.setEnabled(!event.getValue().isEmpty()));
        return field;
    }

    private void showChannelProperties(Channel channel) {
        if (channel != null) {
            Div card = new Div();
            card.setWidth("25%");
            card.addClassName("channel-card");
            card.add(channelAvatar(channel));
            card.add(channel.getDescription());
            card.add(channel.getPublishedAt());
            add(card);
        }
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

}
