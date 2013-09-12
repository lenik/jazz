package net.bodz.bas.text.rst;

import java.io.IOException;
import java.lang.reflect.Field;

import net.bodz.bas.c.object.IEmptyConsts;

public abstract class RstObject
        extends ReflectElementHandler
        implements IRstSerializable, IReflectRstOverrides {

    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        ReflectRstDumper.getInstance().dump(out, this);
    }

    @Override
    public IElementHandler getElementHandler() {
        return this;
    }

    @Override
    public String[] getElementArguments() {
        return IEmptyConsts.emptyStringArray;
    }

    @Override
    public boolean writeObjectFieldOverride(IRstOutput out, Field field)
            throws IOException {
        return false;
    }

}
