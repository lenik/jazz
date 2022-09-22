package net.bodz.lily.entity;

import javax.servlet.http.HttpSession;

public interface ISessionAware {

    void setSession(HttpSession session);

}
