package net.bodz.bas.net.serv.session;

import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.IServiceManager;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.cmd.GatewayCmds;
import net.bodz.bas.net.serv.cmd.RedirectCmds;
import net.bodz.bas.net.serv.cmd.ServerCmds;
import net.bodz.bas.net.serv.cmd.ServiceRegistryCmds;

public class RouterSession
        extends StarterSession {

    public RouterSession(SocketChannel channel, @NotNull ISessionManager sessionManager, @NotNull ISocketPoller poller, IServiceManager serviceManager) {
        super(channel, sessionManager);

        addProvider(new ServiceRegistryCmds(channel, serviceManager));

        addProvider(new RedirectCmds(channel, poller, //
                s -> sessionManager.replaceSession(this, s)));

        addProvider(new ServerCmds(channel, poller,//
                s -> sessionManager.replaceSession(this, s)));

        addProvider(new GatewayCmds(channel, serviceManager,//
                s -> sessionManager.replaceSession(this, s)));

    }

}
