package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenTestClassLoader;
import net.bodz.bas.c.type.CachedInstantiator;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IEtcFilesEditor;
import net.bodz.bas.meta.codegen.IEtcFilesInstaller;
import net.bodz.bas.meta.codegen.IndexedType;

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

//    public ClassScanner getScanner() {
//        return scanner;
//    }

    public void includeDirToScan(File dir) {
        scanOptions.addDirToInclude(dir.getAbsolutePath());
    }

    public void excludeDirToScan(File dir) {
        scanOptions.addDirToExclude(dir.getAbsolutePath());
    }

    public void includePackageToScan(String packageName) {
        includeFqcnPrefixes.add(packageName);
    }

    public void excludePackageToScan(String packageName) {
        excludeFqcnPrefixes.remove(packageName);
    }

    protected FileContentMap publishEtcFiles(ClassForrest forrest, Class<?> baseClass) {
        FileContentMap fileContentMap = new FileContentMap();

        IndexedType aIndexedType = baseClass.getAnnotation(IndexedType.class);

        logger.info("For " + baseClass.getCanonicalName());

        String info = null;
        Collection<Class<?>> derivedClasses = forrest.listFilteredDerivations(baseClass);
        for (Class<?> derivedClass : derivedClasses) {
            if (info != null)
                logger.info(info);

            String derivedClassName = derivedClass.getCanonicalName();
            int modifier = derivedClass.getModifiers();

            info = "    Subclass: " + (baseClass.isAnnotation() ? "@" : "") + derivedClassName;
            if (aIndexedType == null) { // Not indexed-type, skipped
                info += " -noindex";
                continue;
            }

            if (! Modifier.isPublic(modifier))
                if (! aIndexedType.includeNonPublic()) {
                    info += " -nonpub";
                    continue;
                }

            if (derivedClass.isAnnotation()) {
                if (! aIndexedType.includeAnnotation()) {
                    info += " -ann";
                    continue;
                }
            } else {
                // Non-annotation.
                if (! aIndexedType.includeClass()) {
                    info += " -class";
                    continue;
                }
                if (Modifier.isAbstract(modifier))
                    if (! aIndexedType.includeAbstract()) {
                        info += " -abstract";
                        continue;
                    }
            }

            URL url = derivedClass.getResource(derivedClass.getSimpleName() + ".class");
            if ("file".equals(url.getProtocol())) {
                String path = url.getPath();
                File file = new File(path);
                if (! scanOptions.acceptPath(file)) {
                    info += " -dir";
                    continue;
                }
            }

            fileContentMap.extensions.add(derivedClass);

            MavenPomDir pomDir = MavenPomDir.fromClass(derivedClass);
            if (pomDir == null) {
                logger.debug("Not belongs to maven project. Maybe in the jar: " + derivedClass);
                info += " jar";
                continue;
            }

            final File resDir = pomDir.getResourceDir(derivedClass);
            if (resDir == null) {
                logger.debug("No resource dir: " + derivedClassName);
                info += " nrd";
                continue;
            }

            logger.info(info);
            info = null;

            String publishDir = aIndexedType.publishDir();
            if (! publishDir.isEmpty()) {
                if (! publishDir.endsWith("/"))
                    publishDir += "/";

                File listFile = new File(resDir, publishDir + baseClass.getName());
                boolean init = ! fileContentMap.containsKey(listFile);
                List<String> lines = fileContentMap.loadFile(listFile);

                if (init) // Refresh: remove included packages before re-add.
                    beforeRefresh(lines);

                if (aIndexedType.obsoleted()) {
                    // lines.add("# " + extension.getName());
                } else {
                    lines.add(derivedClass.getName());
                }
            } // publishDir

            Class<? extends IEtcFilesInstaller> etcFilesClass = aIndexedType.etcFiles();
            if (etcFilesClass != null && ! Modifier.isAbstract(etcFilesClass.getModifiers())) {
                IEtcFilesInstaller etcFiles = CachedInstantiator.getInstance().instantiate(etcFilesClass);

                IEtcFilesEditor editor = new IEtcFilesEditor() {
                    @Override
                    public void clear(String path) {
                        File file = FilePath.joinHref(resDir, path);
                        List<String> lines = fileContentMap.loadFile(file);
                        lines.clear();
                    }

                    @Override
                    public void addLine(String path, String s) {
                        File file = FilePath.joinHref(resDir, path);
                        List<String> lines = fileContentMap.loadFile(file);
                        lines.add(s);
                    }
                };

                etcFiles.install(derivedClass, editor);
            } // etc-files
        } // for derivations

        if (info != null)
            logger.info(info);

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
        List<File> userClassPath = URLClassLoaders.getUserClassPath(classLoader);
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

    public List<Class<?>> collect(ClassForrest forrest, Class<?> baseClass)
            throws IOException {

        FileContentMap contents = publishEtcFiles(forrest, baseClass);
        int size = contents.size();
        contents.saveFiles(deleteEmptyFiles);

        if (showPaths)
            for (Class<?> extension : contents.extensions) {
                MavenPomDir pomDir = MavenPomDir.fromClass(extension);
                File sourceFile = pomDir.getSourceFile(extension);
                System.out.println(sourceFile);
            }
        return contents.extensions;
    }

}
