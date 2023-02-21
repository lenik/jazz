package net.bodz.bas.program;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderDumper {

    protected int maxDepth = -1;

    public void dump(Appendable out, ClassLoader classLoader)
            throws IOException {
        dump(out, classLoader, "", false, 0);
    }

    public void dump(Appendable out, ClassLoader classLoader, String prefix)
            throws IOException {
        dump(out, classLoader, prefix, false, 0);
    }

    void dump(Appendable out, ClassLoader node, String prefix, boolean more, int depth)
            throws IOException {

        List<ClassLoader> children = null;
        int n = 0;
        if (depth < maxDepth || maxDepth == -1) {
            children = new ArrayList<ClassLoader>();
            ClassLoader parentAsChild = node.getParent();
            if (parentAsChild != null)
                children.add(parentAsChild);
            n = children.size();
        }

        {
            out.append(prefix);

            String prefix1 = prefix;
            if (depth != 0) {
                out.append(more ? " |- " : " `- ");
                prefix1 += more ? " |  " : "    ";
            }

            dumpEntry(out, node, prefix1, more || n != 0);

            out.append("\n");
        }

        if (n != 0) {
            depth++;

            if (depth != 0)
                prefix += more ? " |  " : "    ";

            for (int i = 0; i < n; i++) {
                ClassLoader child = children.get(i);
                dump(out, child, prefix, i < n - 1, depth);
            }
        }
    }

    void dumpEntry(Appendable out, ClassLoader classLoader, String prefix, boolean more)
            throws IOException {
        String name = classLoader.getClass().getSimpleName() + "@" + System.identityHashCode(classLoader);

        out.append(name);

        prefix += "    ";
        out.append("\n" + prefix + (more ? " |- " : " `- ") //
                + "(info)");
        prefix += more ? " |  " : "    ";

        URL[] urls = {};
        if (classLoader instanceof URLClassLoader)
            urls = ((URLClassLoader) classLoader).getURLs();

        for (int i = 0; i < urls.length; i++) {
            boolean _more = i < urls.length - 1;
            out.append("\n" + prefix + (_more ? " |- " : " `- ") //
                    + "URL: " + urls[i]);
        }
    }

}
