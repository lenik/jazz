package net.bodz.bas.site.vhost;

import java.util.List;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.rtx.IMutableAttributes;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.ISiteRoot;

public interface IVirtualHost
        extends
            IElement,
            IQueryable,
            IMutableAttributes,
            IParameterMap<Object> {

    List<String> getBindings();

    ISiteRoot getRoot();

}
