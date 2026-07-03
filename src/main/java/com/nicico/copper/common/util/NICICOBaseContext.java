package com.nicico.copper.common.util;

import com.nicico.copper.common.enumeration.date.ECalendar;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Getter
@Setter
@Accessors(chain = true)
@Component
public class NICICOBaseContext {

	@Value("${nicico.locale.calendar:Persian}")
	private String calendar;

	@Value("${nicico.locale.timezone:Asia/Tehran}")
	private String timeZoneId;

	// ------------------------------

	@PostConstruct
	public void init() {
		INSTANCE = this;
	}

	// ---------------

	public ECalendar getCalendarSafely() {
		return ECalendar.valueOf(calendar);
	}

	public String getTimeZoneIdSafely() {
		return timeZoneId;
	}

	// ------------------------------

	private static NICICOBaseContext INSTANCE, DEFAULT;

	static {
		DEFAULT = new NICICOBaseContext();
		DEFAULT.calendar = "Persian";
		DEFAULT.timeZoneId = "Asia/Tehran";
	}

	public static NICICOBaseContext get() {
		return INSTANCE != null ? INSTANCE : DEFAULT;
	}
}
