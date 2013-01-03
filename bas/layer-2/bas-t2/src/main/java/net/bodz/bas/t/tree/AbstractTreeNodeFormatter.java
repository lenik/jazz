package net.bodz.bas.t.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.sio.IPrintOut;

public class AbstractTreeNodeFormatter<node_t extends ITreeNode>
        extends TreeFormatterTemplate<node_t> {

    @Override
    public final void format(IPrintOut out, node_t node)
            throws IOException {
        format(out, node, linePrefix, false, 0);
    }

    void format(IPrintOut out, node_t node, String prefix, boolean more, int depth)
            throws IOException {

        List<node_t> visibleChildren = null;
        int n = 0;
        if (depth < maxDepth || maxDepth == -1) {
            visibleChildren = new ArrayList<>();
            for (ITreeNode _child : node.children()) {
                @SuppressWarnings("unchecked") node_t child = (node_t) _child;
                if (isVisible(child))
                    visibleChildren.add(child);
            }

            n = visibleChildren.size();
        }

        {
            out.print(prefix);

            String prefix1 = prefix;
            if (drawTreeLines && depth != 0) {
                out.print(more ? treeLineChars.treeBranch : treeLineChars.treeLastBranch);
                prefix1 += more ? treeLineChars.treeLine : treeLineChars.treeSkip;
            }

            formatEntry(out, node, prefix1, more || n != 0);

            out.println();
        }

        if (n != 0) {
            depth++;

            if (drawTreeLines && depth != 0)
                prefix += more ? treeLineChars.treeLine : treeLineChars.treeSkip;

            for (int i = 0; i < n; i++) {
                node_t child = visibleChildren.get(i);
                format(out, child, prefix, i < n - 1, depth);
            }
        }
    }

    protected void formatEntry(IPrintOut out, node_t node, String prefix, boolean more)
            throws IOException {
        String text = node.toString();
        out.print(text);
    }

    protected boolean isVisible(node_t node) {
        return true;
    }

}
