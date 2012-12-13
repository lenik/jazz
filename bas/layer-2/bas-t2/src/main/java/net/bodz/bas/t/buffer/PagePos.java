package net.bodz.bas.t.buffer;

public class PagePos {

    public MemPage page;
    public int offset;

    public PagePos(MemPage page, int offset) {
        if (page == null)
            throw new NullPointerException("block");
        this.page = page;
        this.offset = offset;
    }

    public int getPageRemaining() {
        return page.buffer.limit() - offset;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PagePos) {
            PagePos p = (PagePos) obj;
            if (page != p.page)
                return false;
            if (offset != p.offset)
                return false;
            return true;
        }
        return false;
    }

    public boolean isBefore(PagePos p) {
        return isBefore(p, true);
    }

    public boolean isBefore(PagePos p, boolean equals) {
        if (p == null)
            return true;
        if (page == p.page) {
            if (offset < p.offset)
                return true;
            if (offset == p.offset)
                return equals;
            return false;
        }
        MemPage nexts = page.getNext();
        while (nexts != null) {
            if (nexts == p.page)
                return true;
            nexts = nexts.getNext();
        }
        return false;
    }

    public boolean isAfter(PagePos p) {
        return isAfter(p, false);
    }

    public boolean isAfter(PagePos p, boolean equals) {
        if (p == null)
            return true;
        if (page == p.page) {
            if (offset > p.offset)
                return true;
            if (offset == p.offset)
                return equals;
            return false;
        }
        MemPage prevs = page.getPrev();
        while (prevs != null) {
            if (prevs == p.page)
                return true;
            prevs = prevs.getPrev();
        }
        return false;
    }

}
