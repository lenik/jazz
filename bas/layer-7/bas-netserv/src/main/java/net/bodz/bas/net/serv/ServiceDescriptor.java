package net.bodz.bas.net.serv;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Objects;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public class ServiceDescriptor
        implements IJsonForm {

    final SocketChannel channel;

    public ServiceDescriptor(@NotNull SocketChannel channel) {
        this.channel = channel;
    }

    @NotNull
    public SocketChannel getChannel() {
        return channel;
    }

    public String getAddress() {
        return getAddress(null);
    }

    public String getAddress(String fallback) {
        try {
            SocketAddress remoteAddress = channel.getRemoteAddress();
            return remoteAddress.toString();
        } catch (IOException e) {
            return fallback;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return Objects.equals(channel, that.channel);
    }

    //
    private static final String K_ADDRESS = "address";

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_ADDRESS, getAddress());
    }

}
