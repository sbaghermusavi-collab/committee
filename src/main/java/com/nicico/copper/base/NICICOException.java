package com.nicico.copper.base;

import lombok.Getter;

@Getter
public class NICICOException extends RuntimeException {
	private final IErrorCode errorCode;
	private final String field;

	// ------------------------------

	public NICICOException(Exception exception) {
		super(exception);

		errorCode = null;
		field = null;
	}

	public NICICOException(IErrorCode errorCode) {
		this(errorCode, null, null);
	}

	public NICICOException(IErrorCode errorCode, String field) {
		this(errorCode, field, null);
	}

	// Main Constructor
	public NICICOException(IErrorCode errorCode, String field, String message) {
		super(errorCode.getName() +
			(field != null ? String.format(" - [%s]", field) : "") +
			(message != null ? " - " + message : ""));

		this.errorCode = errorCode;
		this.field = field;
	}

	// ------------------------------

	public Integer getHttpStatusCode() {
		return errorCode.getHttpStatusCode();
	}
}
