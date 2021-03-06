package net.bodz.bas.site.vhost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ServiceLoader;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.t.order.PriorityComparator;

@IndexedTypeLoader(IVirtualHostResolver.class)
public class VirtualHostManager
        extends ArrayList<IVirtualHostResolver>
        implements IVirtualHostResolver {

    private static final long serialVersionUID = 1L;

    public VirtualHostManager() {
        reload();
    }

    public void reload() {
        clear();
        for (IVirtualHostResolver resolver : ServiceLoader.load(IVirtualHostResolver.class))
            add(resolver);
        sort();
    }

    public void sort() {
        Collections.sort(this, PriorityComparator.INSTANCE);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public IVirtualHost get(String id) {
        for (IVirtualHostResolver resolver : this) {
            IVirtualHost vhost = resolver.get(id);
            if (vhost != null)
                return vhost;
        }
        return null;
    }

    @Override
    public IVirtualHost resolve(HttpServletRequest request) {
        for (IVirtualHostResolver resolver : this) {
            IVirtualHost vhost = resolver.resolve(request);
            if (vhost != null)
                return vhost;
        }
        return null;
    }

    private static VirtualHostManager instance = new VirtualHostManager();

    public static VirtualHostManager getInstance() {
        return instance;
    }

}
