package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.ISessionManager;
import net.bodz.bas.cli.Command;
import net.bodz.bas.net.serv.cmd.ICommandProvider;

public class StarterSession
        extends AbstractCmdSession
        implements ICommandProvider {

    @NotNull
    ISessionManager sessionManager;

    public StarterSession(String id, SocketChannel channel, @NotNull ISessionManager sessionManager) {
        super(id, channel);
        this.sessionManager = sessionManager;
        addProvider(this);
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        switch (cmd.getName()) {
            case "echo":
                echo(cmd);
                return true;

            case "chat":
                chat(cmd);
                return true;
        }
        return false;
    }

    void echo(Command cmd)
            throws IOException {
        println(cmd.getArgumentsLine());
    }

    void chat(Command cmd)
            throws IOException {
        ChatSession chatSession = new ChatSession(id, channel);
        sessionManager.replaceSession(this, chatSession);
    }

}
