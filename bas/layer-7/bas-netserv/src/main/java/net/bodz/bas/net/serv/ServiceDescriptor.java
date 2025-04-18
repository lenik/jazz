package net.bodz.bas.net.serv;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.record.BasicColumnType;
import net.bodz.bas.t.record.IColumnType;
import net.bodz.bas.t.tuple.Split;

public class ServiceDescriptor
        implements IJsonForm {

    SocketChannel channel;
    String protocol;
    boolean reuseable = false;

    String id;
    String description;

    LocalDateTime creation = LocalDateTime.now();
    LocalDateTime lastUpdated = creation;

    public ServiceDescriptor() {
    }

    public ServiceDescriptor(@NotNull SocketChannel channel, @NotNull String protocol) {
        this.channel = channel;
        this.protocol = protocol;
    }

    @NotNull
    public String getProtocol() {
        return protocol;
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

    public boolean isReuseable() {
        return reuseable;
    }

    public void setReuseable(boolean reuseable) {
        this.reuseable = reuseable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public LocalDateTime getCreation() {
        return creation;
    }

    @NotNull
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(@NotNull LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return protocol + " at " + getAddress();
    }

    //    private static final String K_CHANNEL = "channel";
    private static final String K_ADDRESS = "address";
    private static final String K_PROTOCOL = "protocol";
    private static final String K_REUSEABLE = "reuseable";
    private static final String K_ID = "id";
    private static final String K_DESCRIPTION = "description";
    private static final String K_CREATION = "creation";
    private static final String K_LAST_UPDATED = "lastUpdated";

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
//        channel = o.getSocketChannel(K_CHANNEL);
        String address = o.getString(K_ADDRESS);
        if (address != null) {
            Split hostPort = Split.hostPort(address);
            String hostName = hostPort.a;
            String portStr = hostPort.b;
            int port = Integer.parseInt(portStr);
            InetSocketAddress sockaddr = new InetSocketAddress(hostName, port);
            try {
                channel = SocketChannel.open(sockaddr);
            } catch (IOException e) {
                throw new ParseException("Error open " + address + ": " + e.getMessage(), e);
            }
        }
        protocol = o.getString(K_PROTOCOL);
        reuseable = o.getBoolean(K_REUSEABLE, reuseable);
        id = o.getString(K_ID);
        description = o.getString(K_DESCRIPTION);
        creation = o.getLocalDateTime(K_CREATION);
        lastUpdated = o.getLocalDateTime(K_LAST_UPDATED);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
//        out.entryNotNull(K_CHANNEL, channel);
        out.entryNotNull(K_ADDRESS, getAddress());
        out.entryNotNull(K_PROTOCOL, protocol);
        out.entryTrue(K_REUSEABLE, reuseable);
        out.entryNotNull(K_ID, id);
        out.entryNotNull(K_DESCRIPTION, description);
        out.entryNotNull(K_CREATION, creation);
        out.entryNotNull(K_LAST_UPDATED, lastUpdated);
    }

    public static final IColumnType<ServiceDescriptor, String> PROTOCOL = BasicColumnType.ofProperty(ServiceDescriptor.class, "protocol");
    public static final IColumnType<ServiceDescriptor, SocketChannel> CHANNEL = BasicColumnType.ofProperty(ServiceDescriptor.class, "channel");
    public static final IColumnType<ServiceDescriptor, String> ADDRESS = BasicColumnType.ofProperty(ServiceDescriptor.class, "address");
    public static final IColumnType<ServiceDescriptor, String> ID = BasicColumnType.ofProperty(ServiceDescriptor.class, "id");
    public static final IColumnType<ServiceDescriptor, String> DESCRIPTION = BasicColumnType.ofProperty(ServiceDescriptor.class, "description");
    public static final IColumnType<ServiceDescriptor, LocalDateTime> CREATION = BasicColumnType.ofProperty(ServiceDescriptor.class, "creation");
    public static final IColumnType<ServiceDescriptor, LocalDateTime> LAST_UPDATED = BasicColumnType.ofProperty(ServiceDescriptor.class, "lastUpdated");

}
