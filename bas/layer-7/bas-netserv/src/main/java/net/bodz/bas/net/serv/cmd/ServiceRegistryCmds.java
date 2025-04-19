package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Map;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.IServiceChannelRegistry;
import net.bodz.bas.net.serv.IServiceManager;
import net.bodz.bas.net.serv.ServiceDescriptor;

public class ServiceRegistryCmds
        extends AbstractNioCommandProvider {

    public static final String FORMAT_PLAIN = "plain";
    public static final String FORMAT_JSON = "json";

    IServiceManager serviceManager;
    IServiceChannelRegistry registry;

    public ServiceRegistryCmds(@NotNull SocketChannel channel, @NotNull IServiceManager serviceManager) {
        super(channel);
        this.serviceManager = serviceManager;
        this.registry = serviceManager;
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "register":
                register(cmd);
                return true;

            case "deregister":
                deregister(cmd);
                return true;

            case "discover":
                discover(cmd);
                return true;
        }
        return false;
    }

    public void register(Command cmd)
            throws IOException {
        String protocol = strictMode ? null : "starter";
        if (cmd.isArgumentPresent(1))
            protocol = cmd.getArgument(1);
        if (protocol == null) {
            error("expect protocol");
            return;
        }
        try {
            registry.registerChannel(channel, protocol);
        } catch (Exception e) {
            error(e);
        }
    }

    public void deregister(Command cmd)
            throws IOException {
        if (!cmd.isArgumentPresent(1)) {
            error("expect id");
            return;
        }
        String id = cmd.getArgument(1);
        try {
            registry.removeChannel(id);
        } catch (Exception e) {
            error(e);
        }
    }

    public void discover(Command cmd)
            throws IOException {
        String format = FORMAT_PLAIN;
        if (cmd.isArgumentPresent(1))
            format = cmd.getArgument(1);
        discover(format);
    }

    public void discover(String format)
            throws IOException {
        switch (format) {
            case FORMAT_PLAIN:
                for (String protocol : serviceManager.getProtocols()) {
                    Map<String, ServiceDescriptor> descriptors = serviceManager.findByProtocol(protocol);
                    for (String id : descriptors.keySet()) {
                        ServiceDescriptor descriptor = descriptors.get(id);
                        String address = descriptor.getAddress();

                        StringBuilder buf = new StringBuilder();
                        buf.append(id);

                        buf.append(" ").append(protocol);

                        if (address != null)
                            buf.append(" (").append(address).append(")");

                        println(buf.toString());
                    }
                }
                break;

            case FORMAT_JSON:
                if (serviceManager instanceof IJsonForm) {
                    IJsonForm jsonForm = (IJsonForm) serviceManager;
                    try {
                        String json = JsonFn.toJson(jsonForm);
                        println(json);
                    } catch (FormatException e) {
                        error("Can't generate JSON: " + e.getMessage());
                    }
                } else {
                    error("Format JSON is not supported.");
                }
                break;

            default:
                error("Invalid format " + format);
        }
    }

}
