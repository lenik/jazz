package net.bodz.bas.net.serv;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;

public interface IServiceManager {

    @NotNull
    Set<String> getProtocols();

    /**
     * @param protocol
     * @return alloc-id => descriptor
     */
    @NotNull
    Map<String, ServiceDescriptor> findByProtocol(@NotNull String protocol);

    /**
     * @return identifier
     */
    String registerService(@NotNull String protocol, @NotNull ServiceDescriptor descriptor);

    ServiceDescriptor getService(@NotNull String id);

    /**
     * @return true if successfully removed.
     */
    boolean removeService(@NotNull String id);

    void removeService(@NotNull String protocol, @NotNull ServiceDescriptor descriptor);

    void removeServices(@NotNull ServiceDescriptor descriptor);

}
