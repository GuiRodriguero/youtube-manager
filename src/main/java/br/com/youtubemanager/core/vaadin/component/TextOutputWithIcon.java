package br.com.youtubemanager.core.vaadin.component;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class TextOutputWithIcon extends HorizontalLayout {

    public TextOutputWithIcon(Icon icon, String text) {
        this(icon, text, "");
    }

    public TextOutputWithIcon(Icon icon, String text, String title) {
        super();
        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        icon.getElement().setProperty("title", title);
        this.add(icon);
        this.add(new H6(text));
    }

}
