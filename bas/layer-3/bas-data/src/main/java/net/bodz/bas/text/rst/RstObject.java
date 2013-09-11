package net.bodz.bas.text.rst;

import java.io.IOException;
import java.lang.reflect.Field;

public abstract class RstObject
        extends ReflectElementHandler
        implements IRstSerializable, IRstFieldOverride {

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        ReflectRstDumper.getInstance().dump(out, this);
    }

    @Override
    public boolean writeObjectFieldOverride(IRstOutput out, Field field)
            throws IOException {
        return false;
    }

    @Override
    public IElementHandler getElementHandler() {
        return this;
    }

}
