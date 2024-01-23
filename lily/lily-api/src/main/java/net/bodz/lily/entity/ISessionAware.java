package net.bodz.lily.entity;

import jakarta.servlet.http.HttpSession;

public interface ISessionAware {

    void setSession(HttpSession session);

}
