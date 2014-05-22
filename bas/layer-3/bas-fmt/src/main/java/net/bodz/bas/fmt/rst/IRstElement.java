package net.bodz.bas.fmt.rst;

import java.util.List;

public interface IRstElement {

    String getName();

    String[] getArguments();

    List<IRstAttribute> getAttributes();

    List<IRstElement> getChildren();

}
