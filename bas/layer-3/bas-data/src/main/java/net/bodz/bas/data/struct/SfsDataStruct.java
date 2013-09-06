package net.bodz.bas.data.struct;

import java.io.IOException;

import net.bodz.bas.text.structf.IStructfElement;
import net.bodz.bas.text.structf.IStructfHandler;
import net.bodz.bas.text.structf.IStructfOutput;
import net.bodz.bas.text.structf.StructfElement;
import net.bodz.bas.text.structf.StructfHandlerException;

public abstract class SfsDataStruct
        extends DataStruct
        implements ISfsDataStruct {

    private static final long serialVersionUID = 1L;

    @Override
    public void writeObject(IStructfOutput out)
            throws IOException {
    }

    @Override
    public boolean attribute(String name, String data)
            throws StructfHandlerException {
        return false;
    }

    @Override
    public IStructfHandler beginChild(String name, String[] args)
            throws StructfHandlerException {
        return new StructfElement(name, args);
    }

    @Override
    public boolean endChild(IStructfElement element)
            throws StructfHandlerException {
        return false;
    }

    @Override
    public void element(IStructfElement element)
            throws StructfHandlerException {
    }

}
