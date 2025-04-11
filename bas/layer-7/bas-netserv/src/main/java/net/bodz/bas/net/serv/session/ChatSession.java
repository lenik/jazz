package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.net.serv.AbstractSession;
import net.bodz.bas.parser.LineQueue;

public class ChatSession
        extends AbstractSession {

    LineQueue lines = new LineQueue();

    public ChatSession(String id, SocketChannel channel) {
        super(id, channel);
    }

    @Override
    public boolean read(SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (true) {
            int numBytesRead = channel.read(buf);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return true;
            }

            buf.flip();
            while (buf.hasRemaining()) {
                byte b = buf.get();
                lines.putOctet(b & 0xFF);
            }
            buf.clear();

            lines.parse();

            if (lines.isNotEmpty()) {
                Iterator<String> lineIterator = lines.iterator();
                while (lineIterator.hasNext()) {
                    String line = lineIterator.next();
                    lineIterator.remove();
                    onReceivedLine(line);
                }
            }
        }
    }

    static final Pattern messagePattern = Pattern.compile("^\\s*(\\w+)(\\s+(.*?)\\s*)?$");

    void onReceivedLine(String line)
            throws IOException {
        Matcher m = messagePattern.matcher(line);
        if (m.matches()) {
            String verb = m.group(1);
            String message = m.group(2);
            switch (verb) {
                case "hello":
                    echo("hey, chatbot here.");
                    break;
                case "bye":
                    echo("good boy!");
                    close();
                    break;
                default:
                    echo("umm.. I couldn't understand yet.");
            }
        }
    }

    void echo(String s)
            throws IOException {
        byte[] bin = (s + "\n").getBytes();
        channel.write(ByteBuffer.wrap(bin));
    }

}
