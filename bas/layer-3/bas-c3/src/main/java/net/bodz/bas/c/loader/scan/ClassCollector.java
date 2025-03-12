package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenTestClassLoader;
import net.bodz.bas.c.type.CachedInstantiator;
import net.bodz.bas.io.res.FileCacheMap;
import net.bodz.bas.io.res.SaveOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IEtcFilesEditor;
import net.bodz.bas.meta.codegen.IEtcFilesInstaller;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.pojo.Pair;

public class ClassCollector {

    static Logger logger = LoggerFactory.getLogger(ClassCollector.class);

    DefaultScanOptions scanOptions;

    List<String> includeFqcnPrefixes = new ArrayList<String>();
    List<String> excludeFqcnPrefixes = new ArrayList<String>();

    boolean scanned;

    boolean showPaths;
    boolean deleteEmptyFiles = true;

    public ClassCollector() {
        scanOptions = new DefaultScanOptions();
    }

    public boolean isShowPaths() {
        return showPaths;
    }

    public void setShowPaths(boolean showPaths) {
        this.showPaths = showPaths;
    }

    public void includeDirToScan(Path dir) {
        scanOptions.includePath(dir.toAbsolutePath().normalize().toString());
    }

    public void includeDirToScan(File dir) {
        scanOptions.includePath(dir.getAbsolutePath());
    }

    public void excludeDirToScan(Path dir) {
        scanOptions.excludePath(dir.toAbsolutePath().normalize().toString());
    }

    public void excludeDirToScan(File dir) {
        scanOptions.excludePath(dir.getAbsolutePath());
    }

    public void includePackageToScan(String packageName) {
        includeFqcnPrefixes.add(packageName);
    }

    public void excludePackageToScan(String packageName) {
        excludeFqcnPrefixes.remove(packageName);
    }

    protected ClassFileMap filter(Collection<Class<?>> classes, Class<?> baseClass) {
        ClassFileMap selection = new ClassFileMap();
        for (Class<?> clazz : classes) {
            ExcludeReason reason;
            do {
                reason = IndexedTypeUtil.check(clazz, baseClass);
                if (reason.isExcluded())
                    break;

                Pair<ExcludeReason, Path> reasonAndFile;
                reason = (reasonAndFile = IndexedTypeUtil.checkDir(clazz)).first;
                if (reason.isExcluded())
                    break;

                try {
                    reason = IndexedTypeUtil.checkScan(clazz, scanOptions);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                if (reason.isExcluded())
                    break;

                selection.put(clazz, reasonAndFile.second);
            } while (false);

            if (reason.isExcluded()) {
                String type = baseClass.isAnnotation() ? "annotated" : "derived";
                logger.infof("    Excluded %s %s: %s", type, clazz, reason);
            }
        }
        return selection;

    }

    protected FileCacheMap<Path> publishEtcFiles(ClassFileMap filterMap, Class<?> baseClass) {
        IndexedType aIndexedType = baseClass.getAnnotation(IndexedType.class);
        if (aIndexedType == null)
            throw new NullPointerException("aIndexedType");

        String publishDir = aIndexedType.publishDir();
        if (publishDir.isEmpty())
            publishDir = null;
        else if (!publishDir.endsWith("/"))
            publishDir += "/";

        FileCacheMap<Path> fileContentMap = FileCacheMap.createPathIndexed();
        for (Class<?> derivedClass : filterMap.keySet()) {
            Path resDir = filterMap.get(derivedClass);

            if (publishDir != null) {
                Path listFile = resDir.resolve(publishDir + baseClass.getName());
                boolean init = !fileContentMap.containsFile(listFile);
                List<String> lines;
                try {
                    lines = fileContentMap.loadLinesCached(listFile);
                } catch (IOException e) {
                    logger.error(e, "Error loading " + listFile + ", skipped.");
                    continue;
                }

                if (init) // Refresh: remove included packages before re-add.
                    beforeRefresh(lines);

                if (aIndexedType.obsoleted()) {
                    // lines.add("# " + extension.getName());
                } else {
                    lines.add(derivedClass.getName());
                }
            }

            Class<? extends IEtcFilesInstaller> etcFilesClass = aIndexedType.etcFiles();
            if (etcFilesClass != null && !Modifier.isAbstract(etcFilesClass.getModifiers())) {
                IEtcFilesInstaller etcFiles = CachedInstantiator.getInstance().instantiate(etcFilesClass);

                IEtcFilesEditor editor = new IEtcFilesEditor() {
                    @Override
                    public void clear(String path)
                            throws IOException {
                        Path file = FilePath.joinHref(resDir, path);
                        List<String> lines = fileContentMap.loadLinesCached(file);
                        lines.clear();
                    }

                    @Override
                    public void addLine(String path, String s)
                            throws IOException {
                        Path file = FilePath.joinHref(resDir, path);
                        List<String> lines = fileContentMap.loadLinesCached(file);
                        lines.add(s);
                    }
                };

                try {
                    etcFiles.install(derivedClass, editor);
                } catch (IOException e) {
                    logger.error(e, "Error install " + derivedClass + " of type " + etcFilesClass);
                }
            } // etc-files
        } // for derivations

        return fileContentMap;
    }

    void beforeRefresh(List<String> list) {
        if (includeFqcnPrefixes.isEmpty()) {
            list.clear();
            return;
        }
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (isClassNameIncluded(line))
                iterator.remove();
        }
    }

    boolean isClassNameIncluded(String className) {
        for (String prefix : includeFqcnPrefixes)
            if (className.startsWith(prefix))
                return true;
        return false;
    }

    static boolean isMavenTest() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        List<Path> userClassPath = URLClassLoaders.getUserClassPath(classLoader);
        String head = userClassPath.get(0).toString();
        if (head.contains("/test-classes")) {
            logger.info("Detect test mode, create test class loader.");
            return true;
        }
        return false;
    }

    static ClassLoader getDefaultLoader() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        if (isMavenTest()) {
            URLClassLoader ucl = URLClassLoaders.findFirstURLClassLoader(classLoader);
            return MavenTestClassLoader.createMavenTestClassLoader(ucl);
        }
        return classLoader;
    }

    public ClassForrest scan(ClassLoader classLoader)
            throws IOException {
        ClassFinder scanner = new ClassFinder(scanOptions);

        if (includeFqcnPrefixes.isEmpty()) {
            scanner.addAllClasses(classLoader);
        } else {
            for (String packageName : includeFqcnPrefixes)
                scanner.addPackage(classLoader, packageName);
        }

        return scanner;
    }

    SaveOptions saveOptions = new SaveOptions();
    {
        saveOptions.setPurgeEmpty(deleteEmptyFiles);
        saveOptions.setCompare(true);
        saveOptions.setMkdirs(true);
        saveOptions.setEncoding("utf-8");
    }

    public Collection<Class<?>> collect(ClassForrest forrest, Class<?> baseClass)
            throws IOException {
        logger.info("For " + baseClass.getCanonicalName());

        Collection<Class<?>> classes = forrest.listFilteredDerivations(baseClass);

        ClassFileMap filterMap = filter(classes, baseClass);
        // filterMap.subMap(baseDir);

        FileCacheMap<Path> contents = publishEtcFiles(filterMap, baseClass);
        if (contents.isEmpty())
            return Collections.emptyList();

        contents.save(saveOptions);

        if (showPaths)
            for (Class<?> extension : filterMap.keySet()) {
                MavenPomDir pomDir = MavenPomDir.fromClass(extension);
                Path sourceFile = pomDir.getSourceFile(extension);
                System.out.println(sourceFile);
            }
        return filterMap.keySet();
    }

}
