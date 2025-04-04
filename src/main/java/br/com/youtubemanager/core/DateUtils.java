package br.com.youtubemanager.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

	public static String formatDate(String date) {
		if (date == null || date.isEmpty()) {
			return "";
		}
		try {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
			return dateTime.format(outputFormatter);
		}
		catch (DateTimeParseException e) {
			e.printStackTrace();
			return "";
		}
	}

}
