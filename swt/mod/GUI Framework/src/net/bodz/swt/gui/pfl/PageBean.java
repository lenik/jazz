package net.bodz.swt.gui.pfl;

import net.bodz.bas.lang.err.CheckException;

public abstract class PageBean {

    public void enter(PageBean previous) {
    }

    /**
     * @return information used to determine the next page. <code>null</code>
     *         value is allowed which means <i>doesn't matter</i>.
     */
    public String leave() {
        return null;
    }

    /**
     * @throws CheckException
     *             if
     */
    public void check() throws CheckException {
    }

}
