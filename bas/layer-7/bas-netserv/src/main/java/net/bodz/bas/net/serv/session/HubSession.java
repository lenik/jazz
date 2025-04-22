package net.bodz.bas.net.serv.session;

import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.IServiceManager;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.cmd.GatewayCmds;
import net.bodz.bas.net.serv.cmd.ForwardCmds;
import net.bodz.bas.net.serv.cmd.ServerCmds;
import net.bodz.bas.net.serv.cmd.ServiceRegistryCmds;

public class HubSession
        extends StarterSession {

    public HubSession(SocketChannel channel, @NotNull ISocketPoller poller, @NotNull ISessionManager sessionManager, IServiceManager serviceManager) {
        super(channel, poller, sessionManager);

        addProvider(new ServiceRegistryCmds(channel, serviceManager));

        addProvider(new ForwardCmds(channel, this, poller, //
                s -> sessionManager.switchSession(this, s)));

        addProvider(new ServerCmds(channel, poller,//
                s -> sessionManager.switchSession(this, s)));

        addProvider(new GatewayCmds(channel, serviceManager,//
                s -> sessionManager.switchSession(this, s)));

    }

}
