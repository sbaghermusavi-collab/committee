package com.nicico.copper.base.constraint;

import lombok.ToString;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
public class ConstraintViolationInfo {
	private final EConstraintType type;
	private final String field;
	private final String message;

	public ConstraintViolationInfo(ConstraintViolation<?> violation) {
		this.type = EConstraintType.findByConstraint(violation.getConstraintDescriptor().getAnnotation().annotationType());

		final List<Path.Node> nodes = new ArrayList<>();
		violation.getPropertyPath().forEach(nodes::add);
		this.field = nodes.stream()
			.filter(node -> node.getKind() == ElementKind.PROPERTY)
			.map(Path.Node::getName)
			.findFirst()
			.orElse(null);
		this.message = violation.getMessage();

		//this.invalidValue = violation.getInvalidValue();
	}

	private ConstraintViolationInfo(EConstraintType type, String field, String message) {
		this.type = type;
		this.field = field;
		this.message = message;
	}

	// ------------------------------

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConstraintViolationInfo that = (ConstraintViolationInfo) o;
		return type == that.type &&
			Objects.equals(field, that.field) &&
			(message == null || that.message == null || Objects.equals(message, that.message));
	}

	// ------------------------------

	public static ConstraintViolationInfo of(EConstraintType type, String field) {
		return of(type, field, null);
	}

	public static ConstraintViolationInfo of(EConstraintType type, String field, String message) {
		return new ConstraintViolationInfo(type, field, message);
	}

}
