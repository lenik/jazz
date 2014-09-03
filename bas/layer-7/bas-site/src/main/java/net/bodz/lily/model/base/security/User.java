package net.bodz.lily.model.base.security;

import java.net.Inet4Address;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.bodz.lily.model.base.CoEntity;

public class User
        extends CoEntity {

    private static final long serialVersionUID = 1L;

    public static final int N_NAME = 30;
    public static final int N_FULL_NAME = 40;
    public static final int N_EMAIL = 30;
    public static final int N_PASSWORD = 40;

    Role primaryRole;
    final Set<Role> roles = new HashSet<Role>();

    String email;
    boolean emailValidated;

    private static final Random RANDOM = new Random();
    int salt = RANDOM.nextInt();
    String password;

    long lastLoginTime;
    Inet4Address lastLoginAddr; // =Inet4Address.getByAddress();

    public final String getId() {
        return getCodeName();
    }

    public final void setId(String id) {
        setCodeName(id);
    }

    public final String getFullName() {
        return getLabel();
    }

    public final void setFullName(String fullName) {
        setLabel(fullName);
    }

    public Role getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(Role primaryRole) {
        this.primaryRole = primaryRole;
    }

    public Set<Role> getRoles() {
        return roles;
    }

}
