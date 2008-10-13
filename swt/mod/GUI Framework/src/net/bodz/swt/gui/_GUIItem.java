package net.bodz.swt.gui;

import net.bodz.bas.lang.err.CheckException;

public abstract class _GUIItem implements GUIItem {

    protected String  name;
    private int       order;
    protected GUIHint hint;

    _GUIItem(String name, GUIHint hint, int order) {
        this.name = name;
        this.hint = hint;
        this.order = order;
    }

    public _GUIItem(String name, GUIHint hint) {
        this(name, hint, hint.order);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(GUIItem o) {
        assert o != null;
        int c = getOrder() - o.getOrder();
        if (c != 0)
            return c;
        return getName().compareTo(o.getName());
    }

    @Override
    public GUIHint getHint() {
        return hint;
    }

    @Override
    public void check(Object obj, Object value) throws CheckException,
            GUIAccessException {
    }

    /**
     * do nothing in default implementation.
     */
    @Override
    public void execute(Object obj, Object... args) throws GUIAccessException {
    }

}
