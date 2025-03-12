package net.bodz.bas.servlet.ui.cmd;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.bodz.bas.ui.model.action.IActionContext;

public interface IServletActionContext
        extends IActionContext {

    ServletContext getServletContext();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    HttpSession getSession();

}
