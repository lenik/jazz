package net.bodz.lily.concrete.util;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.lily.util.sql.LocalFileDebugUtils;

public class HtmlTextObject
        extends TextObject {

    LocalFileDebugUtils d = new LocalFileDebugUtils();

    public HtmlTextObject(String text) {
        super(text);
    }

    public String getHtmlForLocalDebug() {
        HttpServletRequest request = CurrentHttpService.getRequest();
        // Notice: getHeader IS case-sensitive.
        if (request != null && "file://".equals(request.getHeader("Origin"))) {
            String host = request.getHeader("Host");
            String html = text;
            String mod = d.convertAbsolutePathToAbsoluteUrl(html, "http://" + host);
            return mod;
        }
        return text;
    }

    public void setHtmlForLocalDebug(String h) {
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
