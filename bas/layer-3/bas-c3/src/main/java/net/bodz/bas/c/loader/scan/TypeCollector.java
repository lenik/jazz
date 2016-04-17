package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.c.java.util.LazyTreeMap;
import net.bodz.bas.c.java.util.NewArrayList;
import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenTestClassLoader;
import net.bodz.bas.c.type.CachedInstantiator;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IEtcFilesEditor;
import net.bodz.bas.meta.codegen.IEtcFilesInstaller;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public class TypeCollector<T> {

    static Logger logger = LoggerFactory.getLogger(TypeCollector.class);

    final Class<?> baseClass;

    ClassScanner scanner;
    List<String> includePackages = new ArrayList<String>();
    List<String> excludePackages = new ArrayList<String>();
    boolean scanned;

    boolean showPaths;
    List<Class<?>> extensions;
    LazyTreeMap<File, List<String>> fileContentMap = new LazyTreeMap<File, List<String>>(
            new NewArrayList<File, String>());
    boolean deleteEmptyFiles = true;

    protected TypeCollector() {
        this(null);
    }

    public TypeCollector(Class<?> baseClass) {
        URLClassLoader loader;

        loader = (URLClassLoader) ClassLoaders.getRuntimeClassLoader();
        // URL[] callerClasspath = new URL[] { ClassResource.getRootURL(getClass()), };
        // loader = new URLClassLoader(callerClasspath);

        List<File> localURLs = URLClassLoaders.getLocalURLs(loader);
        String url0 = localURLs.get(0).toString();
        if (url0.contains("/test-classes/")) {
            logger.info("Detect test mode, create test class loader.");
            loader = MavenTestClassLoader.createMavenTestClassLoader(loader);
        }

        scanner = createClassScanner();
        scanner.setClassLoader(loader);

        if (baseClass != null)
            this.baseClass = baseClass;
        else
            this.baseClass = TypeParam.infer1(getClass(), TypeCollector.class, 0);
    }

    protected ClassScanner createClassScanner() {
        return new ClassScanner();
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

    public void includePackageToScan(String packageName) {
        includePackages.add(packageName);
    }

    public void excludePackageToScan(String packageName) {
        excludePackages.remove(packageName);
    }

    protected void scanTypes()
            throws IOException {
        for (String packageName : includePackages)
            scanner.scan(packageName);
    }

    protected void createExtensionFiles() {
        IndexedType aIndexedType = baseClass.getAnnotation(IndexedType.class);

        logger.info("For " + baseClass.getCanonicalName());

        String info = null;
        for (Class<?> extension : listFilteredDerivations(baseClass)) {
            if (info != null)
                logger.info(info);

            String extensionName = extension.getCanonicalName();

            if (baseClass.isAnnotation())
                info = "    Subclass: @" + extensionName;
            else
                info = "    Subclass: " + extensionName;

            if (aIndexedType == null) {
                // Not indexed-type, skipped
                info += " -N";
                continue;
            }

            int modifier = extension.getModifiers();
            if (Modifier.isAbstract(modifier))
                if (aIndexedType.includeAbstract())
                    // Included abstract class
                    info += " +a";
                else {
                    info += " -a";
                    continue;
                }

            if (!Modifier.isPublic(modifier))
                if (aIndexedType.includeNonPublic())
                    // Included non-public class
                    info += " -";
                else {
                    info += " +";
                    continue;
                }

            if (extension.isAnnotation())
                if (aIndexedType.includeAnnotation())
                    // Included annotation class
                    info += " +@";
                else {
                    info += " -@";
                    continue;
                }

            extensions.add(extension);

            MavenPomDir pomDir = MavenPomDir.fromClass(extension);
            if (pomDir == null) {
                logger.debug("Not belongs to maven project. Maybe in the jar: " + extension);
                info += " jar";
                continue;
            }

            final File resDir = pomDir.getResourceDir(extension);
            if (resDir == null) {
                logger.debug("No resource dir: " + extensionName);
                info += " nrd";
                continue;
            }

            logger.info(info);
            info = null;

            String publishDir = aIndexedType.publishDir();
            if (!publishDir.isEmpty()) {
                if (!publishDir.endsWith("/"))
                    publishDir += "/";

                File sfile = new File(resDir, publishDir + baseClass.getName());
                List<String> lines = fileContentMap.getOrLoad(sfile);

                if (aIndexedType.obsoleted()) {
                    // lines.add("# " + extension.getName());
                } else {
                    lines.add(extension.getName());
                }
            } // publishDir

            Class<? extends IEtcFilesInstaller> etcFilesClass = aIndexedType.etcFiles();
            if (etcFilesClass != null && !Modifier.isAbstract(etcFilesClass.getModifiers())) {
                IEtcFilesInstaller etcFiles = CachedInstantiator.getInstance().instantiate(etcFilesClass);

                IEtcFilesEditor editor = new IEtcFilesEditor() {
                    @Override
                    public void clear(String path) {
                        File file = new File(resDir, path);
                        List<String> lines = fileContentMap.getOrLoad(file);
                        lines.clear();
                    }

                    @Override
                    public void addLine(String path, String s) {
                        File file = new File(resDir, path);
                        List<String> lines = fileContentMap.getOrLoad(file);
                        lines.add(s);
                    }
                };

                etcFiles.install(extension, editor);
            } // etc-files
        } // for derivations

        if (info != null)
            logger.info(info);
    }

    protected Collection<Class<?>> listFilteredDerivations(Class<?> base) {
        List<Class<?>> list = new ArrayList<Class<?>>();

        for (Class<?> derivation : scanner.getDerivations(base)) {
            if (derivation.isAnonymousClass() || derivation.isLocalClass() || derivation.isMemberClass())
                continue;

            if (derivation.isAnnotationPresent(ExcludedFromIndex.class))
                continue;

            list.add(derivation);
        }
        return list;
    }

    protected void saveFiles(Map<File, List<String>> fileContentMap)
            throws IOException {
        for (Entry<File, List<String>> entry : fileContentMap.entrySet()) {
            File file = entry.getKey();

            List<String> lines = entry.getValue();
            Collections.sort(lines);

            if (lines.isEmpty() && deleteEmptyFiles) {
                if (file.delete())
                    logger.info("Deleted " + file);
                continue;
            }

            StringBuilder buf = new StringBuilder(lines.size() * 100);
            for (String line : new LinkedHashSet<String>(lines)) {
                buf.append(line);
                buf.append('\n');
            }
            String content = buf.toString();

            // The same?
            if (file.exists() == Boolean.TRUE) {
                String old = FileData.readString(file);
                if (content.equals(old)) {
                    logger.debug("Not changed: " + file);
                    continue;
                }
            }

            try {
                logger.info("Update: " + file);
                file.getParentFile().mkdirs();
                FileData.writeString(file, content);
            } catch (IOException e) {
                logger.error("Failed to update " + file, e);
            }
        }
    }

    public void collect()
            throws IOException {
        TypeCollector<T> wired = this;
        wired._collect();
    }

    synchronized void _collect()
            throws IOException {
        fileContentMap.clear();
        extensions = new ArrayList<>();
        scanTypes();
        createFiles();
        saveFiles(fileContentMap);
        if (showPaths)
            for (Class<?> extension : extensions) {
                MavenPomDir pomDir = MavenPomDir.fromClass(extension);
                File sourceFile = pomDir.getSourceFile(extension);
                System.out.println(sourceFile);
            }
        extensions = null;
    }

    protected void createFiles() {
        createExtensionFiles();
    }

}