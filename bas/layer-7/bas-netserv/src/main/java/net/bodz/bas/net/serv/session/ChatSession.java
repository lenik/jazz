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
import net.bodz.bas.text.parser.LineQueue;

public class ChatSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(ChatSession.class);

    LineQueue lineQueue = new LineQueue();

    public ChatSession(@NotNull SocketChannel channel)
            throws IOException {
        super(channel);
        greet();
    }

    @Override
    public boolean read(@NotNull SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (true) {
            int numBytesRead = channel.read(buf);
            logger.info("numBytesRead: " + numBytesRead);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return true;
            }

            buf.flip();
            lineQueue.putOctets(buf);
            buf.clear();

            lineQueue.parse();
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
                    println("hi, " + message + "!");
                    break;

                case "bye":
                    println("good bye!");
                    close();
                    break;

                default:
                    println("umm.. I couldn't understand yet.");
            }
        }
    }

    void greet()
            throws IOException {
        println("hey, chatbot here!");
    }

    void println(String s)
            throws IOException {
        byte[] bin = (s + "\n").getBytes();
        channel.write(ByteBuffer.wrap(bin));
    }

}
