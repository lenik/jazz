package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipEntryOutputStream extends OutputStream {

    private ZipOutputStream out;

    public ZipEntryOutputStream(ZipOutputStream out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    public ZipEntryOutputStream(ZipOutputStream out, ZipEntry entry)
            throws IOException {
        this(out);
        out.putNextEntry(entry);
    }

    public ZipEntryOutputStream(ZipOutputStream out, String name)
            throws IOException {
        this(out, new ZipEntry(name));
    }

    @Override
    public void close() throws IOException {
        out.closeEntry();
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

}
