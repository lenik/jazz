package net.bodz.bas.vfs.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.tree.TreeFormatterTemplate;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileAttributes;

public class FileTreeFormatter
        extends TreeFormatterTemplate<IFile> {

    boolean showHidden;

    boolean showFullPath;
    boolean showSize;
    boolean appendSymbol = true;

    boolean colorized;

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

    @Override
    public void format(IPrintOut out, IFile file)
            throws IOException {
        format(out, "", null, file, 0);
    }

    void format(IPrintOut out, String prefix, Boolean theLast, IFile file, int depth)
            throws IOException {

        IFileAttributes attrs = file.getAttributes();
        if (attrs.isHidden() && !showHidden)
            return;

        IFile target = file;
        IFileAttributes targetAttrs = attrs;
        String targetSpec = null;

        if (attrs.isSymbolicLink()) {
            try {
                targetSpec = file.readSymbolicLink();
                target = file.resolve(targetSpec);
                if (target != null)
                    targetAttrs = target.getAttributes();
            } catch (IOException e) {
                targetSpec = "<error: " + e.getMessage() + ">";
            }
        }

        /* blob */{
            out.print(prefix);

            if (drawTreeLines && theLast != null)
                out.print(theLast ? treeLineChars.treeLastBranch : treeLineChars.treeBranch);

            if (showSize) {
                Long length = target.getLength();
                if (length != null)
                    out.printf("[%8d]", length);
            }

            out.print(getFileLabel(file));

            if (attrs.isSymbolicLink()) {
                out.print(" -> ");
                out.print(targetSpec);
            }

            if (appendSymbol) {
                if (targetAttrs.isDirectory())
                    out.print("/");
                else if (attrs.isExecutable())
                    out.print("*");
                // else if (file.isPipe()) ...
            }

            out.println();
        }

        if (targetAttrs.isDirectory()) {

            if (drawTreeLines && theLast != null)
                prefix += theLast ? treeLineChars.treeSkip : treeLineChars.treeLine;

            depth++;

            Iterable<? extends IFile> children = target.children();
            List<IFile> list = new ArrayList<IFile>();
            for (IFile child : children)
                if (showHidden || !child.getAttributes().isHidden())
                    list.add(child);

            int size = list.size();
            for (int i = 0; i < size; i++) {
                IFile child = list.get(i);
                boolean lastChild = i == size - 1;
                format(out, prefix, lastChild, child, depth);
            }
        }
    }

    String getFileLabel(IFile file) {
        if (drawTreeLines)
            return file.getName();
        else
            return file.getPath().toString();
    }

}
