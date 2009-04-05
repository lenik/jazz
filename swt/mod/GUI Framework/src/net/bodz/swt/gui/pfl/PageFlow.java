package net.bodz.swt.gui.pfl;

import java.util.Collection;
import java.util.NoSuchElementException;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import net.bodz.swt.gui.ValidateException;

public class PageFlow extends Location {

    /**
     * unix-style path -> page object
     */
    private TextMap<Page> pages;

    public PageFlow() {
        this.pages = new TreeTextMap<Page>();
    }

    public Collection<Page> getPages() {
        return pages.values();
    }

    @Override
    public boolean has(String next) {
        String address = resolv(next);
        if (pages.containsKey(address))
            return true;
        return isPageLoadable(address);
    }

    protected boolean isPageLoaded(String address) {
        return pages.containsKey(address);
    }

    public boolean isPageLoadable(String address) {
        return false;
    }

    /**
     * Only called if {@link #isPageLoadable(String)} returns <code>true</code>
     * on the specified address
     * 
     * @return <code>null</code> in default implementation.
     */
    protected Page loadPage(String address) {
        return null;
    }

    public Page getPage() {
        String address = get();
        return getPage(address);
    }

    public Page getPage(String address) {
        if (address == null)
            return null;
        Page page = pages.get(address);
        if (page == null && isPageLoadable(address)) {
            page = loadPage(address);
            pages.put(address, page);
        }
        return page;
    }

    public boolean canGoBack() {
        return has(-1);
    }

    public void goBack() {
        if (!has(-1))
            throw new NoSuchElementException("previous");
        go(-1);
    }

    protected String getPageNext(Page page) {
        if (page == null)
            return null;
        Object exit = page.exitState();
        if (exit == null)
            return null;
        return String.valueOf(exit);
    }

    public boolean canGoOn() {
        Page page = getPage();
        if (page == null)
            return false;
        String next = getPageNext(page);
        return has(next);
    }

    public void goOn() throws ValidateException {
        Page page = getPage();
        if (page == null)
            throw new IllegalStateException("no current page");
        page.validate();
        String next = getPageNext(page);
        set(next);
    }

    @Override
    protected void locationChange(String prev, String next) {
        Page prevPage = getPage(prev);
        if (prevPage != null)
            prevPage.leave(next);

        Page nextPage = getPage(next);
        if (nextPage != null)
            nextPage.enter(prev);

        super.locationChange(prev, next);
    }

}
