package net.bodz.bas.vfs.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.vfs.IFile;

public class FileTreeDumper {

    boolean treeGraph = true;
    boolean maxDepth;
    boolean appendSymbol;
    boolean colorized;

    public boolean isTreeGraph() {
        return treeGraph;
    }

    public void setTreeGraph(boolean treeGraph) {
        this.treeGraph = treeGraph;
    }

    public boolean isMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(boolean maxDepth) {
        this.maxDepth = maxDepth;
    }

    /**
     * Append symbol to indicate the file type.
     * <ul>
     * <li>'<code>/</code>' / for dirs
     * <li>'<code>*</code>' for executables.
     * </ul>
     */
    public boolean isAppendSymbol() {
        return appendSymbol;
    }

    public void setAppendSymbol(boolean appendSymbol) {
        this.appendSymbol = appendSymbol;
    }

    public boolean isColorized() {
        return colorized;
    }

    public void setColorized(boolean colorized) {
        this.colorized = colorized;
    }

    public void dump(IPrintOut out, IFile file) {
        dump(out, "", null, file, 0);
    }

    void dump(IPrintOut out, String prefix, Boolean theLast, IFile file, int depth) {
        out.print(prefix);
        if (theLast != null)
            out.print(theLast ? " `- " : " |- ");
        out.print(getText(file));
        out.println();

        if (file.isTree()) {

            if (theLast != null)
                prefix += theLast ? "    " : " |  ";
            depth++;

            Iterable<? extends IFile> children = file.children();
            List<IFile> list = new ArrayList<IFile>();
            for (IFile child : children)
                list.add(child);

            int size = list.size();
            for (int i = 0; i < size; i++) {
                IFile child = list.get(i);
                boolean lastChild = i == size - 1;
                dump(out, prefix, lastChild, child, depth);
            }
        }
    }

    String getText(IFile file) {
        if (treeGraph)
            return file.getName();
        else
            return file.getPath().toString();
    }

}
