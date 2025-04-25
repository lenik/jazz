package net.bodz.bas.net.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.java.util.Sets;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.buffer.ByteArrayBuffer;
import net.bodz.bas.t.variant.IVariant;

public class WriteBuffer
        extends ByteArrayBuffer
        implements ISettingParsable,
                   IByteOut,
                   IPrintOut {

    boolean debug;

    int writeTimeout;
    long lastWriteTime;
    List<ITimeoutListener> writeTimeoutListeners = new ArrayList<>(1);

    //    public final Printer out = new Printer();
    Charset charset = StandardCharsets.UTF_8;
    String linePrefix;
    String lineSuffix;
    String lineFormat;
    String eolChar = "\n";
    boolean writeClosed;

    public WriteBuffer() {
        this(4096);
    }

    public WriteBuffer(int initialCapacity) {
        super(initialCapacity);
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public long getLastWriteTime() {
        return lastWriteTime;
    }

    public void setLastWriteTime(long lastWriteTime) {
        this.lastWriteTime = lastWriteTime;
    }

    public void touch() {
        lastWriteTime = System.currentTimeMillis();
    }

    public long getWriteExpire() {
        return lastWriteTime + writeTimeout;
    }

    public void addTimeoutListener(@NotNull ITimeoutListener l) {
        writeTimeoutListeners.add(l);
    }

    public void removeTimeoutListener(@NotNull ITimeoutListener l) {
        writeTimeoutListeners.remove(l);
    }

    protected void fireTimeout() {
        long expire = getWriteExpire();
        TimeoutEvent e = new TimeoutEvent(this, expire);
        for (ITimeoutListener l : writeTimeoutListeners)
            l.onTimeout(e);
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
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
        print(buf.toString());
    }

    @Override
    public String toString(Charset charset) {
        return length() + " bytes pending";
    }

    // IByteOut

    @Override
    public void writeByte(int b)
            throws IOException {
        append(b);
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        append(buf, off, len);
    }

    @Override
    public void write(ByteBuffer buf)
            throws IOException {
        append(buf);
    }
    
    // IPrintOut

    @Override
    public void print(String s) {
        if (s == null)
            s = "null";
        byte[] bytes = s.getBytes(charset);
        append(bytes);
    }

    @Override
    public void writeChar(int ch)
            throws IOException {
        char[] buf = new char[] { (char) ch };
        write(buf, 0, buf.length);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        CharBuffer charBuf = CharBuffer.wrap(chars, off, len);
        ByteBuffer encoded = charset.encode(charBuf);
        append(encoded);
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

    static final String K_DEBUG = "debug";
    static final String K_WRITE_TIME = "writeTime";
    static final String K_CHARSET = "charset";
    static final String K_PREFIX = "prefix";
    static final String K_SUFFIX = "suffix";
    static final String K_FORMAT = "format";
    static final String K_EOL = "eol";

    static final Set<String> NAMES = Sets.of(//
            K_DEBUG,  //
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
