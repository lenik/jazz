package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.SessionCookieConfig;

import net.bodz.bas.c.util.MapGlobal;

public class JxSessionCookieConfig
        implements
            SessionCookieConfig {

    javax.servlet.SessionCookieConfig jx;

    public JxSessionCookieConfig(javax.servlet.SessionCookieConfig jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void setName(String name) {
        jx.setName(name);
    }

    @Override
    public String getName() {
        return jx.getName();
    }

    @Override
    public void setDomain(String domain) {
        jx.setDomain(domain);
    }

    @Override
    public String getDomain() {
        return jx.getDomain();
    }

    @Override
    public void setPath(String path) {
        jx.setPath(path);
    }

    @Override
    public String getPath() {
        return jx.getPath();
    }

    @Override
    public void setComment(String comment) {
        jx.setComment(comment);
    }

    @Override
    public String getComment() {
        return jx.getComment();
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        jx.setHttpOnly(httpOnly);
    }

    @Override
    public boolean isHttpOnly() {
        return jx.isHttpOnly();
    }

    @Override
    public void setSecure(boolean secure) {
        jx.setSecure(secure);
    }

    @Override
    public boolean isSecure() {
        return jx.isSecure();
    }

    @Override
    public void setMaxAge(int maxAge) {
        jx.setMaxAge(maxAge);
    }

    @Override
    public int getMaxAge() {
        return jx.getMaxAge();
    }

    public static final MapGlobal<javax.servlet.SessionCookieConfig, SessionCookieConfig> jx2a //
            = MapGlobal.fn(SessionCookieConfig.class, (s) -> new JxSessionCookieConfig(s));

}
