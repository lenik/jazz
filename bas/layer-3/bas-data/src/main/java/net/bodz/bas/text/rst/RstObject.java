package net.bodz.bas.text.rst;

import java.io.IOException;

public abstract class RstObject
        extends ReflectElementHandler
        implements IRstSerializable {

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        ReflectRstDumper.dump(out, this);
    }

    @Override
    public IElementHandler getElementHandler() {
        return this;
    }

}
