package net.bodz.bas.net.serv.cmd;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.cli.Command;
import net.bodz.bas.cli.IArgQueue;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.IServiceChannelRegistry;
import net.bodz.bas.net.serv.session.ISocketSession;

public class GatewayCmds
        extends AbstractNioCommandProvider {

    final IServiceChannelRegistry registry;
    final Consumer<ISocketSession> applier;

    public GatewayCmds(@NotNull SocketChannel channel, @NotNull IServiceChannelRegistry registry, @NotNull Consumer<ISocketSession> applier) {
        super(channel);
        this.registry = registry;
        this.applier = applier;
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

    void relay(IArgQueue args)
            throws IOException {
        int localPort = 0;

        String alloc = args.peek();
        if (StringPred.isDecimal(alloc)) {
            localPort = Integer.parseInt(alloc);
            args.shift();
        }

        if (!"to".equals(args.shift())) {
            println("expect keyword 'to''");
            return;
        }

        String serviceId = args.shift();
        if (serviceId == null) {
            println("expect target service id");
            return;
        }

        // new RelayService(relaySessionId, );
    }

}
