package net.bodz.bas.net.serv;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;

public interface IServiceManager {

    @NotNull
    Set<String> getProtocols();

    /**
     *
     * @param protocol
     * @return alloc-id => descriptor
     */
    @NotNull
    Map<String, ServiceDescriptor> getRegisteredServices(@NotNull String protocol);

    /**
     * @return identifier
     */
    String register(@NotNull String protocol, @NotNull ServiceDescriptor descriptor);

    /**
     * @return true if successfully removed.
     */
    boolean remove(@NotNull String id);

    void remove(@NotNull String protocol, @NotNull ServiceDescriptor descriptor);

    void removeAll(@NotNull ServiceDescriptor descriptor);

}
