package net.bodz.bas.doc.node;

import net.bodz.bas.doc.node.util.Css3ListStyle;
import net.bodz.bas.doc.node.util.IListStyle;
import net.bodz.bas.ui.css3.property.ListStyleTypeMode;

public class ListStyles {

    public static final IListStyle none = new Css3ListStyle(ListStyleTypeMode.none);
    public static final IListStyle decimal = new Css3ListStyle(ListStyleTypeMode.decimal);
    public static final IListStyle disc = new Css3ListStyle(ListStyleTypeMode.disc);
    public static final IListStyle square = new Css3ListStyle(ListStyleTypeMode.square);
    public static final IListStyle circle = new Css3ListStyle(ListStyleTypeMode.circle);

}
