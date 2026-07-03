package com.nicico.committee.validation;

import com.nicico.committee.entities.BaseInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BaseInfoValidator implements ConstraintValidator<ValidBaseInfo, BaseInfo> {

    private String requiredParentCode;

    @Override
    public void initialize(ValidBaseInfo constraintAnnotation) {
        this.requiredParentCode = constraintAnnotation.parentCode();
    }

    @Override
    public boolean isValid(BaseInfo baseInfo, ConstraintValidatorContext context) {
        if (baseInfo == null) {
            return true; // Use @NotNull for null checks
        }

        if (baseInfo.getParent() == null) {
            return false;
        }

        return requiredParentCode.equals(baseInfo.getParent().getCode());
    }
}
