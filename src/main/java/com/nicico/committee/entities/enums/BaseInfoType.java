package com.nicico.committee.entities.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum BaseInfoType {
    ENUM_TYPE("enum.type", null);

    @Getter
    private String code;

    @Getter
    private BaseInfoType parent;

    BaseInfoType(String code, BaseInfoType parent) {
        this.code = code;
        this.parent = parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public List<BaseInfoType> getChildren() {
        List<BaseInfoType> children = new ArrayList<>();
        for (BaseInfoType type : BaseInfoType.values()) {
            if (this.equals(type.getParent())) {
                children.add(type);
            }
        }
        return children;
    }

    /**
     * Gets all children of this type, including grandchildren, etc.
     * @return a list of all children of this type
     */
    public List<BaseInfoType> getAllChildrenWithParent() {
        List<BaseInfoType> children = new ArrayList<>();
        children.add(this);
        for (BaseInfoType type : BaseInfoType.values()) {
            if (this.equals(type.getParent())) {
                children.addAll(type.getAllChildrenWithParent()); // Recursive call to get grandchildren, etc.
            }
        }
        return children;
    }
}
