package net.bodz.bas.servlet.ui.cmd;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ui.model.action.IActionContext;

public interface IServletActionContext
        extends IActionContext {

    ServletContext getServletContext();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    HttpSession getSession();

}
