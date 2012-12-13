package net.bodz.bas.t.buffer;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;

public abstract class _PagedBuffer
        implements IPagedBuffer {

    protected abstract MemPage getFirst();

    protected abstract MemPage getLast();

    //
    // protected abstract void setFirst(MemPage first);
    //
    // protected abstract void setLast(MemPage last);

    protected abstract MemPage newPage(int minSize);

    @Override
    public MemPage add(byte x) {
        MemPage last = getLast();
        if (last != null && last.isManaged()) {
            ByteBuffer lastBuffer = last.buffer;
            int limit = lastBuffer.limit();
            if (limit < lastBuffer.capacity()) {
                lastBuffer.limit(limit + 1);
                lastBuffer.put(limit, x);
                return last;
            }
        }
        byte[] buf = new byte[] { x };
        return add(buf, 0, 1);
    }

    @Override
    public MemPage add(byte[] buf, int off, int len) {
        MemPage last = getLast();
        if (last != null && last.isManaged()) {
            ByteBuffer lastBuffer = last.buffer;
            int free = lastBuffer.capacity() - lastBuffer.limit();
            int addToLast = Math.min(free, len);
            last.buffer = Buffers._insert(lastBuffer, lastBuffer.limit(), buf, off, addToLast);
            off += addToLast;
            len -= addToLast;
        }
        if (len == 0)
            return last;
        ByteBuffer bb = ByteBuffer.wrap(buf, off, len).slice();
        return add(bb);
    }

    @Override
    public MemPage add(byte[] buf) {
        return add(buf, 0, buf.length);
    }

    @Override
    public MemPage insert(PagePos pos, byte x) {
        return null;
    }

    @Override
    public MemPage insert(PagePos pos, byte[] buf, int off, int len) {
        if (pos == null)
            return add(buf, off, len);
        MemPage page = pos.page;
        assert page != null;
        if (len == 0)
            return page;
        if (page.isManaged()) {
            ByteBuffer pageBuffer = page.buffer;
            int free = pageBuffer.capacity() - pageBuffer.limit();
            int addToPage = Math.min(free, len);
            page.buffer = Buffers._insert(pageBuffer, pos.offset, buf, off, addToPage);
            off += addToPage;
            len -= addToPage;
            // <page, NEWPAGE(rest-buf), page.next>
            // insertAfter = page
        } else if (pos.offset == 0) {
            // insertBefore = pos.page.getPrev();
        } else {
            // split page to 2 parts: <page.1, NEWPAGE(buf), page.2, page.next>
            ByteBuffer pageBuffer = page.buffer;
            byte[] array = pageBuffer.array();
            int arrayOffset = pageBuffer.arrayOffset();
            int limit = pageBuffer.limit();
            byte[] p1buf = new byte[pos.offset];
            byte[] p2buf = new byte[limit - pos.offset];
            System.arraycopy(array, arrayOffset, p1buf, 0, pos.offset);
            System.arraycopy(array, arrayOffset + pos.offset, p2buf, 0, limit - pos.offset);
            MemPage p1 = new MemPage(ByteBuffer.wrap(p1buf));
            MemPage p2 = new MemPage(ByteBuffer.wrap(p2buf));
            MemPage prev = page.getPrev();
            MemPage next = page.getNext();
            page.detach();
            prev.setNext(p1);
            p1.setNext(p2);
            p2.setNext(next);
            // insertAfter = p1;
        }
        ByteBuffer bb = ByteBuffer.wrap(buf, off, len).slice();
        return insert(pos, bb);
    }

    @Override
    public MemPage insert(PagePos pos, byte[] buf) {
        return insert(pos, buf, 0, buf.length);
    }

    @Override
    public MemPage insert(PagePos pos, int len, byte fill) {
        byte[] buf = new byte[len];
        Arrays.fill(buf, fill);
        return insert(pos, buf);
    }

    protected int getSize(PagePos begin, PagePos end) {
        if (begin == null)
            throw new NullPointerException("begin");
        if (end == null)
            throw new NullPointerException("end");
        if (begin.page == end.page)
            return end.offset - begin.offset;
        int size = begin.getPageRemaining() + end.offset;
        MemPage p = begin.page.getNext();
        while (p != end.page) {
            int limit = p.buffer.limit();
            size += limit; // XXX - overflow check?
            p = p.getNext();
        }
        return size;
    }

    @Override
    public byte[] getBytes(PagePos begin, PagePos end) {
        if (begin.page == end.page) {
            ByteBuffer simple = begin.page.buffer;
            byte[] array = simple.array();
            int offset = simple.arrayOffset();
            return Arrays.copyOfRange(array, offset + begin.offset, offset + end.offset);
        }

        int wsize = getSize(begin, end);
        int woff = 0;
        byte[] wbuf = new byte[wsize];
        MemPage p = begin.page;
        byte[] p0 = p.buffer.array();
        int p0_remaining = begin.getPageRemaining();
        System.arraycopy(p0, p.buffer.arrayOffset() + begin.offset, //
                wbuf, 0, p0_remaining);
        woff += p0_remaining;

        while (woff < wsize) {
            p = p.getNext();
            if (p == null)
                throw new BufferUnderflowException();
            byte[] array = p.buffer.array();
            int offset = p.buffer.arrayOffset();
            int limit;
            if (p == end.page)
                limit = end.offset;
            else
                limit = p.buffer.limit();
            if (woff + limit > wsize)
                throw new RuntimeException("pre-calculated size is invalid, maybe concurrently changed?");
            System.arraycopy(array, offset, wbuf, woff, limit);
            woff += limit;
        }
        return wbuf;
    }

    @Override
    public String getString(PagePos begin, PagePos end, Charset charset) {
        byte[] bytes = getBytes(begin, end);
        return new String(bytes, charset);
    }

    /**
     * @throws UnsupportedCharsetException
     */
    @Override
    public String getString(PagePos begin, PagePos end, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getString(begin, end, charset);
    }

    @Override
    public String getString(PagePos begin, PagePos end) {
        return getString(begin, end, Charset.defaultCharset());
    }

}
