package net.bodz.bas.types.buf;

import java.nio.ByteBuffer;

import net.bodz.bas.lang.err.OutOfDomainException;

public class MemPage {

    public static final int MANAGED  = 1;
    public static final int READONLY = 2;

    public ByteBuffer       buffer;
    private MemPage         prev;
    private MemPage         next;
    private int             flags;

    public MemPage(ByteBuffer buffer) {
        this(buffer, 0);
    }

    public MemPage(ByteBuffer buffer, int flags) {
        this.buffer = buffer;
        this.flags = flags;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean isManaged() {
        return (flags & MANAGED) != 0;
    }

    public void setManaged(boolean managed) {
        flags &= ~MANAGED;
        if (managed)
            flags |= MANAGED;
    }

    public boolean isReadOnly() {
        return (flags & READONLY) != 0;
    }

    public void setReadOnly(boolean readOnly) {
        flags &= ~READONLY;
        if (readOnly)
            flags |= READONLY;
    }

    public MemPage getPrev() {
        return prev;
    }

    /**
     * @return newPrev
     */
    public MemPage setPrev(MemPage newPrev) {
        if (this.prev != null)
            throw new IllegalStateException("prev is already bound to " + this.prev);
        if (newPrev != null && newPrev.next != null)
            throw new IllegalStateException("newPrev.next is already bound to " + newPrev.next);
        this.prev = newPrev;
        if (newPrev != null)
            newPrev.next = this;
        return newPrev;
    }

    public MemPage detachPrev() {
        MemPage prev = this.prev;
        if (prev != null) {
            prev.next = null;
            this.prev = null;
        }
        return prev;
    }

    public MemPage getNext() {
        return next;
    }

    /**
     * @return newNext
     */
    public MemPage setNext(MemPage newNext) {
        if (this.next != null)
            throw new IllegalStateException("next is already bound to " + this.next);
        if (newNext != null && newNext.prev != null)
            throw new IllegalStateException("newNext.prev is already bound to " + newNext.prev);
        this.next = newNext;
        if (newNext != null)
            newNext.prev = this;
        return newNext;
    }

    public MemPage detachNext() {
        MemPage next = this.next;
        if (next != null) {
            next.prev = null;
            this.next = null;
        }
        return next;
    }

    public void detach() {
        detachPrev();
        detachNext();
    }

    /**
     * @return <code>null</code> if offset out of range.
     */
    public PagePos which(int offset) {
        if (offset < 0)
            throw new OutOfDomainException("offset", offset, 0);
        MemPage page = this;
        while (page != null) {
            int limit = page.buffer.limit();
            if (offset <= limit)
                return new PagePos(page, offset);
            offset -= limit;
            page = page.next;
        }
        return null;
    }

}
