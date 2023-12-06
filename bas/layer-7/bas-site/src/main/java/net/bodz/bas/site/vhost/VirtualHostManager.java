package net.bodz.bas.site.vhost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;
import net.bodz.bas.t.order.PriorityComparator;

import jakarta.servlet.http.HttpServletRequest;

@IndexedTypeLoader(IVirtualHostResolver.class)
public class VirtualHostManager
        extends ArrayList<IVirtualHostResolver>
        implements
            IVirtualHostResolver {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(VirtualHostManager.class);

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
    public IVirtualHost getVirtualHost(String id) {
        for (IVirtualHostResolver resolver : this) {
            IVirtualHost vhost = resolver.getVirtualHost(id);
            if (vhost != null)
                return vhost;
        }
        return null;
    }

    @Override
    public String getVirtualHostId(HttpServletRequest request) {
        Map<String, IVirtualHostResolver> map = new LinkedHashMap<>();
        String lastId = null;
        for (IVirtualHostResolver resolver : this) {
            String id = resolver.getVirtualHostId(request);
            if (id != null) {
                IVirtualHostResolver prev = map.get(id);
                if (prev != null)
                    throw new DuplicatedKeyException(String.format(//
                            "Id %s previous registered with resolver %s", //
                            id, prev));
            }
            lastId = id;
        }
        return lastId;
    }

    @Override
    public IVirtualHost resolveVirtualHost(HttpServletRequest request) {
        for (IVirtualHostResolver resolver : this) {
            IVirtualHost vhost = resolver.resolveVirtualHost(request);
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
