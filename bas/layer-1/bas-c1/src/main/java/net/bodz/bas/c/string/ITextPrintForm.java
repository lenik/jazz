package net.bodz.bas.c.string;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;

public interface ITextPrintForm {

    void printObject(ITreeOut out)
            throws IOException;

    default String printObject() {
        BCharOut buf = new BCharOut();
        try {
            printObject(buf.indented());
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

}
