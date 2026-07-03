package com.nicico.copper.base;

import com.nicico.copper.base.constraint.ConstraintViolationInfo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExceptionUtil {

	public static Optional<String> findDbConstraintName(Throwable ex) {
		if (ex instanceof DataIntegrityViolationException) {
			return findDbConstraintName(ex.getCause());
		} else if (ex instanceof ConstraintViolationException) {
			final ConstraintViolationException cve = (ConstraintViolationException) ex;
			final String constraintName = cve.getConstraintName();
			if (constraintName != null) {
				if (constraintName.contains(".")) {
					return Optional.of(constraintName.split("\\.")[1]);
				}
				return Optional.of(constraintName);
			}
			return Optional.empty();
		}

		return Optional.empty();
	}

	public static List<ConstraintViolationInfo> getConstraintViolationInfo(jakarta.validation.ConstraintViolationException ex) {
		return ex.getConstraintViolations()
			.stream()
			.map(ConstraintViolationInfo::new)
			.collect(Collectors.toList());
	}
}
