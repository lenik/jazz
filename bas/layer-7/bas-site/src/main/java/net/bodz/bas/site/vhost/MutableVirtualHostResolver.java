package net.bodz.bas.site.vhost;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.autowire.ProjectList;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.specmap.InetPort32;
import net.bodz.bas.t.specmap.NetSpecMap;

@ExcludedFromIndex
public class MutableVirtualHostResolver
        implements
            IVirtualHostResolver {

    private int priority;

    Map<String, IVirtualHost> map = new TreeMap<String, IVirtualHost>();
    NetSpecMap<String> bindingMap = new NetSpecMap<>();

    static final String VAR_SIMPLE = "/simple";
    static final String VAR_PROJECT = "/project";

    public MutableVirtualHostResolver() {
        bindingMap.nameMap.addPattern("*.lo", VAR_SIMPLE);
        InetPort32 ap;
        try {
            ap = InetPort32.parse("0.0.0.0/0");
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        bindingMap.ipv4Map.addPrefix(ap, VAR_PROJECT);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public IVirtualHost get(String id) {
        if (id == null)
            throw new NullPointerException("id");
        return map.get(id);
    }

    protected String getRequestName(HttpServletRequest request) {
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String target = bindingMap.find(serverName, "" + serverPort);
        if (target.startsWith("/")) {
            switch (target) {
            case VAR_SIMPLE:
                target = ServerNaming.getServerSimpleName(request);
                break;
            case VAR_PROJECT:
                String topLevelName = ProjectList.INSTANCE.topLevelName();
                target = topLevelName;
                break;
            }
        }
        return target;
    }

    @Override
    public synchronized IVirtualHost resolve(HttpServletRequest request) {
        String name = getRequestName(request);
        if (name == null)
            return null;
        IVirtualHost predefined = map.get(name);
        return predefined;
    }

    public synchronized void add(IVirtualHost vhost, String... bindings) {
        if (vhost == null)
            throw new NullPointerException("vhost");

        String name = vhost.getName();
        if (name == null)
            throw new NullPointerException("name");

        IVirtualHost old = map.get(name);
        if (old != null)
            throw new DuplicatedKeyException(map, vhost.getName(), "vhost");

        for (String binding : bindings) {
            bindingMap.putTop(binding, name);
        }

        map.put(vhost.getName(), vhost);
    }

}
