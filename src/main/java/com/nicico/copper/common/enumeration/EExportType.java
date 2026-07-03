package com.nicico.copper.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EExportType {
	PDF(1, "application/pdf"),
	EXCEL(2, "application/vnd.ms-excel"),
	HTML(3, "text/html"),
	WORD(4, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

	// ------------------------------

	private final Integer id;
	private final String contentType;

	public static EExportType find(String type) {
		return valueOf(type.toUpperCase());
	}
}
