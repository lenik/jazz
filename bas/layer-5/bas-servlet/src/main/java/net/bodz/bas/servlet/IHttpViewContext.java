package net.bodz.bas.servlet;

import java.util.Map;

import net.bodz.bas.ctx.util.IFramedMap;
import net.bodz.bas.rtx.IQueryable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface IHttpViewContext
        extends IQueryable {

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    HttpSession getSession();

    <T> T stop();

    boolean isStopped();

    Map<String, Object> getAttributeMap();

    <T> T getAttribute(String name);

    void setAttribute(String name, Object value);

    IFramedMap<String, Object> getVariableMap();

    <T> T getVariable(String name);

    void setVariable(String name, Object value);

    Object removeVariable(String name);

    IHttpHeadData getHeadData();

}
