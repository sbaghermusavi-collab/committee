//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nicico.copper.oauth.common.enumeration;

public enum EOAAccessMode {
    Normal(1),
    Admin(2);

    private final Integer id;

    public Integer getId() {
        return this.id;
    }

    private EOAAccessMode(final Integer id) {
        this.id = id;
    }
}
