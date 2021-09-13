package net.bodz.bas.c.string;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ICharOut;

public interface ITextForm {

    void writeObject(ICharOut out)
            throws IOException;

    default String toText() {
        BCharOut buf = new BCharOut();
        try {
            writeObject(buf);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

}
