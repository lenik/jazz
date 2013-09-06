package net.bodz.bas.c.loader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.tree.AbstractTreeNodeFormatter;

public class ClassLoaderTreeFormatter
        extends AbstractTreeNodeFormatter<ClassLoaderNode> {

    @Override
    protected void formatEntry(IPrintOut out, ClassLoaderNode node, String prefix, boolean more)
            throws IOException {
        ClassLoader classLoader = node.getClassLoader();
        String name = ObjectInfo.getSimpleId(classLoader);
        out.print(name);

        prefix += treeLineChars.treeSkip;
        out.print("\n" + prefix + (more ? treeLineChars.treeBranch : treeLineChars.treeLastBranch) //
                + "(info)");
        prefix += more ? treeLineChars.treeLine : treeLineChars.treeSkip;

        URL[] urls = {};
        if (classLoader instanceof URLClassLoader)
            urls = ((URLClassLoader) classLoader).getURLs();

        String[] tags = node.getTags().toArray(new String[0]);
        for (int i = 0; i < tags.length; i++) {
            boolean _more = urls.length > 0 || i < tags.length - 1;
            out.print("\n" + prefix + (_more ? treeLineChars.treeBranch : treeLineChars.treeLastBranch) //
                    + "Tag: " + tags[i]);
        }

        for (int i = 0; i < urls.length; i++) {
            boolean _more = i < urls.length - 1;
            out.print("\n" + prefix + (_more ? treeLineChars.treeBranch : treeLineChars.treeLastBranch) //
                    + "URL: " + urls[i]);
        }
    }

}
