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
import java.util.Set;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenTestClassLoader;
import net.bodz.bas.c.type.CachedInstantiator;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IEtcFilesEditor;
import net.bodz.bas.meta.codegen.IEtcFilesInstaller;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

@IndexedType
public class TypeCollector {

    static Logger logger = LoggerFactory.getLogger(TypeCollector.class);

    ClassScanner scanner;

    List<String> includeFqcnPrefixes = new ArrayList<String>();
    List<String> excludeFqcnPrefixes = new ArrayList<String>();

    boolean scanned;

    boolean showPaths;
    boolean deleteEmptyFiles = true;

    public TypeCollector(ClassLoader classLoader) {
        URLClassLoader ucl;

        ucl = (URLClassLoader) classLoader;
        // URL[] callerClasspath = new URL[] { ClassResource.getRootURL(getClass()), };
        // loader = new URLClassLoader(callerClasspath);

        List<File> localURLs = URLClassLoaders.getLocalURLs(ucl);
        // System.out.println("UCL for " + baseClass);
        // for (File u : localURLs) System.out.println("-- " + u);

        String url0 = localURLs.get(0).toString();
        if (url0.contains("/test-classes")) {
            logger.info("Detect test mode, create test class loader.");
            ucl = MavenTestClassLoader.createMavenTestClassLoader(ucl);
        }

        scanner = createClassScanner(classLoader);
    }

    protected ClassScanner createClassScanner(ClassLoader classLoader) {
        return ClassScanner.getInstance(classLoader);
    }

    public boolean isShowPaths() {
        return showPaths;
    }

    public void setShowPaths(boolean showPaths) {
        this.showPaths = showPaths;
    }

    public ClassScanner getScanner() {
        return scanner;
    }

    public void includeDirToScan(File dir) {
        scanner.addDirToInclude(dir.getAbsolutePath());
    }

    public void excludeDirToScan(File dir) {
        scanner.addDirToExclude(dir.getAbsolutePath());
    }

    public void includePackageToScan(String packageName) {
        includeFqcnPrefixes.add(packageName);
    }

    public void excludePackageToScan(String packageName) {
        excludeFqcnPrefixes.remove(packageName);
    }

    protected FileContentMap publishEtcFiles(Class<?> baseClass) {
        FileContentMap fileContentMap = new FileContentMap();

        IndexedType aIndexedType = baseClass.getAnnotation(IndexedType.class);

        logger.info("For " + baseClass.getCanonicalName());

        String info = null;
        Collection<Class<?>> derivedClasses = listFilteredDerivations(baseClass);
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

            if (!Modifier.isPublic(modifier))
                if (!aIndexedType.includeNonPublic()) {
                    info += " -nonpub";
                    continue;
                }

            if (derivedClass.isAnnotation()) {
                if (!aIndexedType.includeAnnotation()) {
                    info += " -ann";
                    continue;
                }
            } else {
                // Non-annotation.
                if (!aIndexedType.includeClass()) {
                    info += " -class";
                    continue;
                }
                if (Modifier.isAbstract(modifier))
                    if (!aIndexedType.includeAbstract()) {
                        info += " -abstract";
                        continue;
                    }
            }

            URL url = derivedClass.getResource(derivedClass.getSimpleName() + ".class");
            if ("file".equals(url.getProtocol())) {
                String path = url.getPath();
                File file = new File(path);
                if (!scanner.isIncluded(file)) {
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
            if (!publishDir.isEmpty()) {
                if (!publishDir.endsWith("/"))
                    publishDir += "/";

                File listFile = new File(resDir, publishDir + baseClass.getName());
                boolean init = !fileContentMap.containsKey(listFile);
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
            if (etcFilesClass != null && !Modifier.isAbstract(etcFilesClass.getModifiers())) {
                IEtcFilesInstaller etcFiles = CachedInstantiator.getInstance().instantiate(etcFilesClass);

                IEtcFilesEditor editor = new IEtcFilesEditor() {
                    @Override
                    public void clear(String path) {
                        File file = new File(resDir, path);
                        List<String> lines = fileContentMap.loadFile(file);
                        lines.clear();
                    }

                    @Override
                    public void addLine(String path, String s) {
                        File file = new File(resDir, path);
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

    protected Collection<Class<?>> listFilteredDerivations(Class<?> base) {
        List<Class<?>> list = new ArrayList<Class<?>>();

        Set<Class<?>> derivations = scanner.getDerivations(base, -1);
        for (Class<?> derivation : derivations) {
            if (derivation.isAnonymousClass())
                continue;

            /*
             * Defined inside a code block, only visible inside that code block
             */
            if (derivation.isLocalClass())
                continue;

            if (derivation.isMemberClass())
                /*
                 * A class defined as non-static inside another class. Instances are dependent on an
                 * instance of the enclosing class.
                 */
                if (!Modifier.isStatic(derivation.getModifiers()))
                    continue;

            // ExcludedFromIndex a = derivation.getAnnotation(ExcludedFromIndex.class);
            if (derivation.isAnnotationPresent(ExcludedFromIndex.class))
                continue;
            if (derivation.isAnnotationPresent(ExcludedFromIndex.Inherited.class))
                continue;

            if (derivation.isAnnotationPresent(IndexedTypeLoader.class))
                continue;

            list.add(derivation);
        }
        return list;
    }

    synchronized void scan()
            throws IOException {
        if (!scanned) {
            if (includeFqcnPrefixes.isEmpty()) {
                scanner.scanPackage("");
            } else {
                for (String packageName : includeFqcnPrefixes)
                    scanner.scanPackage(packageName);
            }
        }
        scanned = true;
    }

    public List<Class<?>> collect(Class<?> baseClass)
            throws IOException {
        scan();

        FileContentMap fileContentMap = publishEtcFiles(baseClass);
        fileContentMap.saveFiles(deleteEmptyFiles);

        if (showPaths)
            for (Class<?> extension : fileContentMap.extensions) {
                MavenPomDir pomDir = MavenPomDir.fromClass(extension);
                File sourceFile = pomDir.getSourceFile(extension);
                System.out.println(sourceFile);
            }
        return fileContentMap.extensions;
    }

}
