package net.bodz.bas.c.type;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.java.io.FileContent;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.c.loader.DefaultClassLoader;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.loader.scan.ClassScanner;
import net.bodz.bas.loader.scan.m2.TestClassLoader;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.snm.MavenProjectOrigin;

@IndexedType
public abstract class TypeCollector<T> {

    static Logger logger = LoggerFactory.getLogger(TypeCollector.class);

    protected final Class<T> baseClass;
    protected ClassScanner scanner = new ClassScanner();

    boolean showPaths;
    List<Class<?>> extensions;
    Map<File, List<String>> commitMap;

    public TypeCollector() {
        this(null);
    }

    public TypeCollector(Boolean global) {
        if (global == null)
            global = getClass().getName().contains(".uber.");

        URLClassLoader mainLoader = (URLClassLoader) DefaultClassLoader.getInstance();
        if (!global)
            mainLoader = restrictLoader(mainLoader, getClass());

        URLClassLoader testLoader = TestClassLoader.createMavenTestClassLoader(mainLoader);
        scanner.setClassLoader(testLoader);

        baseClass = TypeParam.infer1(getClass(), TypeCollector.class, 0);
        try {
            scanner.scan("net.bodz");
            scanner.scan("user");
        } catch (IOException e) {
            throw new Error(e.getMessage(), e);
        }
    }

    static URLClassLoader restrictLoader(URLClassLoader loader, Class<?> clazz) {
        String implClass = clazz.getName().replace('.', '/') + ".class";
        URL _url = loader.getResource(implClass);

        String _path = _url.toString();
        if (!_path.endsWith(implClass))
            throw new UnexpectedException("Unexpected URL:  " + _url);
        _path = _path.substring(0, _path.length() - implClass.length());
        if (_path.endsWith("/") || _path.endsWith("\\"))
            _path = _path.substring(0, _path.length() - 1);
        if (_path.endsWith("!"))
            _path = _path.substring(0, _path.length() - 1);

        URL folderUrl;
        try {
            folderUrl = new URL(_path);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }

        loader = new URLClassLoader(new URL[] { folderUrl });
        return loader;
    }

    public void collect()
            throws IOException {
        TypeCollector<T> wired = this;
        wired._collect();
    }

    synchronized void _collect()
            throws IOException {
        commitMap = new HashMap<File, List<String>>();
        extensions = new ArrayList<>();
        scan();
        commit(commitMap);
        if (showPaths)
            for (Class<?> extension : extensions) {
                MavenProjectOrigin pomDir = MavenProjectOrigin.fromClass(extension);
                File sourceFile = pomDir.getSourceFile(extension);
                System.out.println(sourceFile);
            }
        commitMap = null;
        extensions = null;
    }

    protected void scan() {
        List<Class<?>> serviceList = new ArrayList<Class<?>>(getExtensions(baseClass, true));
        // Collections.sort(serviceList, ClassNameComparator.getInstance());
        for (Class<?> serviceImpl : serviceList) {
            publish(baseClass, serviceImpl);
        }
    }

    /**
     * @return <code>false</code> if the serviceImpl is ignored. For example, it's not a service, or
     *         it's in a jar.
     */
    protected boolean publish(Class<?> baseClass, Class<?> extension) {
        logger.info("    Extension: " + extension);

        IndexedType indexing = extension.getAnnotation(IndexedType.class);
        if (indexing == null) {
            logger.debug("        (Not indexed-type, skipped)");
            return false;
        }

        extensions.add(extension);

        int mod = extension.getModifiers();
        if (Modifier.isAbstract(mod)) {
            if (indexing.includeAbstract())
                logger.debug("    (Included abstract class)");
            else
                return false;
        }

        MavenProjectOrigin pomDir = MavenProjectOrigin.fromClass(extension);
        File resdir = pomDir.getResourceDir(extension);
        if (resdir == null)
            return false;

        File sfile;

        sfile = new File(resdir, indexing.prefix() + baseClass.getName());

        List<String> lines = commitMap.get(sfile);
        if (lines == null) {
            lines = new ArrayList<String>();
            commitMap.put(sfile, lines);
        }

        lines.add(extension.getName());
        return true;
    }

    protected Collection<Class<?>> getExtensions(Class<?> base, boolean includeAbstract) {
        List<Class<?>> list = new ArrayList<Class<?>>();

        for (Class<?> clazz : scanner.getClosure(base)) {
            int mod = clazz.getModifiers();
            if (Modifier.isAbstract(mod))
                if (!includeAbstract)
                    continue;
            if (!Modifier.isPublic(mod))
                continue;
            // defined in code-block, or inner class with-in enclosing instance.
            if (clazz.isAnonymousClass() || clazz.isLocalClass() || clazz.isMemberClass())
                continue;
            if (clazz.isAnnotationPresent(ExcludedFromIndex.class))
                continue;
            list.add(clazz);
        }
        return list;
    }

    public boolean isShowPaths() {
        return showPaths;
    }

    public void setShowPaths(boolean showPaths) {
        this.showPaths = showPaths;
    }

    protected static void commit(Map<File, List<String>> files)
            throws IOException {
        for (Entry<File, List<String>> entry : files.entrySet()) {
            File file = entry.getKey();

            List<String> lines = entry.getValue();
            Collections.sort(lines);

            StringBuilder buf = new StringBuilder(lines.size() * 100);
            for (String line : new LinkedHashSet<String>(lines)) {
                buf.append(line);
                buf.append('\n');
            }
            String content = buf.toString();

            // The same?
            if (file.exists() == Boolean.TRUE) {
                String old = FileContent.readUtf8(file);
                if (content.equals(old))
                    continue;
            }

            logger.info("Update " + file);
            file.getParentFile().mkdirs();
            FileContent.createUtf8(file, content);
        }
    }

}