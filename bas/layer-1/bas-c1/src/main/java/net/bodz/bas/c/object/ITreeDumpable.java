package net.bodz.bas.c.object;

import net.bodz.bas.sio.ITreeOut;
import net.bodz.bas.sio.PrintException;

public interface ITreeDumpable {

    void dump(ITreeOut out, IDumpTreeContext context)
            throws PrintException;

}
