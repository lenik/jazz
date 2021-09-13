package net.bodz.bas.c.string;

import java.io.IOException;

import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;

public interface ITextSerializable {

    void readObject(ICharIn in)
            throws IOException;

    void writeObject(ICharOut out)
            throws IOException;

}
