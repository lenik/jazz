package net.bodz.lily.security.auth;

public class AuthModuleState
        implements
            IAuthModuleState {

    boolean enabled;
    Object authId;

//    public AuthModuleState() {
//    }

    private AuthModuleState(boolean enabled, Object authId) {
        this.enabled = enabled;
        this.authId = authId;
    }

    public static AuthModuleState enabled(Object id) {
        return new AuthModuleState(true, id);
    }

    public static AuthModuleState disabled() {
        return DISABLED;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Object getAuthId() {
        return authId;
    }

    public void setAuthId(Object authId) {
        this.authId = authId;
    }

    public static final AuthModuleState DISABLED = new AuthModuleState(false, null);
    public static final AuthModuleState PENDING = new AuthModuleState(true, null);

}
