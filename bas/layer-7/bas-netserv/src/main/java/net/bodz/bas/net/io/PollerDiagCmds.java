package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.cmd.AbstractNioCommandProvider;

public class PollerDiagCmds
        extends AbstractNioCommandProvider {

    DefaultPoller poller;

    public PollerDiagCmds(@NotNull SocketChannel channel, @NotNull DefaultPoller poller) {
        super(channel);
        this.poller = poller;
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        if (!"poller".equals(cmd.getName()))
            return false;

        cmd = cmd.makeCommand();
        switch (cmd.getName()) {
            case "selector":
                showSelector();
                break;

            case "list":
                listListeners();
                break;
        }
        return false;
    }

    void showSelector()
            throws IOException {
        Selector selector = poller.selector;
        println("selector " + selector + " " + (selector.isOpen() ? "open" : "closed"));
        println("    provider: " + selector.provider());
        println("    keys: " + selector.keys());
        println("    selected keys: " + selector.selectedKeys());
    }

    void listListeners()
            throws IOException {
        for (SelectableChannel channel : poller.map.keySet()) {
            ChannelLink link = poller.map.get(channel);

            StringBuilder buf = new StringBuilder();
            buf.append("channel ").append(channel);
            if (channel.isBlocking())
                buf.append(" blocking");
            if (channel.isOpen())
                buf.append(" open");
            if (channel.isRegistered())
                buf.append(" registered");
            if (link.description != null)
                buf.append(": ").append(link.description);
            println(buf.toString());

            if (link.accepter != null)
                println("    accepter: " + link.accepter);
            if (link.connector != null)
                println("    connector: " + link.connector);
            if (link.reader != null)
                println("    reader: " + link.reader);
            if (link.writer != null)
                println("    writer: " + link.writer);
        }
    }

}
