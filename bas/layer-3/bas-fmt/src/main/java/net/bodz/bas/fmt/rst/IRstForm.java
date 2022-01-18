package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IRstForm {

    default void writeObject(IRstOutput out)
            throws IOException, FormatException {
        RstFn.defaultDump(this, out);
    }

    default IRstHandler getElementHandler() {
        return RstFn.getDefaultHandler(this);
    }

    class rst
            extends RstFn {
    }

}
