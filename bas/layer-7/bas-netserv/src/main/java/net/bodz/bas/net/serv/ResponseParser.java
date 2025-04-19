package net.bodz.bas.net.serv;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.cli.Command;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.cmd.AbstractNioCommandProvider;

public class ResponseParser
        extends AbstractNioCommandProvider {

    static final Logger logger = LoggerFactory.getLogger(ResponseParser.class);

    boolean readyToSend = true;

    public ResponseParser(@NotNull SocketChannel channel) {
        super(channel);
    }

    @Override
    public boolean execute(Command cmd)
            throws IOException, ParseException {
        boolean readyToSend = true;
        try {
            String head = cmd.peek();
            if (head.startsWith("Error:")) {
                logger.error("Received error: " + head);
                return true;
            }
        } finally {
            this.readyToSend |= readyToSend;
        }
        return true;
    }

}
