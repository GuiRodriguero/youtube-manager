package br.com.youtubemanager.core.vaadin.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class SearchButton extends Button {

	public SearchButton(boolean isEnabled) {
		super("Search", new Icon(VaadinIcon.SEARCH));
		this.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		this.setEnabled(isEnabled);
	}

	public SearchButton withClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
		this.addClickListener(listener);
		return this;
	}

}
