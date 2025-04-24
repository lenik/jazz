package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.cmd.ICommandProvider;

public class StarterSession
        extends DirectiveSocketSession
        implements ICommandProvider {

    ISessionManager sessionManager;

    public StarterSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull ISessionManager sessionManager) {
        super(name, channel, poller);
        this.sessionManager = sessionManager;
        addProvider(this);
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "chat":
                chat(cmd);
                return true;
        }
        return false;
    }

    void chat(Command cmd)
            throws IOException {
        ChatSession chatSession = new ChatSession(name + "_chat", channel, poller);
        sessionManager.switchSession(this, chatSession);
    }

}
