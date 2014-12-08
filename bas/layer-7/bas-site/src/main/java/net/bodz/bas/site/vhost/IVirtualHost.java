package net.bodz.bas.site.vhost;

import java.util.List;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.typer.std.IMutableAttributes;

public interface IVirtualHost
        extends IElement, IQueryable, IMutableAttributes {

    List<HostSpecifier> getHostSpecifiers();

    Iterable<String> getParameterNames();

    String getParameter(String name);

    void setParameter(String name, String value);

    void removeParameter(String name);

}
