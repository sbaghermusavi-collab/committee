package com.nicico.committee.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = BaseInfoValidator.class)
@Documented
public @interface ValidBaseInfo {

    String message() default "Invalid BaseInfo: parent code does not match the required condition.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * The expected code of the parent BaseInfo record (e.g. "ENUM_COMMITTY_TYPE")
     */
    String parentCode();
}
