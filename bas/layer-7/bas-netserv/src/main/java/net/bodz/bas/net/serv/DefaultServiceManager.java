package net.bodz.bas.net.serv;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.pool.IPool;
import net.bodz.bas.t.pool.IntSetPool;
import net.bodz.bas.t.record.BeanRecordType;
import net.bodz.bas.t.record.IRecordType;
import net.bodz.bas.t.record.RecordMap;

public class DefaultServiceManager
        implements IServiceManager,
                   IServiceChannelRegistry,
                   IJsonForm {

    IPool<Integer> idPool = IntSetPool.ofSize(10000);

    RecordMap<String, ServiceDescriptor> map = new RecordMap<>(TYPE);

    static final IRecordType<ServiceDescriptor> TYPE = BeanRecordType.ofList(//
            ServiceDescriptor.PROTOCOL, //
            ServiceDescriptor.CHANNEL);

    public DefaultServiceManager() {
    }

    @NotNull
    @Override
    public Set<String> getProtocols() {
        return map.index(ServiceDescriptor.PROTOCOL).keySet();
    }

    @NotNull
    @Override
    public Map<String, ServiceDescriptor> findByProtocol(@NotNull String protocol) {
        Map<String, ServiceDescriptor> ans = SortOrder.KEEP.newMap();
        for (String id : map.find(ServiceDescriptor.PROTOCOL, protocol::equals)) {
            ans.put(id, map.get(id));
        }
        return ans;
    }

    @Override
    public String registerService(@NotNull ServiceDescriptor descriptor) {
        Set<String> ids = map.find(descriptor);
        if (!ids.isEmpty())
            throw new IllegalArgumentException("already registered: " + ids);
        String id = idPool.allocate().toString();
        map.add(id, descriptor);
        return id;
    }

    @Override
    public ServiceDescriptor getService(@NotNull String id) {
        return map.get(id);
    }

    @Override
    public boolean removeService(@NotNull String id) {
        return map.remove(id);
    }

    @Override
    public void removeService(@NotNull ServiceDescriptor descriptor) {
        for (String id : map.find(descriptor))
            removeService(id);
    }

//

    @Override
    public String registerChannel(@NotNull SocketChannel channel, @NotNull String protocol) {
        ServiceDescriptor descriptor = new ServiceDescriptor(channel, protocol);
        return registerService(descriptor);
    }

    @Override
    public void removeChannel(@NotNull SocketChannel channel, String protocol) {
        for (String id : map.find(ServiceDescriptor.CHANNEL, channel::equals)) {
            ServiceDescriptor descriptor = map.get(id);
            if (protocol != null)
                if (!descriptor.getProtocol().equals(protocol))
                    continue;
            removeService(id);
        }
    }

    @Override
    public void removeChannel(@NotNull String id) {
        removeService(id);
    }

    //

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        for (String id : o.keySet()) {
            JsonObject joDescriptor = o.getJsonObject(id);
            ServiceDescriptor descriptor = new ServiceDescriptor();
            descriptor.jsonIn(joDescriptor);
            if (descriptor.getId() == null)
                descriptor.setId(id);
            registerService(descriptor);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String protocol : getProtocols()) {
            for (String id : map.find(ServiceDescriptor.PROTOCOL, protocol::equals)) {
                ServiceDescriptor descriptor = map.get(id);
                out.key(id);
                out.object();
                descriptor.jsonOut(out, opts);
                out.endObject();
            }
        }
    }

}