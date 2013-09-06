package net.bodz.bas.text.lop;

import net.bodz.bas.io.IXYTellable;

public interface Token
        extends IXYTellable {

    int getId();

    String getName();

    String getText();

    int length();

    char charAt(int index);

    Object getValue();

    IXYTellable end();

}
