package net.bodz.bas.repr.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.meta.decl.Shortcut;

public interface IHtmlOutputContext
        extends IHtmlReprContext {

    IHtmlOutputContext getParent();

    @Shortcut("parent/.../parent")
    IHtmlOutputContext getRoot();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    IByteOut getByteOut();

    IHtmlOut getOut();

    void flush();

    boolean enter()
            throws IOException;

}
