package br.com.youtubemanager.channel.web;

import br.com.youtubemanager.core.vaadin.components.SearchButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Channel")
@Route(value = "channel")
class ChannelView extends VerticalLayout {

    private final TextField field;

    private final SearchButton button;

    ChannelView() {
        field = new TextField();
        button = new SearchButton(false).withClickListener(event -> search());

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.add(channelTextInput(), button);

        add(channelTitle());
        add(horizontalLayout);
    }

    private Component channelTitle() {
        return new H2("Search for a YouTube channel");
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

    private void search() {
        System.out.println("Searching for channel: " + field.getValue());
    }

}
