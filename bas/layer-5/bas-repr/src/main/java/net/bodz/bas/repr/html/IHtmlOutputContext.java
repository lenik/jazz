package net.bodz.bas.repr.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.decl.Shortcut;

public interface IHtmlOutputContext {

    IHtmlOutputContext getParent();

    @Shortcut("parent/.../parent")
    IHtmlOutputContext getRoot();

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    IByteOut getByteOut();

    IPrintOut getPrintOut();

}
