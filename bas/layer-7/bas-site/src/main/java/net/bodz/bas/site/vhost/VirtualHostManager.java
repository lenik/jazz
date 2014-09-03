package net.bodz.bas.site.vhost;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.text.trie.TokenTrie;
import net.bodz.bas.text.trie.TokenTrie.Node;

public class VirtualHostManager
        implements IVirtualHostResolver {

    private IVirtualHost defaultVhost;
    private TokenTrie<HostBinding> trie = new TokenTrie<>();
    private Map<String, IVirtualHost> map = new TreeMap<String, IVirtualHost>();

    public VirtualHostManager() {
        this(null);
    }

    public VirtualHostManager(IVirtualHost defaultVhost) {
        this.defaultVhost = defaultVhost;
    }

    public IVirtualHost getDefaultVhost() {
        return defaultVhost;
    }

    public void setDefaultVhost(IVirtualHost defaultVhost) {
        this.defaultVhost = defaultVhost;
    }

    public IVirtualHost getVirtualHost(String name) {
        if (name == null)
            throw new NullPointerException("name");
        // if (name.equals(defaultVhost.getName())) return defaultVhost;
        return map.get(name);
    }

    @Override
    public synchronized IVirtualHost getVirtualHost(HttpServletRequest request) {
        IVirtualHost exact = null;
        IVirtualHost nearest = defaultVhost;
        Node<HostBinding> node = trie.getRoot();

        for (String label : new DomainNameTokenizer(request.getServerName(), true)) {
            node = node.getChild(label);
            if (node == null) {
                exact = null;
                break;
            }

            HostBinding binding = node.getData();
            if (binding == null) {
                exact = null;
                continue;
            }

            int port = request.getServerPort();
            exact = binding.portMap.get(port);
            if (exact == null)
                exact = binding.portMap.getDefault();

            IVirtualHost _fallback = binding.subDomainPortMap.get(port);
            if (_fallback == null)
                _fallback = binding.subDomainPortMap.getDefault();
            if (_fallback != null)
                nearest = _fallback;
        }
        return exact != null ? exact : nearest;
    }

    public synchronized void add(IVirtualHost vhost) {
        if (vhost == null)
            throw new NullPointerException("vhost");

        IVirtualHost old = map.get(vhost.getName());
        if (old != null)
            throw new DuplicatedKeyException(map, vhost.getName(), "vhost");

        for (HostSpecifier spec : vhost.getHostSpecifiers()) {
            Node<HostBinding> node = trie.getRoot();

            for (String label : new DomainNameTokenizer(spec.getHostName(), true))
                node = node.getOrAddChild(label);

            HostBinding binding = node.getData();
            if (binding == null)
                node.setData(binding = new HostBinding());

            PortMap portMap = spec.isIncludeSubDomains() ? binding.subDomainPortMap : binding.portMap;
            if (spec.getPort() == 0) {
                if (portMap.getDefault() != null)
                    throw new IllegalUsageException(String.format("Duplicated %s: %s and %s.", //
                            spec, portMap.getDefault().getName(), vhost.getName()));
                portMap.setDefault(vhost);
            } else {
                old = portMap.get(spec.getPort());
                if (old != null)
                    throw new IllegalUsageException(String.format("Duplicated %s: %s and %s.", //
                            spec, old.getName(), vhost.getName()));
                portMap.put(spec.getPort(), vhost);
            }
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

    private static VirtualHostManager instance = new VirtualHostManager();

    public static VirtualHostManager getInstance() {
        return instance;
    }

}

class HostBinding {

    public PortMap portMap = new PortMap();
    public PortMap subDomainPortMap = new PortMap();

}

class PortMap
        extends HashMap<Integer, IVirtualHost> {

    private static final long serialVersionUID = 1L;

    IVirtualHost default_;

    public IVirtualHost getDefault() {
        return default_;
    }

    public void setDefault(IVirtualHost default_) {
        this.default_ = default_;
    }

}
