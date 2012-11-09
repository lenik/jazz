package net.bodz.bas.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

import net.bodz.bas.meta.codegen.GeneratedByCopyPaste;

public class ConcatInputStream
        extends InputStream {

    protected final Queue<InputStream> q;
    private boolean fast = true;

    public ConcatInputStream(Queue<InputStream> queue) {
        if (queue == null)
            throw new NullPointerException();
        q = queue;
    }

    public ConcatInputStream(InputStream... ins) {
        this(defaultQueue(ins));
    }

    @GeneratedByCopyPaste(ConcatReader.class)
    static Queue<InputStream> defaultQueue(InputStream... ins) {
        LinkedList<InputStream> queue = new LinkedList<InputStream>();
        for (InputStream in : ins)
            queue.add(in);
        return queue;
    }

    @Override
    public int available()
            throws IOException {
        if (q.isEmpty())
            return 0;
        InputStream head = q.peek();
        int avail = head.available();
        if (avail == 0) { // EOF.
            q.remove();
            return available();
        }
        return avail;
    }

    @GeneratedByCopyPaste(ConcatReader.class)
    @Override
    public void close()
            throws IOException {
        while (!q.isEmpty()) {
            InputStream in = q.remove();
            in.close();
        }
    }

    @GeneratedByCopyPaste(ConcatReader.class)
    @Override
    public int read()
            throws IOException {
        if (q.isEmpty())
            return -1;
        InputStream head = q.peek();
        int c = head.read();
        if (c == -1) {
            q.remove();
            return read();
        }
        return c;
    }

    @GeneratedByCopyPaste(ConcatReader.class)
    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        if (q.isEmpty())
            return -1;
        int sum = 0;
        InputStream head = q.peek();
        while (len > 0) {
            int read = head.read(b, off, len);
            if (read == -1) {
                q.remove();
                int rest = read(b, off, len);
                if (rest != -1)
                    sum += rest;
                break;
            } else {
                boolean partial = read != len;
                sum += read;
                if (partial && fast)
                    break;
                else {
                    off += read;
                    len -= read;
                }
            }
        }
        return sum;
    }

    @GeneratedByCopyPaste(ConcatReader.class)
    @Override
    public long skip(long n)
            throws IOException {
        if (q.isEmpty())
            return 0;
        long sum = 0;
        InputStream head = q.peek();
        while (n > 0) {
            long skipped = head.skip(n);
            if (skipped == 0) { // 0=EOF in skip().
                q.remove();
                long rest = skip(n);
                sum += rest;
                break;
            } else {
                boolean partial = skipped != n;
                if (partial && fast)
                    break;
                else {
                    n -= skipped;
                    sum += skipped;
                }
            }
        }
        return sum;
    }

}
