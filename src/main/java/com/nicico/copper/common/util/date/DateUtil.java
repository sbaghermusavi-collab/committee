package com.nicico.copper.common.util.date;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("all")
@Component
public class DateUtil {

	private final NumberFormat numberFormat;
	public static final String KH_PATTERN="%d/%02d/%02d";
	public static final String Mi_PATTERN="%d-%02d-%02d";

	public DateUtil() {
		this.numberFormat = new RuleBasedNumberFormat(
			"صفر;" +
				"یک;" +
				"دو;" +
				"سه;" +
				"چهار;" +
				"پنج;" +
				"شش;" +
				"هفت;" +
				"هشت;" +
				"نه;" +
				"ده;" +
				"یازده;" +
				"دوازده;" +
				"سیزده;" +
				"چهارده;" +
				"پانزده;" +
				"شانزده;" +
				"هفده;" +
				"هجده;" +
				"نوزده;" +
				"20: بیست[ و >>];" +
				"30: سی[ و >>];" +
				"40: چهل[ و >>];" +
				"50: پنجاه[ و >>];" +
				"60: شصت[ و >>];" +
				"70: هفتاد[ و >>];" +
				"80: هشتاد[ و >>];" +
				"90: نود[ و >>];" +
				"100: صد[ و >>];" +
				"200: دویست[ و >>];" +
				"300: سیصد[ و >>];" +
				"400: چهارصد[ و >>];" +
				"500: پانصد[ و >>];" +
				"600: ششصد[ و >>];" +
				"700: هفتصد[ و >>];" +
				"800: هشتصد[ و >>];" +
				"900: نهصد[ و >>];" +
				"1000: << هزار[ و >>];" +
				"1,000,000: << میلیون[ و >>];" +
				"1,000,000,000: << میلیارد[ و >>];" +
				"1,000,000,000,000,000: خارج از محدوده!;"
		);
	}

	// ------------------------------

