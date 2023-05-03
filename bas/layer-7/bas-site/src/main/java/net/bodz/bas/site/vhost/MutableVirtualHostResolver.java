package net.bodz.bas.site.vhost;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.FromCurrentProjectName;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.specmap.NetBindingMap;
import net.bodz.bas.text.trie.TokenTrie.Node;

@ExcludedFromIndex
public class MutableVirtualHostResolver
        implements
            IVirtualHostResolver {

    private int priority;

    Map<String, IVirtualHost> map = new TreeMap<String, IVirtualHost>();
    NetBindingMap<String> bindingMap = new NetBindingMap<>();

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

    @Override
    public synchronized IVirtualHost resolve(HttpServletRequest request) {
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String target;
        try {
            target = bindingMap.find(serverName, serverPort);
        } catch (ParseException e) {
            throw new UnexpectedException(String.format(//
                    "cannot parse server name: %s (port %d)", //
                    serverName, serverPort));
        }

        if (target.startsWith("/")) {
            switch (target) {
            case "/simple":
                target = ServerNaming.getServerSimpleName(request);
                break;
            case "/project":
                String lastProjectName = FromCurrentProjectName.getLastProjectName();
                target = lastProjectName;
                break;
            }
        }

        IVirtualHost predefined = map.get(target);
        return predefined;
    }

    public synchronized void add(IVirtualHost vhost, String... bindings) {
        if (vhost == null)
            throw new NullPointerException("vhost");

        IVirtualHost old = map.get(vhost.getName());
        if (old != null)
            throw new DuplicatedKeyException(map, vhost.getName(), "vhost");

        for (String binding : bindings) {
//             bindingMap.pu
        }

        map.put(vhost.getName(), vhost);
    }

    public synchronized void remove(IVirtualHost vhost) {
        if (vhost == null)
            throw new NullPointerException("vhost");

        L: for (HostSpecifier spec : vhost.getHostSpecifiers()) {
            Node<HostBinding> node = trie.getRoot();

            for (String label : new DomainNameTokenizer(spec.getHostName(), true)) {
                node = node.getChild(label);
                if (node == null)
                    continue L;
            }

            HostBinding binding = node.getData();
            if (binding != null) {
                PortMap portMap = spec.isIncludeSubDomains() ? binding.subDomainPortMap : binding.portMap;
                if (spec.getPort() == 0) {
                    if (portMap.getDefault() == vhost)
                        portMap.setDefault(null);
                } else {
                    if (portMap.get(spec.getPort()) == vhost)
                        portMap.remove(spec.getPort());
                }
            }
        }

        map.remove(vhost.getName());
    }

}
