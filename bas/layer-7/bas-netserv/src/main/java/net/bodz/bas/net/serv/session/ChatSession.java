package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.net.serv.Session;
import net.bodz.bas.parser.LineQueue;

public class ChatSession
        extends Session {

    LineQueue lines = new LineQueue();

    public ChatSession(SocketChannel channel) {
        super(channel);
    }

    @Override
    public void onDataReady(SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int numBytesRead = channel.read(buf);
        if (numBytesRead == -1)                        // The client has closed the connection
        {
            stop();
            return;
        }
        if (numBytesRead == 0)
            return;

        while (buf.hasRemaining()) {
            byte b = buf.get();
            lines.putOctet(b & 0xFF);
        }

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
                    stop();
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
