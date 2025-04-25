package net.bodz.bas.net.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Buffers;
import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.IReadResult;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.io.ISocketWriter;
import net.bodz.bas.net.io.ReadResult;

public class SocketBuffer
        extends ReadBuffer
        implements ISocketReader,
                   ISocketWriter {

    static final Logger logger = LoggerFactory.getLogger(SocketBuffer.class);

    final SocketChannel channel;
    final ISocketPoller poller;

    boolean readStarted;
    ISocketReader readerOverride = this;

    boolean writerEnabled;
    public final WriteBuffer out = new WriteBuffer();
//    public final WriteBuffer.Printer out = writeBuffer.out;

    CloseSupport closeSupport = new CloseSupport(this);

    public SocketBuffer(SocketChannel channel, ISocketPoller poller) {
        this.channel = channel;
        this.poller = poller;
        addReadStoppedListener(event -> {
        });
    }

    String getLogPrefix() {
        StringBuilder buf = new StringBuilder(40);
        String info = SocketChannels.getConnectionShortInfo(channel);
        buf.append(info);
        if (info.contains(SocketChannels.ARROW))
            buf.append("…");
        buf.append("  ");
        return buf.toString();
    }

    @Override
    public void setCharset(@NotNull Charset charset) {
        super.setCharset(charset);
        out.setCharset(charset);
    }

    public boolean isReadStarted() {
        return readStarted;
    }

    public void startRead()
            throws IOException {
        startRead(readerOverride);
    }

    public void startRead(ISocketReader reader)
            throws IOException {
        if (!readStarted) {
            poller.registerRead(channel, reader);
            readStarted = true;
            this.readerOverride = reader;
        }
    }

    @Override
    protected void stopReadImpl() {
        if (readStarted) {
            poller.cancelRead(channel, readerOverride);
            readStarted = false;
        }
    }

    @Override
    public IReadResult read(@NotNull SocketChannel channel)
            throws IOException {
        logger.debug(getLogPrefix() + "read()");
        if (channel.socket().isClosed()) {
            logger.debug("socket is closed");
            detectClosed();
            return ReadResult.end();
        }

        if (readStopped)
            return ReadResult.numOfBytes(0);

        ByteBuffer byteBuf = ByteBuffer.allocate(4096);
        long totalBytesRead = 0;
        while (!readStopped) {
            int numBytesRead = channel.read(byteBuf);
            logger.debug("    read " + numBytesRead + " bytes: " + Buffers.preview(byteBuf, 30));

            switch (numBytesRead) {
                case -1:
                    // the channel input has reached end-of-stream
                    // but you can still write to the channel.. ?
                    // channel.socket().close();
                    stopRead();

                case 0:
                    return ReadResult.numOfBytes(totalBytesRead, numBytesRead == -1);
                default:
                    totalBytesRead += numBytesRead;
            }

            int remaining = byteBuf.remaining();
            byteBuf.flip();
            numBytesRead = super.read(byteBuf);

            if (numBytesRead < remaining) {
                byteBuf.limit(remaining);
                byteBuf.position(numBytesRead);
                byteBuf.compact();
                logger.debug("    partial discarded: " + Buffers.preview(byteBuf, 30));
            }

            byteBuf.clear();
        }
        return ReadResult.numOfBytes(totalBytesRead);
    }

    public boolean isWriterEnabled() {
        return writerEnabled;
    }

    public void enableWriter()
            throws IOException {
        if (!writerEnabled) {
            logger.debug(getLogPrefix() + "enable writer");
            poller.registerWrite(channel, this);
            writerEnabled = true;
        }
    }

    public void disableWriter() {
        if (writerEnabled) {
            logger.debug(getLogPrefix() + "disable writer");
            poller.cancelWrite(channel, this);
            writerEnabled = false;
        }
    }

    @Override
    public long write(@NotNull SocketChannel channel)
            throws IOException {
        int length = out.length();
        logger.debug(getLogPrefix() + "write(" + length + " bytes pending)");
        if (length == 0) {
            disableWriter();
            return 0;
        }

        int numBytesWritten = channel.write(out.asByteBuffer());
        logger.debug("    " + numBytesWritten + " bytes written");
        out.delete(0, numBytesWritten);

        return numBytesWritten;
    }

    void detectClosed() {
        stopRead();
        closeSupport.fireClose(false);
    }

    public void printLine(String s) {
        out.printLine(s);
    }

    public void printError(String message)
            throws IOException {
        printError(message, null);
    }

    public void printError(String message, Throwable e) {
        out.println(message);
        if (e != null) {
            StringWriter buf = new StringWriter();
            buf.write("Stacktrace:\n");
            e.printStackTrace(new PrintWriter((buf)));
            out.print(buf.toString());
        }
    }

}
