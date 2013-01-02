package net.bodz.bas.t.tree;

import java.io.IOException;

import net.bodz.bas.c.string.TreeLineChars;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintOut;

public abstract class TreeFormatterTemplate<T> // implements IFormatter
{

    protected boolean drawTreeLines = true;
    protected TreeLineChars treeLineChars = TreeLineChars.smooth;
    protected int maxDepth = -1;

    public boolean isDrawTreeLines() {
        return drawTreeLines;
    }

    public void setDrawTreeLines(boolean drawTreeLines) {
        this.drawTreeLines = drawTreeLines;
    }

    public TreeLineChars getTreeLineChars() {
        return treeLineChars;
    }

    public void setTreeLineChars(TreeLineChars treeLineChars) {
        if (treeLineChars == null)
            throw new NullPointerException("treeLineChars");
        this.treeLineChars = treeLineChars;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String format(T node) {
        BCharOut buf = new BCharOut();
        try {
            format(buf, node);
        } catch (IOException e) {
            assert false;
        }
        return buf.toString();
    }

    public abstract void format(IPrintOut out, T node)
            throws IOException;

}
