package net.bodz.bas.program;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
        String name = classLoader.getClass().getName() + "@" + System.identityHashCode(classLoader);

        out.append(name);

        prefix += "    ";
        out.append("\n" + prefix + (more ? " |- " : " `- ") //
                + "(info)");
        prefix += more ? " |  " : "    ";

        URL[] urls = {};

        Class<?> clazz = classLoader.getClass();
        switch (clazz.getName()) {
            case "jdk.internal.loader.ClassLoaders$AppClassLoader":
                clazz = clazz.getSuperclass();
            case "jdk.internal.loader.BuiltinClassLoader":
                try {
                    Field ucpField = clazz.getDeclaredField("ucp");
                    ucpField.setAccessible(true);
                    Object urlClassPath = ucpField.get(classLoader);

                    Class<?> urlClassPathClass = urlClassPath.getClass();
                    Method getURLs = urlClassPathClass.getMethod("getURLs");
                    urls = (URL[]) getURLs.invoke(urlClassPath);
                    break;
                } catch (ReflectiveOperationException e) {
                    // not supported. just ignore, try default method.
                }

            default:
                if (classLoader instanceof URLClassLoader) {
                    urls = ((URLClassLoader) classLoader).getURLs();
                }
        }

        for (int i = 0; i < urls.length; i++) {
            boolean _more = i < urls.length - 1;
            out.append("\n") //
                    .append(prefix).append(_more ? " |- " : " `- ").append("URL: ") //
                    .append(String.valueOf(urls[i]));
        }
    }

}
