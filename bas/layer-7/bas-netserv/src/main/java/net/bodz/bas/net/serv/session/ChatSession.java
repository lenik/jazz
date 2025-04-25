package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.IReadResult;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.text.parser.LineQueue;

public class ChatSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(ChatSession.class);

    LineQueue lineQueue = new LineQueue();

    public ChatSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller)
            throws IOException {
        super(name, channel, poller);

        buffer.setDecodeMode(true);

        greet();
    }

    @Override
    public IReadResult read(@NotNull SocketChannel channel)
            throws IOException {
        IReadResult rr = buffer.read(channel);

        lineQueue.buffer.append(buffer);
        buffer.clear();

        try {
            lineQueue.parse();
        } catch (ParseException e) {
            logger.error(e, "error parse: " + e.getMessage());
        }

        while (lineQueue.isNotEmpty()) {
            String line = lineQueue.take();
            parseAndReply(line);
        }

        return rr;
    }

    static final Pattern messagePattern = Pattern.compile("^\\s*(\\w+)(\\s+(.*?)\\s*)?$");

    public void parseAndReply(@NotNull String line)
            throws IOException {
        Matcher m = messagePattern.matcher(line);
        if (m.matches()) {
            String verb = m.group(1);
            String message = m.group(2);
            switch (verb) {
                case "hello":
                    logger.info("message: " + message);
                    buffer.printLine("hi, " + message + "!");
                    break;

                case "bye":
                    buffer.printLine("good bye!");
                    close();
                    break;

                default:
                    buffer.printLine("umm.. I couldn't understand yet.");
            }
        }
    }

    void greet()
            throws IOException {
        buffer.printLine("hey, chatbot here!");
    }

}
