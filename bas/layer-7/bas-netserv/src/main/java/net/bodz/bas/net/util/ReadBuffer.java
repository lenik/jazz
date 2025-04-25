package net.bodz.bas.net.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import net.bodz.bas.c.java.util.Sets;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.buffer.ByteArrayBuffer;
import net.bodz.bas.t.buffer.CharArrayBuffer;
import net.bodz.bas.t.buffer.ICharBuffer;
import net.bodz.bas.t.variant.IVariant;

public class ReadBuffer
        extends ByteArrayBuffer
        implements ISettingParsable {

    static final Logger logger = LoggerFactory.getLogger(ReadBuffer.class);

    boolean debug = true;

    int readTimeout;
    long lastReadTime;
    List<ITimeoutListener> readTimeoutListeners = new ArrayList<>(1);

    int maxReadCount;
    Predicate<Byte> readByteUntil;
    Predicate<Character> readCharUntil;    // only work in decoded mode

    Charset charset = StandardCharsets.UTF_8;
    boolean decodeMode;
    //    private final ByteArrayBuffer readBuffer = this; // new ByteArrayBuffer(4096);
    public final CharArrayBuffer decoded = new CharArrayBuffer(4096);
    boolean readStopped;
    ReadStopSupport readStopSupport = new ReadStopSupport(this);

    List<IDataReadyListener> bufferReadyListeners = new ArrayList<>(1);
    List<IDataReadyListener> charBufferReadyListeners = new ArrayList<>(1);

    public ReadBuffer() {
        this(4096);
    }

    public ReadBuffer(int initialCapacity) {
        super(initialCapacity);
    }

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

    public int read(ByteBuffer byteBuf) {
        int numBytesRead = 0;
        if (!readStopped) {
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

            numBytesRead += byteBuf.remaining();
            append(byteBuf);

            if (isNotEmpty())
                fireBufferReady();

            if (decodeMode) {
                decode();

                if (decoded.isNotEmpty())
                    fireCharBufferReady();
            }

            if (maxReadCount > 0) {
                maxReadCount--;
                if (maxReadCount == 0) {
                    logger.debug("Reached to the max read count, stop reading.");
                    stopRead();
                }
            }
        }
        return numBytesRead;
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

    public ICharBuffer decode() {
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

        while (isNotEmpty()) {
            int block = Math.min(length(), byteBuf.remaining());
            if (block == 0)
                throw new UnexpectedException();
            get(0, byteBuf, block);
            boolean endOfInput = block == length();

            byteBuf.flip();
            decoder.decode(byteBuf, charBuf, endOfInput);
            byteBuf.compact();
            int pending = byteBuf.position();
            delete(0, block - pending);

            charBuf.flip();
            decoded.append(charBuf);
            charBuf.clear();
        } // while targetBuffer is not empty
        assert isEmpty();

        if (readCharUntil != null) {
            char[] backedArray = decoded.getBackedArray();
            int backedArrayOffset = decoded.getBackedArrayOffset();
            int len = decoded.length();
            int end = backedArrayOffset + len;
            for (int pos = backedArrayOffset; pos < end; pos++) {
                if (readCharUntil.test(backedArray[pos])) {
                    decoded.delete(pos - backedArrayOffset, end - pos);
                    readStopped = true;
                    break;
                }
            }
        } // if defined readCharUntil
        return decoded;
    }

    public String getReadString() {
        if (decodeMode) {
            return decoded.toString();
        } else {
            return toString(charset);
        }
    }

    @Override
    public void clear() {
        super.clear();
        decoded.clear();
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

    public void addBufferReadyListener(@NotNull IDataReadyListener l) {
        bufferReadyListeners.add(l);
    }

    public void removeBufferReadyListener(@NotNull IDataReadyListener l) {
        bufferReadyListeners.remove(l);
    }

    void fireBufferReady() {
        for (IDataReadyListener l : bufferReadyListeners) {
            l.onDataReady();
        }
    }

    public void addCharBufferReadyListener(@NotNull IDataReadyListener l) {
        charBufferReadyListeners.add(l);
    }

    public void removeCharBufferReadyListener(@NotNull IDataReadyListener l) {
        charBufferReadyListeners.remove(l);
    }

    void fireCharBufferReady() {
        for (IDataReadyListener l : charBufferReadyListeners) {
            l.onDataReady();
        }
    }

    @Override
    public String toString() {
        return length() + " bytes (+" + //
                decoded.length() + " chars)";
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
                return readByteUntil == null ? null : readByteUntil.toString();
            case K_READ_CHAR_UNTIL:
                return readCharUntil == null ? null : readCharUntil.toString();
            case K_CHARSET:
                return charset.name();
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
            case K_CHARSET:
                charset = Charset.forName(var.getString());
                break;
            default:
                return false;
        }
        return true;
    }

}
