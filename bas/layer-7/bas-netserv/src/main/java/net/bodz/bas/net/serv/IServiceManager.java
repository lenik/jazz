package net.bodz.bas.net.serv;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;

public interface IServiceManager
        extends IServiceChannelRegistry {

    @NotNull
    Set<String> getProtocols();

    /**
     * @return id => descriptor
     */
    @NotNull
    Map<String, ServiceDescriptor> findByProtocol(@NotNull String protocol);

    /**
     * @return identifier
     */
    String registerService(@NotNull ServiceDescriptor descriptor);

    ServiceDescriptor getService(@NotNull String id);

    /**
     * @return true if successfully removed.
     */
    boolean removeService(@NotNull String id);

    void removeService(@NotNull ServiceDescriptor descriptor);

}
