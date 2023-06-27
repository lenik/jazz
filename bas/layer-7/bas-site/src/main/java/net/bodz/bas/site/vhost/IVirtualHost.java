package net.bodz.bas.site.vhost;

import java.util.List;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.ISiteRoot;
import net.bodz.bas.typer.std.IMutableAttributes;

public interface IVirtualHost
        extends
            IElement,
            IQueryable,
            IMutableAttributes,
            IParameterMap<Object> {

    List<String> getBindings();

    ISiteRoot getRoot();

}
