package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IRstSerializable {

    void writeObject(IRstOutput out)
            throws IOException;

    IElementHandler getElementHandler();

    class rst
            extends RstFn {
    }

}
