package net.bodz.swt.c3.pageflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.i18n.nls.CombinedNLS;
import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.t.tree.TreePath;

public abstract class AbstractBook
        implements IBook {

    private final String title;
    protected final IBook next;

    public AbstractBook(String title, IBook next) {
        if (title == null)
            title = getClass().getName();
        this.title = title;
        this.next = next;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public final boolean contains(TreePath path) {
        if (_contains(path))
            return true;
        if (next != null)
            return next.contains(path);
        return false;
    }

    @Override
    public IPage getPage(TreePath path) {
        IPage page = _getPage(path);
        if (page != null)
            return page;
        if (next != null)
            return next.getPage(path);
        return null;
    }

    @Override
    public TreePath getFirst() {
        TreePath first = _getFirst();
        if (first != null)
            return first;
        if (next != null)
            return next.getFirst();
        return null;
    }

    @Override
    public final Collection<PageMethod> getMethods() {
        Collection<PageMethod> m1 = _getMethods();
        if (next == null)
            return m1;
        Collection<PageMethod> m2 = next.getMethods();
        if (m1 == null || m1.isEmpty())
            return m2;
        if (m2 == null || m2.isEmpty())
            return m1;
        if (m1 == m2)
            return m1;
        List<PageMethod> all = new ArrayList<PageMethod>();
        all.addAll(m2);
        all.addAll(m1);
        return all;
    }

    @Override
    public NLS getDict() {
        NLS d1 = _getDict();
        if (next == null)
            return d1;
        NLS d2 = next.getDict();
        if (d1 == null)
            return d2;
        if (d2 == null)
            return d1;
        if (d1 == d2)
            return d1;
        CombinedNLS all = new CombinedNLS(d1, d2);
        all.setName(d1.getName());
        return all;
    }

    protected abstract IPage _getPage(TreePath path);

    protected boolean _contains(TreePath path) {
        IPage page = _getPage(path);
        return page != null;
    }

    protected TreePath _getFirst() {
        return null;
    }

    protected Collection<PageMethod> _getMethods() {
        return null;
    }

    protected NLS _getDict() {
        return null;
    }

}
