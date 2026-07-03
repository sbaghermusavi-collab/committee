package com.nicico.copper.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface IErrorCode {
	String getName();

	Integer getHttpStatusCode();

	// ------------------------------

	IErrorCode Unknown = GeneralError.Unknown;
	IErrorCode Forbidden = GeneralError.Forbidden;
	IErrorCode NotFound = GeneralError.NotFound;
	IErrorCode UpdatingInvalidOldVersion = GeneralError.UpdatingInvalidOldVersion;
	IErrorCode TotalConcurrencyViolation = GeneralError.TotalConcurrencyViolation;
	IErrorCode PerUserConcurrencyViolation = GeneralError.PerUserConcurrencyViolation;
	IErrorCode SwapFileLimitViolation = GeneralError.SwapFileLimitViolation;

	// ------------------------------

	@Getter
	@RequiredArgsConstructor
	enum GeneralError implements IErrorCode {
		Unknown(500),
		Forbidden(403),
		NotFound(404),
		UpdatingInvalidOldVersion(400),
		TotalConcurrencyViolation(403),
		PerUserConcurrencyViolation(403),
		SwapFileLimitViolation(403);

		private final Integer httpStatusCode;

		@Override
		public String getName() {
			return name();
		}
	}
}
