package net.bodz.bas.c.javax.servlet;

import javax.servlet.SessionCookieConfig;

import net.bodz.bas.c.util.MapGlobal;

public class JaSessionCookieConfig
        implements
            SessionCookieConfig {

    jakarta.servlet.SessionCookieConfig ja;

    public JaSessionCookieConfig(jakarta.servlet.SessionCookieConfig ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void setName(String name) {
        ja.setName(name);
    }

    @Override
    public String getName() {
        return ja.getName();
    }

    @Override
    public void setDomain(String domain) {
        ja.setDomain(domain);
    }

    @Override
    public String getDomain() {
        return ja.getDomain();
    }

    @Override
    public void setPath(String path) {
        ja.setPath(path);
    }

    @Override
    public String getPath() {
        return ja.getPath();
    }

    @Override
    public void setComment(String comment) {
        ja.setComment(comment);
    }

    @Override
    public String getComment() {
        return ja.getComment();
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        ja.setHttpOnly(httpOnly);
    }

    @Override
    public boolean isHttpOnly() {
        return ja.isHttpOnly();
    }

    @Override
    public void setSecure(boolean secure) {
        ja.setSecure(secure);
    }

    @Override
    public boolean isSecure() {
        return ja.isSecure();
    }

    @Override
    public void setMaxAge(int maxAge) {
        ja.setMaxAge(maxAge);
    }

    @Override
    public int getMaxAge() {
        return ja.getMaxAge();
    }

    public static final MapGlobal<jakarta.servlet.SessionCookieConfig, SessionCookieConfig> jx2a //
            = MapGlobal.fn(SessionCookieConfig.class, (s) -> new JaSessionCookieConfig(s));

}
