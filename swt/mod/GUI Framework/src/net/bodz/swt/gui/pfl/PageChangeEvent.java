package net.bodz.swt.gui.pfl;

import java.util.EventObject;

public class PageChangeEvent extends EventObject {

    private static final long serialVersionUID = -807822550463632363L;

    public String             previous;
    public String             current;

    public PageChangeEvent(Object source, String previous, String current) {
        super(source);
        this.previous = previous;
        this.current = current;
    }

}
