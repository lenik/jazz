package net.bodz.bas.c.object;

import net.bodz.bas.sio.ITreeOut;
import net.bodz.bas.sio.PrintException;

public interface ITreeDump {

    void dump(ITreeOut out, ITreeDumpContext context)
            throws PrintException;

}
