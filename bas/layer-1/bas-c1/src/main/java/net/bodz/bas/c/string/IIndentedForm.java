package net.bodz.bas.c.string;

import java.io.IOException;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public interface IIndentedForm
        extends
            ITextForm {

    void writeObject(ITreeOut out)
            throws IOException;

    @Override
    default void writeObject(ICharOut out)
            throws IOException {
        ITreeOut treeOut = TreeOutImpl.from(out);
        writeObject(treeOut);
    }

}
