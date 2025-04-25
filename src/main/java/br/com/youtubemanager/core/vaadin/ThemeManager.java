package br.com.youtubemanager.core.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ThemeManager {

	public static void setTheme(boolean dark) {
		ThemeList themeList = UI.getCurrent().getElement().getThemeList();

		if (dark) {
			themeList.add(Lumo.DARK);
		}
		else {
			themeList.remove(Lumo.DARK);
		}
	}

	public static boolean isDarkMode() {
		ThemeList themeList = UI.getCurrent().getElement().getThemeList();
		return themeList.contains(Lumo.DARK);
	}

}