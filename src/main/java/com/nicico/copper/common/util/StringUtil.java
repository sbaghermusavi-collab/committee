package com.nicico.copper.common.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
	private static final Map<String, String> REPLACEMENT = new HashMap<>();

	static {
		REPLACEMENT.put("ي", "ی");
		REPLACEMENT.put("ك", "ک");
	}

	public static String replaceSpecialArabic(String str) {
		for (Map.Entry<String, String> entry : REPLACEMENT.entrySet()) {
			str = str.replace(entry.getKey(), entry.getValue());
		}

		return str;
	}

}
