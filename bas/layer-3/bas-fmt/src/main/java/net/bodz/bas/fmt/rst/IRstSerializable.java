package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IRstSerializable {

    default void writeObject(IRstOutput out)
            throws IOException {
        RstFn.defaultDump(this, out);
    }

    default IRstHandler getElementHandler() {
        return RstFn.getDefaultHandler(this);
    }

    class rst
            extends RstFn {
    }

}
