package net.bodz.bas.text.rst;

import java.io.IOException;

public interface IRstSerializable {

    void writeObject(IRstOutput out)
            throws IOException;

    IElementHandler getElementHandler();

}
