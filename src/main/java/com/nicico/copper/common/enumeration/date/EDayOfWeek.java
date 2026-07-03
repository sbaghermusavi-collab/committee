package com.nicico.copper.common.enumeration.date;

import java.util.Calendar;

public enum EDayOfWeek {
	Sat, Sun, Mon, Tue, Wed, Thu, Fri;

	public static EDayOfWeek get(int dateValue) {
		switch (dateValue) {
			case Calendar.SATURDAY:
				return Sat;

			case Calendar.SUNDAY:
				return Sun;

			case Calendar.MONDAY:
				return Mon;

			case Calendar.TUESDAY:
				return Tue;

			case Calendar.WEDNESDAY:
				return Wed;

			case Calendar.THURSDAY:
				return Thu;

			case Calendar.FRIDAY:
				return Fri;
		}

		throw new RuntimeException("Invalid dateValue: " + dateValue);
	}
}
