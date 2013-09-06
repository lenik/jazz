package net.bodz.bas.c.object;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.PrintException;

public interface ITreeDump {

    void dump(ITreeOut out, ITreeDumpContext context)
            throws PrintException;

}
