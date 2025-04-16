package net.bodz.bas.net.serv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.pool.IPool;
import net.bodz.bas.t.pool.IntSetPool;

public class ServiceManager
        implements IServiceManager,
                   IJsonForm {

    IPool<Integer> idPool = IntSetPool.ofSize(10000);

    // id => descriptor
    Map<String, ServiceDescriptor> byId = new HashMap<>();

    // protocol => List<ServiceDescriptor>
    ListMap<String, ServiceDescriptor> byProtocol = new ListMap<>(SortOrder.SORTED);

    // descriptor => alloc, or parent => children
    ListMap<ServiceDescriptor, Allocation> rindex = new ListMap<>();

    static class Allocation {

        final String protocol;
        final String id;

        public Allocation(String protocol, String id) {
            this.protocol = protocol;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass())
                return false;
            Allocation that = (Allocation) o;
            return Objects.equals(protocol, that.protocol) && Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(protocol, id);
        }

        @Override
        public String toString() {
            return "Allocation{" + "protocol='" + protocol + '\'' + ", id='" + id + '\'' + '}';
        }

    }

    @NotNull
    @Override
    public Set<String> getProtocols() {
        return byProtocol.keySet();
    }

    @NotNull
    @Override
    public Map<String, ServiceDescriptor> getRegisteredServices(@NotNull String protocol) {
        Map<String, ServiceDescriptor> map = SortOrder.KEEP.newMap();
        for (ServiceDescriptor descriptor : byProtocol.list(protocol)) {
            for (Allocation alloc : rindex.list(descriptor)) {
                if (alloc.protocol.equals(protocol))
                    map.put(alloc.id, descriptor);
            }
        }
        return map;
    }

    @Override
    public String register(@NotNull String protocol, @NotNull ServiceDescriptor descriptor) {
        String id = idPool.allocate().toString();
        byId.put(id, descriptor);
        byProtocol.addToList(protocol, descriptor);
        rindex.addToList(descriptor, new Allocation(protocol, id));
        return id;
    }

    @Override
    public boolean remove(@NotNull String id) {
        ServiceDescriptor descriptor = byId.remove(id);
        if (descriptor == null)
            return false;
        for (Allocation alloc : rindex.list(descriptor)) {
            if (alloc.id.equals(id)) {
                byProtocol.removeFromList(alloc.protocol, descriptor);
                rindex.removeFromList(descriptor, alloc);
            }
        }
        return true;
    }

    @Override
    public void remove(@NotNull String protocol, @NotNull ServiceDescriptor descriptor) {
        byProtocol.removeFromList(protocol, descriptor);
        for (Allocation alloc : find(descriptor)) {
            if (alloc.protocol.equals(protocol)) {
                rindex.removeFromList(descriptor, alloc);
                byId.remove(alloc.id);
            }
        }
    }

    @Override
    public void removeAll(@NotNull ServiceDescriptor descriptor) {
        rindex.remove(descriptor);
        for (Allocation alloc : find(descriptor)) {
            byProtocol.removeFromList(alloc.protocol, descriptor);
            byId.remove(alloc.id);
        }
    }

    public ServiceDescriptor get(@NotNull String id) {
        return byId.get(id);
    }

    /**
     * find the id of the service descriptor.
     * @param serviceDescriptor To find
     * @return
     */
    List<Allocation> find(@NotNull ServiceDescriptor serviceDescriptor) {
        return rindex.get(serviceDescriptor);
    }

    List<String> findIds(@NotNull ServiceDescriptor serviceDescriptor, String protocol) {
        List<String> ids = new ArrayList<>();
        for (Allocation alloc : rindex.get(serviceDescriptor))
            if (alloc.protocol.equals(protocol))
                ids.add(alloc.id);
        return ids;
    }

    String findProtocol(@NotNull ServiceDescriptor serviceDescriptor, String id) {
        for (Allocation alloc : rindex.get(serviceDescriptor))
            if (alloc.id.equals(id))
                return alloc.protocol;
        return null;
    }

    //
    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String protocol : getProtocols()) {
            for (ServiceDescriptor descriptor : byProtocol.list(protocol)) {
                for (String id : findIds(descriptor, protocol)) {
                    out.key(id);
                    out.object();
                    {
                        out.key("protocol");
                        out.value(protocol);
                        descriptor.jsonOut(out);
                    }
                    out.endObject();
                }
            }
        }
    }

}
