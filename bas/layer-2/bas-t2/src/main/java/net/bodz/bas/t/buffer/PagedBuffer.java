package net.bodz.bas.t.buffer;

import java.nio.ByteBuffer;

public class PagedBuffer
        extends _PagedBuffer {

    int preferredPageSize;
    MemPage head;
    MemPage end;

    public PagedBuffer() {
        this(4096);
    }

    public PagedBuffer(int preferredPageSize) {
        this.preferredPageSize = preferredPageSize;
    }

    @Override
    protected MemPage getFirst() {
        return head;
    }

    @Override
    protected MemPage getLast() {
        return end;
    }

    @Override
    protected MemPage newPage(int minSize) {
        ByteBuffer buf = ByteBuffer.allocate(Math.max(minSize, preferredPageSize));
        MemPage page = new MemPage(buf, MemPage.MANAGED);
        return page;
    }

    @Override
    public MemPage add(byte x) {
        if (end == null)
            head = end = newPage(1);
        int limit = end.buffer.limit();
        if (limit == end.buffer.capacity()) {
            end = end.setNext(newPage(1));
            limit = 0;
        }
        end.buffer.limit(limit + 1);
        end.buffer.put(limit, x);
        return end;
    }

    @Override
    public MemPage add(ByteBuffer buf) {
        MemPage page = new MemPage(buf);
        end = end.setNext(page);
        return end;
    }

    @Override
    public MemPage insert(PagePos pos, byte x) {
        if (head == null)
            if (pos == null)
                return add(x);
        if (pos == null)
            throw new NullPointerException("pos");
        MemPage page = pos.page;
        if (!page.isManaged())
            ;
        return null;
    }

    @Override
    public MemPage insert(PagePos pos, ByteBuffer buf) {
        return null;
    }

    @Override
    public void delete(PagePos begin, PagePos end) {
    }

    @Override
    public void compact(int pageSize) {
    }

}
