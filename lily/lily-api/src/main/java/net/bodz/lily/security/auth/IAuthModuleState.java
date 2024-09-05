package net.bodz.lily.security.auth;

public interface IAuthModuleState {

    boolean isEnabled();

    default boolean isDisabled() {
        return ! isEnabled();
    }

    Object getAuthId();

}
