package com.nicico.copper.common.enumeration.date;

public enum ECalendar {
	Gregorian, Persian, Islamic;

	public EUniCalendar getUniCalendar() {
		switch (this) {
			case Islamic:
				return EUniCalendar.Islamic;
			case Gregorian:
				return EUniCalendar.Gregorian;
			case Persian:
				return EUniCalendar.Persian;
			default:
				throw new RuntimeException("Mapping not found: " + this);
		}
	}

	public static ECalendar of(EUniCalendar uniCalendar) {
		switch (uniCalendar) {
			case Gregorian:
				return Gregorian;
			case Persian:
				return Persian;
			case Islamic:
				return Islamic;
			default:
				throw new RuntimeException("Map Not Found: " + uniCalendar);
		}
	}
}
