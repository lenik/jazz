package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.DefaultServiceManager;
import net.bodz.bas.net.serv.cmd.GatewayCmds;
import net.bodz.bas.net.serv.cmd.RedirectCmds;
import net.bodz.bas.net.serv.cmd.ServiceRegistry;

public class RouterSession
        extends StarterSession {

    DefaultServiceManager serviceManager = new DefaultServiceManager();
    ServiceRegistry registry;

    public RouterSession(SocketChannel channel, @NotNull ISessionManager sessionManager, @NotNull ISocketPoller poller) {
        super(channel, sessionManager);

        registry = new ServiceRegistry(serviceManager);

        addProvider(new RedirectCmds(channel, poller, //
                s -> sessionManager.replaceSession(this, s)));
        addProvider(new GatewayCmds(channel, registry,//
                s -> sessionManager.replaceSession(this, s)));
    }

}
