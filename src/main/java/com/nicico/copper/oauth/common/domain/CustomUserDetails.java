//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nicico.copper.oauth.common.domain;

import com.nicico.copper.oauth.common.enumeration.EOAAccessMode;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
    private static final long serialVersionUID = 1L;
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String nationalCode;
    private final EOAAccessMode accessMode;
    private final List<CustomAppDetails> allowedApps;
    private final List<CustomAppDetails> adminApps;

    public CustomUserDetails(String username, String password, Long userId, String firstName, String lastName, String nationalCode, EOAAccessMode accessMode, List<CustomAppDetails> allowedApps, List<CustomAppDetails> adminApps) {
        super(username, password, Collections.emptyList());
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.accessMode = accessMode;
        this.allowedApps = allowedApps;
        this.adminApps = adminApps;
    }

    public CustomUserDetails(String username, String password, Long userId, String firstName, String lastName, String nationalCode, EOAAccessMode accessMode, List<CustomAppDetails> allowedApps, List<CustomAppDetails> adminApps, Set<GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.accessMode = accessMode;
        this.allowedApps = allowedApps;
        this.adminApps = adminApps;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getNationalCode() {
        return this.nationalCode;
    }

    public EOAAccessMode getAccessMode() {
        return this.accessMode;
    }

    public List<CustomAppDetails> getAllowedApps() {
        return this.allowedApps;
    }

    public List<CustomAppDetails> getAdminApps() {
        return this.adminApps;
    }

    public String toString() {
        Long var10000 = this.getUserId();
        return "CustomUserDetails(userId=" + var10000 + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", nationalCode=" + this.getNationalCode() + ", accessMode=" + this.getAccessMode() + ", allowedApps=" + this.getAllowedApps() + ", adminApps=" + this.getAdminApps() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CustomUserDetails)) {
            return false;
        } else {
            CustomUserDetails other = (CustomUserDetails)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$userId = this.getUserId();
                Object other$userId = other.getUserId();
                if (this$userId == null) {
                    if (other$userId != null) {
                        return false;
                    }
                } else if (!this$userId.equals(other$userId)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomUserDetails;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        return result;
    }

    public static class CustomAppDetails implements Serializable {
        private static final long serialVersionUID = 1L;
        private String id;
        private String title;
        private EOAAccessMode accessMode;
        private Long appGroupId;

        public CustomAppDetails(String id) {
            this.id = id;
        }

        public CustomAppDetails(String id, String title, EOAAccessMode accessMode, Long appGroupId) {
            this.id = id;
            this.title = title;
            this.accessMode = accessMode;
            this.appGroupId = appGroupId;
        }

        public String getId() {
            return this.id;
        }

        public String getTitle() {
            return this.title;
        }

        public EOAAccessMode getAccessMode() {
            return this.accessMode;
        }

        public Long getAppGroupId() {
            return this.appGroupId;
        }

        public String toString() {
            return "CustomUserDetails.CustomAppDetails(id=" + this.getId() + ")";
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof CustomAppDetails)) {
                return false;
            } else {
                CustomAppDetails other = (CustomAppDetails)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id != null) {
                            return false;
                        }
                    } else if (!this$id.equals(other$id)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof CustomAppDetails;
        }

        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $id = this.getId();
            result = result * 59 + ($id == null ? 43 : $id.hashCode());
            return result;
        }
    }
}
