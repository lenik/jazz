package net.bodz.bas.c.object;

import java.util.Set;

import net.bodz.bas.io.ITreeOut;

public class DefaultTreeDumping
        extends AbstractTreeDumping<Object> {

    public DefaultTreeDumping() {
        super();
    }

    public DefaultTreeDumping(ITreeOut out, Set<Object> occurred) {
        super(out, occurred);
    }

    @Override
    protected void printId(TreeDumpFormat format, Object val) {
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
