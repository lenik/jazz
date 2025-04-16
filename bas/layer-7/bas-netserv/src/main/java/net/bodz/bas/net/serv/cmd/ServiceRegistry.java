package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.IServiceManager;
import net.bodz.bas.net.serv.ServiceDescriptor;

public class ServiceRegistry {

    @NotNull
    final IServiceManager serviceManager;

    final Map<String, String> byProtocol = new TreeMap<>();
    final Map<String, String> byId = new TreeMap<>();
    final Map<SocketChannel, ServiceDescriptor> byChannel = new IdentityHashMap<>();

    public ServiceRegistry(@NotNull IServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void register(String protocol, SocketChannel channel)
            throws IOException {
        String id = byProtocol.get(protocol);
        if (id != null)
            throw new IllegalArgumentException("Protocol " + protocol + " has already been registered, id=" + id);

        ServiceDescriptor prev = byChannel.get(channel);
        if (prev != null)
            throw new IllegalArgumentException("channel duplicated");

        ServiceDescriptor descriptor = new ServiceDescriptor(channel);
        try {
            id = serviceManager.registerService(protocol, descriptor);
        } catch (Exception e) {
            throw new IllegalArgumentException("can't register: " + e.getMessage());
        }

        byId.put(id, protocol);
        byProtocol.put(protocol, id);
        byChannel.put(channel, descriptor);
    }

    public void deregister(@NotNull String protocol, @NotNull SocketChannel channel)
            throws IOException {
        String id = byProtocol.get(protocol);
        if (id == null)
            throw new IllegalArgumentException("not registered. ");
        ServiceDescriptor descriptor = byChannel.get(channel);
        if (descriptor == null)
            throw new UnexpectedException("missing channel entry ");
        serviceManager.removeService(protocol, descriptor);
        byProtocol.remove(protocol);
        byChannel.remove(channel);
    }

    public void deregister(String id)
            throws IOException {
        ServiceDescriptor descriptor = serviceManager.getService(id);
        if (descriptor == null)
            throw new IllegalArgumentException("not registered: " + id);

        serviceManager.removeService(id);

        String protocol = byId.remove(id);
        if (protocol != null)
            byProtocol.remove(protocol);
        byChannel.remove(descriptor.getChannel());
    }

}
