package net.bodz.bas.site.vhost;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.autowire.ProjectList;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.specmap.InetPort32;
import net.bodz.bas.t.specmap.NetSpecMap;

import jakarta.servlet.http.HttpServletRequest;

@ExcludedFromIndex
public class MutableVirtualHostResolver
        implements
            IVirtualHostResolver,
            IJsonForm {

    static final Logger logger = LoggerFactory.getLogger(MutableVirtualHostResolver.class);

    private int priority;

    IVirtualHostFactory virtualHostFactory;

    Map<String, IVirtualHost> map = new TreeMap<String, IVirtualHost>();
    NetSpecMap<String> bindingMap = new NetSpecMap<>();

    static final String VAR_ECHO_HEAD = "/echo/head";
    static final String VAR_PROJECT = "/project";

    public MutableVirtualHostResolver(IVirtualHostFactory virtualHostFactory) {
        if (virtualHostFactory == null)
            throw new NullPointerException("virtualHostFactory");
        this.virtualHostFactory = virtualHostFactory;

        bindingMap.nameMap.addPattern("*.lo", VAR_ECHO_HEAD);
        InetPort32 ap;
        try {
            ap = InetPort32.parse("0.0.0.0/0");
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        bindingMap.ipv4Map.addPrefix(ap, VAR_PROJECT);
    }

    public IVirtualHostFactory getVirtualHostFactory() {
        return virtualHostFactory;
    }

    public void setVirtualHostFactory(IVirtualHostFactory virtualHostFactory) {
        if (virtualHostFactory == null)
            throw new NullPointerException("virtualHostFactory");
        this.virtualHostFactory = virtualHostFactory;
    }

    public NetSpecMap<String> getBindingMap() {
        return bindingMap;
    }

    public void fallbackToProject() {
        bindingMap.nameMap.addDefault(VAR_PROJECT);
    }

    public void clearFallback() {
        bindingMap.nameMap.removeDefault();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public IVirtualHost getVirtualHost(String id) {
        if (id == null)
            throw new NullPointerException("id");
        return map.get(id);
    }

    protected String getRequestName(HttpServletRequest request) {
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String target = bindingMap.find(serverName, "" + serverPort);
        if (target == null) {
            logger.errorf("Can't resolve name from request: %s:%d", //
                    serverName, serverPort);
            return null;
        }
        if (target.startsWith("/")) {
            switch (target) {
            case VAR_ECHO_HEAD:
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
    public String getVirtualHostId(HttpServletRequest request) {
        return getRequestName(request);
    }

    @Override
    public synchronized IVirtualHost resolveVirtualHost(HttpServletRequest request) {
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
            throw new DuplicatedKeyException(name, old);

        for (String binding : bindings) {
            bindingMap.putTop(binding, name);
        }

        map.put(vhost.getName(), vhost);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("priority", priority);
        out.entry("map", map);
        out.key("bindingMap");
        out.object();
        bindingMap.jsonOut(out);
        out.endObject();
    }

    public void dumpJson() {
        try {
            String json = JsonFn.toJson(this);
            System.err.println(json);
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

}
