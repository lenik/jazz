package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.text.parser.LineQueue;

public class ChatSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(ChatSession.class);

    LineQueue lineQueue = new LineQueue();

    public ChatSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller)
            throws IOException {
        super(name, channel, poller);
        greet();
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        long totalBytesRead = 0;

        while (true) {
            int numBytesRead = channel.read(buf);
            logger.info("numBytesRead: " + numBytesRead);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return totalBytesRead;
                default:
                    totalBytesRead += numBytesRead;
            }

            buf.flip();
            lineQueue.putOctets(buf);
            buf.clear();

            try {
                lineQueue.parse();
            } catch (ParseException e) {
                logger.error(e, "error parse: " + e.getMessage());
                lineQueue.dropLine();
            }

            while (lineQueue.isNotEmpty()) {
                String line = lineQueue.take();
                parseAndReply(line);
            }
        }
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
