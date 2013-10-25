package net.bodz.pkg.sis.util;

import java.io.IOException;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.tree.AbstractTreeNodeFormatter;
import net.bodz.pkg.sis.ISisComponent;

public class SisDumper
        extends AbstractTreeNodeFormatter<ISisComponent> {

    private boolean showHidden = true;

    public boolean isShowHidden() {
        return showHidden;
    }

    public void setShowHidden(boolean showHidden) {
        this.showHidden = showHidden;
    }

    @Override
    protected void formatEntry(IPrintOut out, ISisComponent node, String prefix, boolean more)
            throws IOException {
        if (!showHidden && !node.isVisible())
            return;

        out.print(node.getId());
    }

    public static String dump(ISisComponent root) {
        BCharOut buf = new BCharOut(4096);
        try {
            dump(buf, root);
        } catch (IOException e) {
            assert false;
        }
        return buf.toString();
    }

    public static void dump(IPrintOut out, ISisComponent root)
            throws IOException {
        SisDumper dumper = new SisDumper();
        dumper.format(out, root);
    }

}
