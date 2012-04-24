package net.bodz.bas.c.object;

import java.util.Set;

import net.bodz.bas.sio.ITreeOut;

public class DefaultDumpTreeProcess
        extends AbstractDumpTreeProcess<Object> {

    public DefaultDumpTreeProcess() {
        super();
    }

    public DefaultDumpTreeProcess(ITreeOut out, Set<Object> occurred) {
        super(out, occurred);
    }

    @Override
    protected void printId(DumpTreeFormat format, Object val) {
        if (!format.isShowIdentity())
            return;
        String typeName = getClass().getSimpleName();
        int id = System.identityHashCode(this);
        String idHex = Integer.toHexString(id);
        out.print(typeName);
        out.print("@");
        out.print(idHex);
    }

}
