package net.bodz.bas.http.viz;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.util.IFramedMap;
import net.bodz.bas.rtx.IQueryable;

public interface IHttpViewContext
        extends IQueryable {

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    HttpSession getSession();

    void stop();

    boolean isStopped();

    Map<String, Object> getAttributeMap();

    <T> T getAttribute(String name);

    void setAttribute(String name, Object value);

    IFramedMap<String, Object> getVariableMap();

    <T> T getVariable(String name);

    void setVariable(String name, Object value);

}
