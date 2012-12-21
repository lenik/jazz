package net.bodz.bas.vfs.util;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.string.TreeLineChars;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.vfs.IFile;

public class FileTreeDumper {

    boolean treeGraph = true;
    TreeLineChars treeLineChars = TreeLineChars.smooth;
    boolean maxDepth;
    boolean showHidden;

    boolean showFullPath;
    boolean showSize;
    boolean appendSymbol = true;

    boolean colorized;

    public boolean isTreeGraph() {
        return treeGraph;
    }

    public void setTreeGraph(boolean treeGraph) {
        this.treeGraph = treeGraph;
    }

    public TreeLineChars getTreeLineChars() {
        return treeLineChars;
    }

    public void setTreeLineChars(TreeLineChars treeLineChars) {
        if (treeLineChars == null)
            throw new NullPointerException("treeLineChars");
        this.treeLineChars = treeLineChars;
    }

    public boolean isMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(boolean maxDepth) {
        this.maxDepth = maxDepth;
    }

    public boolean isShowHidden() {
        return showHidden;
    }

    public void setShowHidden(boolean showHidden) {
        this.showHidden = showHidden;
    }

    public boolean isShowFullPath() {
        return showFullPath;
    }

    public void setShowFullPath(boolean showFullPath) {
        this.showFullPath = showFullPath;
    }

    public boolean isShowSize() {
        return showSize;
    }

    public void setShowSize(boolean showSize) {
        this.showSize = showSize;
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

    public void dump(IPrintOut out, IFile file)
            throws IOException {
        dump(out, "", null, file, 0);
    }

    void dump(IPrintOut out, String prefix, Boolean theLast, IFile file, int depth)
            throws IOException {

        BasicFileAttributeView _view = file.getAttributeView(BasicFileAttributeView.class,
                LinkOption.NOFOLLOW_LINKS);
        BasicFileAttributes _attrs = _view.readAttributes();

        BasicFileAttributeView view = file.getAttributeView(BasicFileAttributeView.class);
        BasicFileAttributes attrs = view.readAttributes();

        if (showHidden || !file.isHidden()) {
            out.print(prefix);

            if (treeGraph && theLast != null)
                out.print(theLast ? treeLineChars.treeLastBranch : treeLineChars.treeBranch);

            if (showSize) {
                Long length = file.getLength();
                if (length != null)
                    out.printf("[%8d]", length);
            }

            out.print(getText(file));

            if (_attrs.isSymbolicLink()) {
                String targetSpec;
                try {
                    targetSpec = file.readSymLink();
                } catch (IOException e) {
                    targetSpec = "<error: " + e.getMessage() + ">";
                }
                out.print(" -> ");
                out.print(targetSpec);
            }

            if (appendSymbol) {
                if (attrs.isDirectory())
                    out.print("/");
                else if (file.isExecutable())
                    out.print("*");
                // else if (file.isPipe()) ...
            }

            out.println();
        }

        if (attrs.isDirectory()) {

            if (treeGraph && theLast != null)
                prefix += theLast ? treeLineChars.treeSkip : treeLineChars.treeLine;

            depth++;

            Iterable<? extends IFile> children = file.children();
            List<IFile> list = new ArrayList<IFile>();
            for (IFile child : children)
                if (showHidden || !child.isHidden())
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
