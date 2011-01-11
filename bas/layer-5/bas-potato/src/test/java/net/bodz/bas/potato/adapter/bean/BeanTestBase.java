package net.bodz.bas.potato.adapter.bean;

import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;
import net.bodz.bas.meta.info.Doc;

public class BeanTestBase
        extends TestCase {

    public static class AddressBean {

        private String country;
        private String city;
        private String address;

        @Doc("Country in the world")
        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Doc("City in a country")
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Doc("Address in the city")
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }

    public static class BookBean
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

    public static class PageBean {

        int pageNo;
        String contents;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

    }

}
