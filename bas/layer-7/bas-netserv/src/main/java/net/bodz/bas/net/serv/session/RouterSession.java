package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.ServiceDescriptor;
import net.bodz.bas.net.serv.ServiceManager;
import net.bodz.bas.net.serv.cmd.RedirectCmds;

public class RouterSession
        extends StarterSession {

    ServiceManager serviceManager = new ServiceManager();

    public RouterSession(String id, SocketChannel channel, @NotNull ISessionManager sessionManager, @NotNull ISocketPoller poller) {
        super(id, channel, sessionManager);
        addProvider(new RedirectCmds(channel, sessionManager, this, poller));
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "relay":
                relay(cmd);
                return true;
        }
        return false;
    }

    // GATEWAY SIDE

    void relay(Command cmd)
            throws IOException {
        int localPort = 0;

        String alloc = cmd.peek();
        if (StringPred.isDecimal(alloc)) {
            localPort = Integer.parseInt(alloc);
            cmd.shift();
        }

        if (!"to".equals(cmd.shift())) {
            println("expect keyword 'to''");
            return;
        }

        String serviceId = cmd.shift();
        if (serviceId == null) {
            println("expect target service id");
            return;
        }

        ServiceDescriptor descriptor = serviceManager.get(serviceId);
        String relaySessionId = getSessionId() + ":relay";
        // new RelayService(relaySessionId, );
    }

}
