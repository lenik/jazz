package net.bodz.bas.net.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import net.bodz.bas.c.java.util.Sets;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.buffer.ByteArrayBuffer;
import net.bodz.bas.t.buffer.CharArrayBuffer;
import net.bodz.bas.t.variant.IVariant;

public class SmartBuffer
        implements ISettingParsable {

    static final Logger logger = LoggerFactory.getLogger(SmartBuffer.class);

    boolean debug = true;

    int readTimeout;
    long lastReadTime;
    long lastWriteTime;
    List<ITimeoutListener> readTimeoutListeners = new ArrayList<>(1);

    int maxReadCount;
    Predicate<Byte> readByteUntil;
    Predicate<Character> readCharUntil;    // only work in decoded mode

    Charset charset = StandardCharsets.UTF_8;
    boolean decodeMode;
    public final ByteArrayBuffer readBuffer = new ByteArrayBuffer(4096);
    public final CharArrayBuffer readCharBuffer = new CharArrayBuffer(4096);
    boolean readStopped;
    ReadStopSupport readStopSupport = new ReadStopSupport(this);

    public final ByteArrayBuffer writeBuffer = new ByteArrayBuffer(4096);
    public final Printer out = new Printer();
    String linePrefix;
    String lineSuffix;
    String lineFormat;
    String eolChar = "\n";
    boolean writeClosed;

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void touch() {
        lastReadTime = System.currentTimeMillis();
    }

    public long getReadExpire() {
        return lastReadTime + readTimeout;
    }

    public void addTimeoutListener(@NotNull ITimeoutListener l) {
        readTimeoutListeners.add(l);
    }

    public void removeTimeoutListener(@NotNull ITimeoutListener l) {
        readTimeoutListeners.remove(l);
    }

    protected void fireTimeout() {
        long expire = getReadExpire();
        TimeoutEvent e = new TimeoutEvent(this, expire);
        for (ITimeoutListener l : readTimeoutListeners)
            l.onTimeout(e);
    }

    public int getMaxReadCount() {
        return maxReadCount;
    }

    public void setMaxReadCount(int maxReadCount) {
        this.maxReadCount = maxReadCount;
    }

    public Predicate<Byte> getReadByteUntil() {
        return readByteUntil;
    }

    public void setReadByteUntil(Predicate<Byte> readByteUntil) {
        this.readByteUntil = readByteUntil;
    }

    public Predicate<Character> getReadCharUntil() {
        return readCharUntil;
    }

    public void setReadCharUntil(Predicate<Character> readCharUntil) {
        this.readCharUntil = readCharUntil;
    }

    public void read(ByteBuffer byteBuf) {
        if (readStopped)
            return;

        lastReadTime = System.currentTimeMillis();

        if (readByteUntil != null) {
            byteBuf.mark();
            int n = byteBuf.remaining();
            for (int i = 0; i < n; i++) {
                byte b = byteBuf.get();
                if (readByteUntil.test(b)) {
                    byteBuf.flip();
                    readStopped = true;
                    break;
                }
            }
            if (!readStopped)
                byteBuf.reset();
        }
        readBuffer.append(byteBuf);

        if (decodeMode)
            decode();

        if (maxReadCount > 0) {
            maxReadCount--;
            if (maxReadCount == 0) {
                logger.debug("Reached to the max read count, stop reading.");
                stopRead();
            }
        }
    }

    @NotNull
    public Charset getCharset() {
        return charset;
    }

    public void setCharset(@NotNull Charset charset) {
        this.charset = charset;
    }

    public boolean isDecodeMode() {
        return decodeMode;
    }

    public void setDecodeMode(boolean decodeMode) {
        this.decodeMode = decodeMode;
    }

    void decode() {
        CharsetDecoder decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPLACE);

        ByteBuffer byteBuf;
        CharBuffer charBuf;
        if (debug) {
            byteBuf = ByteBuffer.allocate(4);
            charBuf = CharBuffer.allocate(2);
        } else {
            byteBuf = ByteBuffer.allocate(4096);
            charBuf = CharBuffer.allocate(4096);
        }

        while (readBuffer.isNotEmpty()) {
            int block = Math.min(readBuffer.length(), byteBuf.remaining());
            if (block == 0)
                throw new UnexpectedException();
            readBuffer.get(0, byteBuf, block);
            boolean endOfInput = block == readBuffer.length();

            byteBuf.flip();
            decoder.decode(byteBuf, charBuf, endOfInput);
            byteBuf.compact();
            int pending = byteBuf.remaining();
            readBuffer.delete(0, block - pending);

            charBuf.flip();
            readCharBuffer.append(charBuf);
            charBuf.clear();
        } // while targetBuffer is not empty
        assert readBuffer.isEmpty();

        if (readCharUntil != null) {
            char[] backedArray = readCharBuffer.getBackedArray();
            int backedArrayOffset = readCharBuffer.getBackedArrayOffset();
            int len = readCharBuffer.length();
            int end = backedArrayOffset + len;
            for (int pos = backedArrayOffset; pos < end; pos++) {
                if (readCharUntil.test(backedArray[pos])) {
                    readCharBuffer.delete(pos - backedArrayOffset, end - pos);
                    readStopped = true;
                    break;
                }
            }
        } // if defined readCharUntil
    }

    public String getReadString() {
        if (decodeMode) {
            return readCharBuffer.toString();
        } else {
            return readBuffer.toString(charset);
        }
    }

    public void clearReadBuffer() {
        if (decodeMode)
            readCharBuffer.clear();
        else
            readBuffer.clear();
    }

    public boolean isReadStopped() {
        return readStopped;
    }

    public void stopRead() {
        stopRead(null, null);
    }

    public void stopRead(Object value, Throwable exception) {
        if (readStopped)
            return;

        stopReadImpl();

        readStopped = true;

        ReadStopEvent event = new ReadStopEvent(this);
        if (exception != null) {
            event.setErrored(true);
            event.setException(exception);
        } else {
            event.setValue(value);
        }
        readStopSupport.fireReadStopped(event);
    }

    protected void stopReadImpl() {
    }

    public void addReadStoppedListener(@NotNull IReadStoppedListener l) {
        readStopSupport.addReadStoppedListener(l);
    }

    public void removeReadStoppedListener(@NotNull IReadStoppedListener l) {
        readStopSupport.removeReadStoppedListener(l);
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

    public void printLine(String s) {
        StringBuilder buf = new StringBuilder(s.length() + 32);
        if (lineFormat != null)
            s = String.format(lineFormat, s);
        if (linePrefix != null)
            buf.append(linePrefix);
        buf.append(s);
        if (lineSuffix != null)
            buf.append(lineSuffix);
        if (eolChar != null)
            buf.append(eolChar);
        out.print(buf.toString());
    }

    public class Printer
            implements IPrintOut,
                       IByteOut {

        @Override
        public void print(String s) {
            if (s == null)
                s = "null";
            byte[] bytes = s.getBytes(charset);
            writeBuffer.append(bytes);
        }

        @Override
        public void write(int b)
                throws IOException {
            writeBuffer.append(b);
        }

        @Override
        public void write(@NotNull byte[] buf, int off, int len)
                throws IOException {
            writeBuffer.append(buf, off, len);
        }

        @Override
        public void write(@NotNull char[] chars, int off, int len)
                throws IOException {
            CharBuffer charBuf = CharBuffer.wrap(chars, off, len);
            ByteBuffer encoded = charset.encode(charBuf);
            writeBuffer.append(encoded);
        }

        @Override
        public void flush() {
        }

        /**
         * Close the output.
         */
        @Override
        public synchronized void close() {
            if (writeClosed)
                return;
            writeClosed = true;
        }

    } // class Printer

    public void moveTo(SmartBuffer dst) {
        dst.writeBuffer.append(readBuffer);
        readBuffer.clear();
    }

    public void moveCharsTo(SmartBuffer dst) {
        char[] backedArray = readCharBuffer.getBackedArray();
        int backedArrayOffset = readCharBuffer.getBackedArrayOffset();
        int length = readCharBuffer.length();

        CharsetEncoder encoder = dst.charset.newEncoder();
        encoder.onMalformedInput(CodingErrorAction.REPLACE);

        ByteBuffer encoded;
        try {
            encoded = encoder.encode(CharBuffer.wrap(backedArray, backedArrayOffset, length));
        } catch (CharacterCodingException e) {
            logger.error("error encode", e);
            return;
        }

        dst.writeBuffer.append(encoded);
        readCharBuffer.clear();
    }

    @Override
    public String toString() {
        return "read " + readBuffer.length() + " bytes (+" + //
                readCharBuffer.length() + " chars)" + //
                ", write " + writeBuffer.length() + " bytes pending";
    }

    static final String K_DEBUG = "debug";
    static final String K_READ_TIMEOUT = "readTimeout";
    static final String K_READ_TIME = "readTime";
    static final String K_MAX_READ = "maxRead";
    static final String K_READ_UNTIL = "readUntil";
    static final String K_READ_CHAR_UNTIL = "readCharUntil";
    static final String K_WRITE_TIME = "writeTime";
    static final String K_CHARSET = "charset";
    static final String K_PREFIX = "prefix";
    static final String K_SUFFIX = "suffix";
    static final String K_FORMAT = "format";
    static final String K_EOL = "eol";

    static final Set<String> NAMES = Sets.of(//
            K_DEBUG,  //
            K_READ_TIMEOUT, K_READ_TIME, K_MAX_READ, K_READ_UNTIL, K_READ_CHAR_UNTIL, //
            K_WRITE_TIME, //
            K_CHARSET, //
            K_PREFIX, K_SUFFIX, K_FORMAT, K_EOL);

    @NotNull
    @Override
    public Set<String> getSettingNames() {
        return NAMES;
    }

    @Override
    public Object getSetting(@NotNull String name) {
        switch (name) {
            case K_DEBUG:
                return debug;
            case K_READ_TIMEOUT:
                return readTimeout;
            case K_READ_TIME:
                return lastReadTime;
            case K_MAX_READ:
                return maxReadCount;
            case K_READ_UNTIL:
                return readByteUntil.toString();
            case K_READ_CHAR_UNTIL:
                return readCharUntil.toString();
            case K_WRITE_TIME:
                return lastWriteTime;
            case K_CHARSET:
                return charset.name();
            case K_PREFIX:
                return linePrefix;
            case K_SUFFIX:
                return lineSuffix;
            case K_FORMAT:
                return lineFormat;
            case K_EOL:
                return eolChar;
        }
        return null;
    }

    @Override
    public boolean setSettingVar(@NotNull String name, @NotNull IVariant var) {
        switch (name) {
            case K_DEBUG:
                debug = var.getBoolean();
                break;
            case K_READ_TIMEOUT:
                readTimeout = var.getInt();
                break;
            case K_READ_TIME:
                lastReadTime = var.getLong();
                break;
            case K_MAX_READ:
                maxReadCount = var.getInt();
                break;
            case K_READ_UNTIL:
                readByteUntil = null;
                break;
            case K_READ_CHAR_UNTIL:
                readCharUntil = null;
                break;
            case K_WRITE_TIME:
                lastWriteTime = var.getLong();
                break;
            case K_CHARSET:
                charset = Charset.forName(var.getString());
                break;
            case K_PREFIX:
                linePrefix = var.getString();
                break;
            case K_SUFFIX:
                lineSuffix = var.getString();
                break;
            case K_FORMAT:
                lineFormat = var.getString();
                break;
            case K_EOL:
                eolChar = var.getString();
                break;
            default:
                return false;
        }
        return true;
    }

}
