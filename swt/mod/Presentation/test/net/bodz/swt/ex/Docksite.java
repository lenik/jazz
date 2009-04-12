package net.bodz.swt.ex;

import org.eclipse.swt.widgets.Composite;

public class Docksite extends Composite {

    public static class Dock extends Composite {
        public Dock(Composite parent, int style) {
            super(parent, style);
        }
    }

    // north, south, west, east, center ?
    Dock[] docks = new Dock[5];

    public Docksite(Composite parent, int style) {
        super(parent, style);
    }

}
