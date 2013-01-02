package net.bodz.bas.c.loader;

import java.io.IOException;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.t.tree.AbstractTreeNodeFormatter;

public class ClassLoaderTreeFormatter
        extends AbstractTreeNodeFormatter<ClassLoaderNode> {

    @Override
    protected void formatEntry(IPrintOut out, ClassLoaderNode node, String prefix, boolean more)
            throws IOException {
        ClassLoader classLoader = node.getClassLoader();
        String name = ObjectInfo.getSimpleId(classLoader);
        out.print(name);

        if (!node.getTags().isEmpty()) {
            prefix += more ? treeLineChars.treeLine : treeLineChars.treeSkip;
            String tags = StringArray.join(", ", node.getTags());
            out.print("\n" + prefix + "<-- " + tags);
        }
    }

}
