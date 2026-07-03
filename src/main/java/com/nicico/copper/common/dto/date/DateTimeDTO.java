package com.nicico.copper.common.dto.date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nicico.copper.common.enumeration.date.ECalendar;
import com.nicico.copper.common.enumeration.date.EDayOfWeek;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString(of = {"year", "month", "day"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class DateTimeDTO {

	private Integer year;
	private Integer month;
	private Integer day;

	private Integer hour = 0;
	private Integer minute = 0;
	private Integer second = 0;

	// ------------------------------

	@Getter
	@Setter
	@Accessors(chain = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class DateTimeRq extends DateTimeDTO {
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class DateTimeRs extends DateTimeDTO {
		private EDayOfWeek dayOfWeek;
		private ECalendar calendar;
	}

	// ------------------------------

	@Getter
	@Setter
	@Accessors(chain = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class DateTimeStrRq {
		private String date;
		private String time;
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@ToString(of = {"date", "time", "calendar"})
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class DateTimeStrRs {
		private String date;
		private String time;
		private ECalendar calendar;
	}
}
