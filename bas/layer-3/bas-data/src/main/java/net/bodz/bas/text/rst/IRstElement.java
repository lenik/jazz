package net.bodz.bas.text.rst;

import java.util.List;

public interface IRstElement {

    String getName();

    String[] getArguments();

    List<IRstAttribute> getAttributes();

    List<IRstElement> getChildren();

}
