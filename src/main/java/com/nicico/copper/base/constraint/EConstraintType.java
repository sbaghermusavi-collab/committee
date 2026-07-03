package com.nicico.copper.base.constraint;

import java.lang.annotation.Annotation;

public enum EConstraintType {
	NotNull(jakarta.validation.constraints.NotNull.class),
	NotEmpty(jakarta.validation.constraints.NotEmpty.class),
	Pattern(jakarta.validation.constraints.Pattern.class),
	Size(jakarta.validation.constraints.Size.class);

	private final Class<? extends Annotation> annotationClass;

	EConstraintType(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public static EConstraintType findByConstraint(Class<? extends Annotation> cls) {
		for (EConstraintType value : values()) {
			if (value.annotationClass.equals(cls)) {
				return value;
			}
		}

		throw new RuntimeException("Invalid Constraint Annotation: " + cls.getName());
	}
}
