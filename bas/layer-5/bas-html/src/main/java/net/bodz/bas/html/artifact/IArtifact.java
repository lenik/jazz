package net.bodz.bas.html.artifact;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IArtifact
        extends IQueryable, IArtifactDependent {

    String getName();

    IVersion getVersion();

    ContentType getContentType();

    /**
     * @see javax.servlet.http.HttpServletRequest#getRequestURI()
     */
    String getHref(String requestURI);

}
