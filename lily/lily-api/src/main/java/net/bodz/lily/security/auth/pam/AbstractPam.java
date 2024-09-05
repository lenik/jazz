package net.bodz.lily.security.auth.pam;

import net.bodz.lily.security.auth.IAuthModule;

public abstract class AbstractPam
        implements
            IAuthModule {

    @Override
    public boolean isAuto() {
        return false;
    }

}
