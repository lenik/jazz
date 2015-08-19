package net.bodz.bas.http.viz;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.meta.decl.Shortcut;
import net.bodz.bas.rtx.IQueryable;

public interface IHttpViewContext
        extends IQueryable {

    IHttpViewContext getParent();

    @Shortcut("parent*")
    IHttpViewContext getRoot();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    HttpSession getSession();

    Map<String, Object> getAttributeMap();

    <T> T getAttribute(String name);

    void setAttribute(String name, Object value);

}