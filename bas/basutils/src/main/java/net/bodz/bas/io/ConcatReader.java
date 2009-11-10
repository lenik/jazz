package net.bodz.bas.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

public class ConcatReader extends Reader {

    protected final Queue<Reader> q;
    private boolean fast = true;

    public ConcatReader(Queue<Reader> queue) {
        if (queue == null)
            throw new NullPointerException();
        q = queue;
    }

    public ConcatReader(Reader... readers) {
        this(defaultQueue(readers));
    }

    static Queue<Reader> defaultQueue(Reader... readers) {
        LinkedList<Reader> queue = new LinkedList<Reader>();
        for (Reader reader : readers)
            queue.add(reader);
        return queue;
    }

    public void add(Reader reader) {
        q.add(reader);
    }

    @Override
    public void close() throws IOException {
        while (!q.isEmpty()) {
            Reader reader = q.remove();
            reader.close();
        }
    }

    @Override
    public int read() throws IOException {
        if (q.isEmpty())
            return -1;
        Reader head = q.peek();
        int c = head.read();
        if (c == -1) {
            q.remove();
            return read();
        }
        return c;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (q.isEmpty())
            return -1;
        int sum = 0;
        Reader head = q.peek();
        while (len > 0) {
            int read = head.read(cbuf, off, len);
            if (read == -1) {
                q.remove();
                int rest = read(cbuf, off, len);
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

    @Override
    public int read(CharBuffer target) throws IOException {
        if (q.isEmpty())
            return -1;
        int len = target.remaining();
        int sum = 0;
        Reader head = q.peek();
        while (len > 0) {
            int read = head.read(target);
            if (read == -1) {
                q.remove();
                int rest = read(target);
                if (rest != -1)
                    sum += rest;
                break;
            } else {
                boolean partial = read != len;
                if (partial && fast)
                    break;
                else {
                    len -= read;
                    sum += read;
                }
            }
        }
        return sum;
    }

    @Override
    public boolean ready() throws IOException {
        if (q.isEmpty())
            return false;
        Reader head = q.peek();
        return head.ready();
    }

    @Override
    public long skip(long n) throws IOException {
        if (q.isEmpty())
            return 0;
        long sum = 0;
        Reader head = q.peek();
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
