package net.bodz.bas.fmt.rst;

import java.util.List;

import net.bodz.bas.fmt.api.ITextAttribute;

public interface IRstElement {

    String getName();

    String[] getArguments();

    List<ITextAttribute> getAttributes();

    List<IRstElement> getChildren();

}
