package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.net.serv.cmd.ICommandProvider;

public class StarterSession
        extends DirectiveSocketSession
        implements ICommandProvider {

    @NotNull
    ISessionManager sessionManager;

    public StarterSession(SocketChannel channel, @NotNull ISessionManager sessionManager) {
        super(channel);
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
        ChatSession chatSession = new ChatSession(channel);
        sessionManager.switchSession(this, chatSession);
    }

}
