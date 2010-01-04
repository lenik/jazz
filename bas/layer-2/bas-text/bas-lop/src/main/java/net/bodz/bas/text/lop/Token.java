package net.bodz.bas.text.lop;

import net.bodz.bas.text.lop.util.XYTellable;

public interface Token extends XYTellable {

    int getId();

    String getName();

    String getText();

    int length();

    char charAt(int index);

    Object getValue();

    XYTellable end();

}
