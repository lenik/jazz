package net.bodz.bas.potato.adapter.bean.user;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BookBean
        implements Iterable<PageBean> {

    private PageBean[] pages;

    public PageBean[] getPages() {
        return pages;
    }

    public void setPages(PageBean[] pages) {
        this.pages = pages;
    }

    /**
     * Read-only.
     */
    public PageBean getPage(int pageIndex) {
        return pages[pageIndex];
    }

    class PageIterator
            implements Iterator<PageBean> {

        int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return nextIndex < pages.length;
        }

        @Override
        public PageBean next() {
            if (nextIndex >= pages.length)
                throw new NoSuchElementException();
            return pages[nextIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public Iterator<PageBean> iterator() {
        return new PageIterator();
    }

}