	public static String convertKhToMi1(String date) {
		String[] s = date.split("/");
		String finalDate = "";
		try {
			if (s.length == 3) {
				if (Integer.parseInt(s[0]) < 1 && Integer.parseInt(s[1]) < 1 && Integer.parseInt(s[2]) < 1)
					return null;
				else
					finalDate = convertKhToMi1(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return finalDate;
	}

	public static String convertMiToKh(String date) {
		String[] s = date.split("-");
		try {
			if (s.length == 3) {
				if (Integer.parseInt(s[0]) < 1 && Integer.parseInt(s[1]) < 1 && Integer.parseInt(s[2]) < 1)
					return null;
				else
					convertMiToKh(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			} else
				return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return convertMiToKh(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
	}

	public static String convertMiToKh2(String date)//****************yyyy/mm/dd
	{
		if (date == null || date.equals(""))
			return "";
		String[] str = date.split("/");
		String[] s = new String[3];
		s[0] = str[1];
		s[1] = str[2];
		s[2] = str[0];
		String ret = "";
		try {
			if (s.length == 3) {
				if (Integer.parseInt(s[0]) < 1 && Integer.parseInt(s[1]) < 1 && Integer.parseInt(s[2]) < 1)
					return null;
				else if (s[2].length() > 2)
					ret = convertMiToKh(Integer.parseInt(s[2]), Integer.parseInt(s[0]), Integer.parseInt(s[1]));
				else
					ret = convertMiToKh(Integer.parseInt("20" + s[2]), Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String convertKhToMi(int year, int month, int day) {
		return convertKhToMi1(year,month,day);
	}

	public static String convertKhToMi1(int year, int month, int day) {
		int[] date = khToMi(year, month, day);
		return Mi_PATTERN.formatted(date[0],date[1],date[2]);
	}
	public static int[] khToMi(int jy, int jm, int jd) {
		jy += 1595;
		int[] out = {
				0,
				0,
				-355668 + (365 * jy) + (((int) (jy / 33)) * 8) + ((int) (((jy % 33) + 3) / 4)) + jd + ((jm < 7) ? (jm - 1) * 31 : ((jm - 7) * 30) + 186)
		};
		out[0] = 400 * ((int) (out[2] / 146097));
		out[2] %= 146097;
		if (out[2] > 36524) {
			out[0] += 100 * ((int) (--out[2] / 36524));
			out[2] %= 36524;
			if (out[2] >= 365) out[2]++;
		}
		out[0] += 4 * ((int) (out[2] / 1461));
		out[2] %= 1461;
		if (out[2] > 365) {
			out[0] += (int) ((out[2] - 1) / 365);
			out[2] = (out[2] - 1) % 365;
		}
		int[] sal_a = { 0, 31, ((out[0] % 4 == 0 && out[0] % 100 != 0) || (out[0] % 400 == 0)) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		for (out[2]++; out[1] < 13 && out[2] > sal_a[out[1]]; out[1]++) out[2] -= sal_a[out[1]];
		return out;
	}

	public static String convertMiToKh(int year, int month, int day) {
		int[] date = miToKh(year, month, day);
		return KH_PATTERN.formatted(date[0],date[1],date[2]);
	}
	public static int[] miToKh(int gy, int gm, int gd) {
		int[] out = {
				(gm > 2) ? (gy + 1) : gy,
				0,
				0
		};
		{
			int[] g_d_m = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
			out[2] = 355666 + (365 * gy) + ((int) ((out[0] + 3) / 4)) - ((int) ((out[0] + 99) / 100)) + ((int) ((out[0] + 399) / 400)) + gd + g_d_m[gm - 1];
		}
		out[0] = -1595 + (33 * ((int) (out[2] / 12053)));
		out[2] %= 12053;
		out[0] += 4 * ((int) (out[2] / 1461));
		out[2] %= 1461;
		if (out[2] > 365) {
			out[0] += (int) ((out[2] - 1) / 365);
			out[2] = (out[2] - 1) % 365;
		}
		if (out[2] < 186) {
			out[1] = 1 + (int)(out[2] / 31);
			out[2] = 1 + (out[2] % 31);
		} else {
			out[1] = 7 + (int)((out[2] - 186) / 30);
			out[2] = 1 + ((out[2] - 186) % 30);
		}
		return out;
	}

	public static String todayDate() {
		Calendar c = Calendar.getInstance();
		Date todayDate = new Date(c.getTimeInMillis());
		String sDate = new String(todayDate.toString());
		String[] mDate = sDate.split("-");
		DateUtil dateUtil = new DateUtil();
		String khDate = dateUtil.convertMiToKh(Integer.parseInt(mDate[0]), Integer.parseInt(mDate[1]), Integer.parseInt(mDate[2]));
		return khDate;
	}

	public static String todayDateMiladi() {
		Calendar c = Calendar.getInstance();
		Date todayDate = new Date(c.getTimeInMillis());
		String sDate = new String(todayDate.toString());
		String[] mDate = sDate.split("-");
		String miDate = mDate[0] + "/" + mDate[1] + "/" + mDate[2];
		return miDate;
	}

	public static String getYear() {
		Calendar c = Calendar.getInstance();
		Date todayDate = new Date(c.getTimeInMillis());
		String sDate = new String(todayDate.toString());
		String[] mDate = sDate.split("-");
		String khDate = convertMiToKh(Integer.parseInt(mDate[0]), Integer.parseInt(mDate[1]), Integer.parseInt(mDate[2]));
		String[] mDate1 = khDate.split("/");
		return mDate1[0];
	}

	public String convertKhToMi(String date) {
		String[] s = date.split("/");
		String finalDate = "";
		try {
			if (s.length == 3) {
				if (Integer.parseInt(s[0]) < 1 && Integer.parseInt(s[1]) < 1 && Integer.parseInt(s[2]) < 1)
					return null;
				else
					finalDate = convertKhToMi(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return finalDate;
	}

	public Timestamp todayMiladiDate() {
		return null;
	}

	public String getThisTime() {
		Calendar c = Calendar.getInstance();
		Long hour = new Long(c.getTime().getHours());
		Long minute = new Long(c.getTime().getMinutes());
		Long second = new Long(c.getTime().getSeconds());
		String ho = hour.toString();
		String min = minute.toString();
		String sec = second.toString();
		if (c.get(Calendar.AM_PM) == 0)
			;
		else
			hour += 12;
		if (hour.longValue() < 10)
			ho = "0" + ho;
		if (minute.longValue() < 10)
			min = "0" + min;
		if (second.longValue() < 10)
			sec = "0" + sec;
		String thisTime = ho + ":" + min + ":" + sec;
		return thisTime;
	}

	public String ConvertMiladyToFavoriteFormat(java.util.Date inputDate, String Format) {
		int dayNumber = inputDate.getDay();
		String[] toadays =
			{"يكشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه", "شنبه"};
		String dayOfWeek = "";
		for (int i = 0; i < 7; i++) {
			if (dayNumber == i)
				dayOfWeek = toadays[i];
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = dateFormat.format(inputDate);
		String[] mDate = sDate.split("-");
		DateUtil dateUtil = new DateUtil();
		String khDate = dateUtil.convertMiToKh(Integer.parseInt(mDate[0]), Integer.parseInt(mDate[1]), Integer.parseInt(mDate[2]));
		String[] khDates = khDate.split("/");

		int intmonth = Integer.parseInt(khDates[1]);
		String[] khmonthYear =
			{"يكشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه", "شنبه"};
		String monthYear = "";
		for (int i = 1; i < 13; i++) {
			if (intmonth == i)
				monthYear = khmonthYear[i - 1];
		}

		String ckhDate = dayOfWeek + " " + khDates[2] + " " + monthYear + " " + khDates[0];
		return ckhDate;
	}

	public String ConvertMiladyToFavoriteFormat_(java.util.Date inputDate, String Format) {
		int dayNumber = inputDate.getDay();
		String[] toadays =
			{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		String dayOfWeek = "";
		for (int i = 0; i < 7; i++) {
			if (dayNumber == i)
				dayOfWeek = toadays[i];
		}
		// /////
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = dateFormat.format(inputDate);
		String[] sDates = sDate.split("-");

		int intmonth = Integer.parseInt(sDates[1]);
		String[] khmonthYear =
			{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String monthYear = "";
		for (int i = 1; i < 13; i++) {
			if (intmonth == i)
				monthYear = khmonthYear[i - 1];
		}

		String ckhDate = dayOfWeek + ", " + monthYear + " " + sDates[2] + ", " + sDates[0];
		return ckhDate;
	}

	public String numberToString(String number) {
		return numberFormat.format(new BigDecimal(number));
	}

	public String format(BigDecimal number) {
		return numberFormat.format(number);
	}

	public static void main(String[] args) {
		DateUtil util = new DateUtil();
		System.out.println(util.numberToString("12100462739512"));
		System.out.println(util.numberToString("12140462739512"));
		System.out.println(util.numberToString("12149462739512"));
		System.out.println(util.numberToString("10000000000000"));
		System.out.println(util.numberToString("10000000000001"));
		System.out.println(util.numberToString("12100439512"));
		System.out.println(util.numberToString("12100412"));
		System.out.println(util.numberToString("912100462739512"));
		System.out.println(util.numberToString("1912100462739512"));
	}

	public String dateAdd(String currentDate, int add) {
		String miladiDate = convertKhToMi(currentDate);

		try {
			String[] all = miladiDate.split("-");
			Date mil = new Date(Integer.parseInt(all[0]) - 1900, Integer.parseInt(all[1]) - 1, Integer.parseInt(all[2]));
			mil.setDate(mil.getDate() + add);
			currentDate = convertMiToKh(mil.toString());
		} catch (Exception ex) {
			if (add > 0)
				currentDate = dateAdd(currentDate, add - 1);
		}
		return currentDate;
	}

	public String dateAddMi(String currentDate, int add) {
		String miladiDate = currentDate;

		try {
			String[] all = miladiDate.split("-");
			Date mil = new Date(Integer.parseInt(all[0]) - 1900, Integer.parseInt(all[1]) - 1, Integer.parseInt(all[2]));
			mil.setDate(mil.getDate() + add);
			currentDate = mil.toString();
		} catch (Exception ex) {
		}
		return currentDate;
	}

	public Long DateDifferent(String startDate, String endDate, String type) {
		String year, month, day;
		String[] stringDate = null;
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		stringDate = new String[startDate.split(
			"-").length];

		stringDate = startDate.split("-");

		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];
		calendar1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		stringDate = endDate.split("-");

		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];

		calendar2.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);
		if (type.equals("d"))
			return diffDays;
		else if (type.equals("h"))
			return diffHours;
		else if (type.equals("m"))
			return diffMinutes;
		else return diffSeconds;
	}

	public Long DateDifferent(String startDate, String endDate, String type, String splitChar) {

		String year, month, day;
		String[] stringDate = null;
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		stringDate = new String[startDate.split(
			splitChar).length];

		stringDate = startDate.split(splitChar);

		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];
		calendar1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		stringDate = endDate.split(splitChar);

		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];

		calendar2.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);
		if (type.equals("d"))
			return diffDays;
		else if (type.equals("h"))
			return diffHours;
		else if (type.equals("m"))
			return diffMinutes;
		else return diffSeconds;

	}

	public String NextDateKh(String paramDate) {

		String year, month, day;

		String[] stringDate = null;
		Calendar calendar1 = Calendar.getInstance();

		paramDate = convertKhToMi(paramDate);

		stringDate = new String[paramDate.split(
			"/").length];

		stringDate = paramDate.split("-");

		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];
		calendar1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		calendar1.add(Calendar.DATE, 1);
		paramDate = calendar1.get(Calendar.YEAR) + "-" + calendar1.get(Calendar.MONTH) + "-" + calendar1.get(Calendar.DATE);
		paramDate = convertMiToKh(paramDate);
		return paramDate;

	}

	public String PreviousDateKh(String paramDate) {

		String year, month, day;

		String[] stringDate = null;
		Calendar calendar1 = Calendar.getInstance();

		paramDate = convertKhToMi(paramDate);

		stringDate = new String[paramDate.split(
			"-").length];

		stringDate = paramDate.split("-");

		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		calendar1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		calendar1.add(Calendar.DAY_OF_YEAR, -1);
		java.util.Date previousDate = calendar1.getTime();
		String result = dateFormat.format(previousDate);

		paramDate = calendar1.get(Calendar.YEAR) + "-" + calendar1.get(Calendar.MONTH) + "-" + calendar1.get(Calendar.DATE);
		paramDate = convertMiToKh(paramDate);
		return paramDate;
	}

	public String ConvertShamisiToFavoriteFormatNew(String inputDate, String Format, String separator) {
		String year, month, day;

		String[] stringDate = null;
		Calendar calendar1 = Calendar.getInstance();

		inputDate = convertKhToMi(inputDate);

		stringDate = new String[inputDate.split(
			separator).length];

		stringDate = inputDate.split(separator);

		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];
		calendar1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		int dayNumber = 0;
		dayNumber = calendar1.get(Calendar.DAY_OF_WEEK);
		String[] toadays =
			{"دو شنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه", "شنبه", "يک شنبه"};
		String dayOfWeek = "";
		for (int i = 0; i < 7; i++) {
			if (dayNumber == i)
				dayOfWeek = toadays[i];
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = dateFormat.format(inputDate);
		String[] mDate = sDate.split("-");
		String khDate = convertMiToKh(Integer.parseInt(mDate[0]), Integer.parseInt(mDate[1]), Integer.parseInt(mDate[2]));
		String[] khDates = khDate.split("/");

		int intmonth = Integer.parseInt(khDates[1]);
		String[] khmonthYear =
			{"فروردين", "ارديبهشت", "خرداد", "تير", "مرداد", "شهريور", "مهر", "آبان", "آذر", "دي", "بهمن", "اسفند"};
		String monthYear = "";
		for (int i = 1; i < 13; i++) {
			if (intmonth == i)
				monthYear = khmonthYear[i - 1];
		}

		String ckhDate = dayOfWeek + ";" + khDates[2] + ";" + monthYear + ";" + khDates[0];
		return ckhDate;
	}

	public String ConvertMiladiToShamsiFavoriteFormat(String inputDate, String separator) {
		String year, month, day;

		String[] stringDate = null;
		Calendar calendar1 = Calendar.getInstance();
		stringDate = new String[inputDate.split(
			separator).length];
		stringDate = inputDate.split(separator);
		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];
		calendar1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		int dayNumber = 2;
		dayNumber = calendar1.get(Calendar.DAY_OF_WEEK);
		String[] toadays =
			{"دوشنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه", "شنبه", "يکشنبه"};
		String dayOfWeek = "";
		for (int i = 1; i <= 7; i++) {
			if (dayNumber == i) {
				dayOfWeek = toadays[i - 1];
				break;
			}
		}

		String khDate = convertMiToKh(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		String[] khDates = khDate.split(separator);
		int intmonth = Integer.parseInt(khDates[1]);
		String[] khmonthYear =
			{"فروردين", "ارديبهشت", "خرداد", "تير", "مرداد", "شهريور", "مهر", "آبان", "آذر", "دي", "بهمن", "اسفند"};
		String monthYear = "";
		for (int i = 1; i < 13; i++) {
			if (intmonth == i) {
				monthYear = khmonthYear[i - 1];
				break;
			}
		}

		String ckhDate = dayOfWeek + ";" + khDates[2] + ";" + monthYear + ";" + intmonth + ";" + khDates[0];
		return ckhDate;
	}

	public String ConvertMiladiToMiladiFavoriteFormat(String inputDate, String separator) {
		String year, month, day;

		String[] stringDate = null;
		Calendar calendar1 = Calendar.getInstance();
		stringDate = new String[inputDate.split(
			separator).length];
		stringDate = inputDate.split(separator);
		year = stringDate[0];
		month = stringDate[1];
		day = stringDate[2];
		calendar1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		int dayNumber = 0;
		dayNumber = calendar1.get(Calendar.DAY_OF_WEEK);
		String[] toadays =
			{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		String dayOfWeek = "";
		for (int i = 1; i <= 7; i++) {
			if (dayNumber == i) {
				dayOfWeek = toadays[i - 1];
				break;
			}
		}

		int intmonth = calendar1.get(Calendar.MONTH);
		String[] khmonthYear =
			{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String monthYear = "";
		for (int i = 1; i < 13; i++) {
			if (intmonth == i) {
				monthYear = khmonthYear[i - 1];
				break;
			}
		}

		String ckhDate = dayOfWeek + ";" + Integer.toString(calendar1.get(Calendar.DATE)) + ";" + monthYear + ";" + intmonth + ";" + Integer.toString(calendar1.get(Calendar.YEAR));
		return ckhDate;
	}

	public String returnDate(Long year, Long month, Long day, String seperator) {
		String ret = "";
		ret = year.toString() + seperator;
		if (month < 10)
			ret = ret + "0" + month.toString() + seperator;
		else
			ret = ret + month.toString() + seperator;
		if (day < 10)
			ret = ret + "0" + day.toString();
		else
			ret = ret + day.toString();
		return ret;
	}

	public String returnKhMonth(Long month) {
		String ret = "";
		if (month == 1) ret = "فروردين";
		else if (month == 2) ret = "ارديبهشت";
		else if (month == 3) ret = "خرداد";
		else if (month == 4) ret = "تير";
		else if (month == 5) ret = "مرداد";
		else if (month == 6) ret = "شهريور";
		else if (month == 7) ret = "مهر";
		else if (month == 8) ret = "آبان";
		else if (month == 9) ret = "آذر";
		else if (month == 10) ret = "دي";
		else if (month == 11) ret = "بهمن";
		else if (month == 12) ret = "اسفند";

		return ret;
	}

	public String returnMiMonth(Long month) {

		String ret = "";
		if (month == 1) ret = "January";
		else if (month == 2) ret = "February";
		else if (month == 3) ret = "March";
		else if (month == 4) ret = "April";
		else if (month == 5) ret = "May";
		else if (month == 6) ret = "June";
		else if (month == 7) ret = "July";
		else if (month == 8) ret = "August";
		else if (month == 9) ret = "September";
		else if (month == 10) ret = "October";
		else if (month == 11) ret = "November";
		else if (month == 12) ret = "December";

		return ret;
	}

	public Long returnMiMonthString(String month) {
		Long ret = null;
		if (month.equalsIgnoreCase("JAN")) ret = new Long(1);
		else if (month.equalsIgnoreCase("FEB")) ret = new Long(2);
		else if (month.equalsIgnoreCase("MAR")) ret = new Long(3);
		else if (month.equalsIgnoreCase("APR")) ret = new Long(4);
		else if (month.equalsIgnoreCase("MAY")) ret = new Long(5);
		else if (month.equalsIgnoreCase("JUN")) ret = new Long(6);
		else if (month.equalsIgnoreCase("JUL")) ret = new Long(7);
		else if (month.equalsIgnoreCase("AUG")) ret = new Long(8);
		else if (month.equalsIgnoreCase("SEP")) ret = new Long(9);
		else if (month.equalsIgnoreCase("OCT")) ret = new Long(10);
		else if (month.equalsIgnoreCase("NOV")) ret = new Long(11);
		else if (month.equalsIgnoreCase("DEC")) ret = new Long(12);
		else new Long(0);
		return ret;
	}

	public String[] getdatePart(String myDate) {
		String[] result = new String[3];
		result[0] = myDate.substring(0, 4);
		result[1] = myDate.substring(5, 7);
		result[2] = myDate.substring(8, 10);

		return result;

	}

	public int getdayInMonth(int year, int month) {
		int days = 0;
		int newMOnth = month;
		String startDate = year + "/" + (month < 10 ? "0" + month : month) + "/01";

		while (month == newMOnth && days < 31) {
			days++;
			String miladiDate = convertKhToMi(startDate);
			try {
				String[] all = miladiDate.split("-");
				Date mil = new Date(Integer.parseInt(all[0]) - 1900, Integer.parseInt(all[1]) - 1, Integer.parseInt(all[2]));
				mil.setDate(mil.getDate() + 1);
				String newDate = convertMiToKh(mil.toString());
				startDate = newDate;
				newMOnth = Integer.parseInt(newDate.split("/")[1]);
			} catch (Exception ex) {
			}

		}
		return days;
	}

	public String[] getMonthBetween2Date(String startDate, String finishDate) {
		String[] monthDate = null;
		try {
			int smonth = Integer.parseInt(startDate.substring(5, 7));
			int fmonth = Integer.parseInt(finishDate.substring(5, 7));
			int syear = Integer.parseInt(startDate.substring(0, 4));
			int fyear = Integer.parseInt(finishDate.substring(0, 4));
			int arrayLen = 0; // for start and end date
			int currentMonth = 0;
			int i, j;
			String temp = "";
			String[] datePart = new String[3];

			if (syear == fyear)
				arrayLen = fmonth - smonth;
			else {
				arrayLen = 12 - smonth;

				arrayLen = arrayLen + fmonth;
				if (fyear - syear > 1)
					arrayLen = arrayLen + (fyear - syear - 1) * 12;

			}
			arrayLen = arrayLen + 2;
			monthDate = new String[arrayLen];
			monthDate[0] = startDate;
			monthDate[arrayLen - 1] = finishDate;
			if (monthDate.length > 2) {

				currentMonth = smonth;
				datePart = getdatePart(startDate);
				i = arrayLen;
				j = 1;

				while (i > 1) {
					currentMonth = currentMonth + 1;
					if (currentMonth < 13) {
						if (currentMonth < 10)
							temp = "0" + Integer.toString(currentMonth);
						else
							temp = Integer.toString(currentMonth);
						monthDate[j] = datePart[0] + "/" + temp + "/01";

					} else {
						currentMonth = 1;
						temp = "0" + Integer.toString(currentMonth);
						datePart[0] = Integer.toString((Integer
							.parseInt(datePart[0]) + 1));
						monthDate[j] = datePart[0] + "/" + temp + "/01";
					}
					j = j + 1;
					i = i - 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthDate;
	}

	public String calculatePatternDate(String startDatePlan, String reportDateUnit, int projectTaskReportNumber) {

		String patternReportDate = "";

		Long year = new Long(startDatePlan.substring(0, 4));
		Long month = new Long(startDatePlan.substring(5, 7));
		Long day = new Long(startDatePlan.substring(8, 10));

		Long targetCheckPointVal = new Long(projectTaskReportNumber + 1);
		Long Counter = new Long(day);

		if (reportDateUnit.equals("year"))
			for (int i = 0; i < targetCheckPointVal.longValue(); i++, year++) ;

		else if (reportDateUnit.equals("month")) {
			for (int i = 0; i < targetCheckPointVal.longValue(); i++) {
				if (month.longValue() == 12) {
					year++;
					month = new Long(1);
				} else
					month++;
			}
		} else if (reportDateUnit.equals("week") || reportDateUnit.equals("day")) {
			if (reportDateUnit.equals("week"))
				targetCheckPointVal = (targetCheckPointVal.longValue() * 7);

			for (int i = 0; i < targetCheckPointVal.longValue(); i++) {
				if (month.longValue() < 7 && day.longValue() == 31) {
					month++;
					day = new Long(1);
				} else if (month.longValue() >= 7 && month.longValue() <= 11 && day.longValue() == 30) {
					month++;
					day = new Long(1);
				} else if (month.longValue() == 12 && day.longValue() == 29) {
					year++;
					month = new Long(1);
					day = new Long(1);
				} else
					day++;

			}

		}
		patternReportDate = year + "/" + ((month >= 10) ? month : ("0" + month)) + "/" + ((day >= 10) ? day : ("0" + day));

		return patternReportDate;
	}

	public int getTimeDiffrentBetweenTowTimesInMinute(String StartTime, String endTime) {
		long def = 0;

		try {
			Long startTimeInMin = (new Long(StartTime.split(":")[0]) * 3600 * 1000)
				+ (new Long(StartTime.split(":")[1]) * 60 * 1000);
			Long endTimeInMin = (new Long(
				endTime.split(":")[0])
				* 3600 * 1000)
				+ (new Long(endTime
				.split(":")[1]) * 60 * 1000);
			def = (endTimeInMin - startTimeInMin) / 60000;
		} catch (NumberFormatException e) {
		}
		return (int) def;
	}

	public int getTimeInMinute(String time) {
		long timeInMin = 0;

		try {
			timeInMin = (new Long(time
				.split(":")[0]) * 3600 * 1000)
				+ (new Long(time
				.split(":")[1]) * 60 * 1000);
		} catch (NumberFormatException e) {
		}
		return (int) timeInMin;
	}

	public String addMonthToKhDate(String inputDate, Long month) {
		String DateMiladi = convertKhToMi1(inputDate);
		String[] seperateDate = DateMiladi.split("-");
		Calendar startDate = Calendar.getInstance();
		startDate.set(Integer.parseInt(seperateDate[0]),
			Integer.parseInt(seperateDate[1]),
			Integer.parseInt(seperateDate[2]));
		startDate.add(Calendar.MONTH, month.intValue());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String correctMilFormat = sdf.format(startDate.getTime()).toString();
		String newDate = convertMiToKh2(correctMilFormat);
		return newDate;
	}

	public int compareDates(String firstDate, String lastDate) {
		if (firstDate == lastDate)
			return 0;

		// compare year
		if (Long.valueOf(firstDate.substring(0, 4)) < Long.valueOf(lastDate.substring(0, 4)))
			return -1;
		else if (Long.valueOf(firstDate.substring(0, 4)) > Long.valueOf(lastDate.substring(0, 4)))
			return 1;

			// compare month
		else if (Long.valueOf(firstDate.substring(5, 7)) < Long.valueOf(lastDate.substring(5, 7)))
			return -1;
		else if (Long.valueOf(firstDate.substring(5, 7)) < Long.valueOf(lastDate.substring(5, 7)))
			return 1;

			// compare day
		else if (Long.valueOf(firstDate.substring(8, 10)) < Long.valueOf(lastDate.substring(8, 10)))
			return -1;
		else
			return 1;
	}

	public int pastDay(int year, int month, int day) {
		int[] khMonth =
			{0, 31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
		if (((((((year - ((year > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682) {
			khMonth[12] = 30;
		}
		int addmonth = 0;
		for (int x = 1; x < month; x++) {
			addmonth += khMonth[x];
		}
		int year2 = 0;
		for (int z = 1; z < year; z++) {
			if (((((((z - ((z > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682) {
				year2 += 366;
				continue;
			} else {
				year2 += 365;
				continue;
			}
		}
		int total = year2 + addmonth + day + 226895;
		return total;
	}

	public int dateLength(String date) {
		int length = 0;
		String[] s = date.split("/");
		try {
			if (s.length == 3) {
				if (Integer.parseInt(s[0]) < 1 && Integer.parseInt(s[1]) < 1 && Integer.parseInt(s[2]) < 1)
					return 0;
				else
					length = pastDay(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return length;
	}

	public int diff(String date1, String date2) {
		return dateLength(date2) - dateLength(date1);
	}
}


	/*public String numberToString(String number) {
		String[] a =
				{"", "يك", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه"};
		String[] b =
				{"ده ", "يازده", "دوازده", "سيزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"};
		String[] c =
				{" ", " ", "بيست", "سي", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"};
		String[] d =
				{" ", "صد", "دويست", "سيصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"};
		String[] e =
				{"", "هزار", "ميليون", "ميليارد", "بيليارد"};
		String input = number;
		String finall = " ";
		int m = 0;
		for (int n = 0; n < input.length(); n += 3) {
			int inputIndex = 0;
			if ((input.length() - (n + 3)) > 0)
				inputIndex = (input.length() - (n + 3));
			String length3 = input.substring(inputIndex, (input.length() - n));
			String length4 = input.substring(inputIndex, (input.length() - n));
			int length3Index = 0;
			if ((length3.length() - (2)) > 0)
				length3Index = (length3.length() - (2));
			String length2 = length3.substring(length3Index, (length3.length()));
			String length5 = input.substring(0, inputIndex);
			int inputNumber = Integer.parseInt(length2);
			String inputTxt = String.valueOf(Integer.parseInt(length2));
			if (inputNumber == 0 && length3.charAt(0) == '0' && length5.length() == 0) {
				m++;
				continue;
			}
			int d2Index = 0;
			if (inputTxt.length() - 2 > 0)
				d2Index = inputTxt.length() - 2;
			int d3Index = 0;
			if (length3.length() - 3 > 0)
				d3Index = length3.length() - 3;
			String d1 = "" + inputTxt.charAt(inputTxt.length() - 1);
			String d2 = "" + inputTxt.charAt(d2Index);
			String d3 = "" + length3.charAt(d3Index);
			String yekan = "";
			String dahgan = "";
			String sadgan = "";
			String total = "";
			String total1 = "";
			if (inputTxt.length() == 1) {
				for (int h = 0; h < a.length; h++) {
					if (Integer.parseInt(d1) == h) {
						total = a[h];
						break;
					}
				}
			} else if (Integer.parseInt(d2) >= 2) {
				for (int i = 0; i < a.length; i++) {
					if (Integer.parseInt(d1) == i) {
						yekan = a[i];
						break;
					}
				}
				for (int j = 0; j < c.length; j++) {
					if (Integer.parseInt(d2) == j) {
						dahgan = c[j];
						if (!yekan.equals(""))
							total = dahgan + " و" + yekan;
						else
							total = dahgan;
						break;
					}
				}
			} else {
				int k;
				for (k = 0; k < b.length; k++) {
					if (Integer.parseInt(d1) == k) {
						yekan = b[k];
						break;
					}
				}
				total = b[k];
			}
			if (length4.length() == 3 && length4.charAt(0) != '0') {
				for (int p = 0; p < d.length; p++) {
					if (Integer.parseInt(d3) == p) {
						sadgan = d[p];
						if (!total.equals(""))
							total1 = sadgan + " و" + total;
						else
							total1 = sadgan;
						break;
					}
				}
			} else {
				total1 = total;
			}
			String total3 = "";
			String re = "";
			if (total1.length() - 2 >= 0)
				re = "" + total1.charAt(total1.length() - 2);
			if (re == " ") {
				String newWord = total1.substring(0, (total1.length() - 2));
				total3 = newWord;
			} else {
				total3 = total1;
			}
			if (!finall.equals(" "))
				if (!finall.equals("") && !total3.equals(""))
					finall = total3 + " " + e[m] + " و" + finall;
				else if (finall.equals("") && !total3.equals(""))
					finall = total3 + " " + e[m];
				else if (!finall.equals("") && total3.equals(""))
					finall = finall;
				else
					finall = total3;
			else
				finall = total3;
			m++;
		}
		return finall;
	}*/