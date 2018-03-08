package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URLClassLoader;
import java.util.*;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenTestClassLoader;
import net.bodz.bas.c.type.CachedInstantiator;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.codegen.IEtcFilesEditor;
import net.bodz.bas.meta.codegen.IEtcFilesInstaller;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

@IndexedType
public class TypeCollector<T> {

    static Logger logger = LoggerFactory.getLogger(TypeCollector.class);

    final Class<?> baseClass;

    ClassScanner scanner;
    List<String> includePrefixes = new ArrayList<String>();
    List<String> excludePrefixes = new ArrayList<String>();
    boolean scanned;

    boolean showPaths;
    List<Class<?>> extensions;
    TreeMap<File, List<String>> fileContentMap = new TreeMap<File, List<String>>();
    boolean deleteEmptyFiles = true;

    protected TypeCollector(ClassLoader classLoader) {
        this(classLoader, null);
    }

    public TypeCollector(ClassLoader classLoader, Class<?> baseClass) {
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

        if (baseClass != null)
            this.baseClass = baseClass;
        else
            this.baseClass = TypeParam.infer1(getClass(), TypeCollector.class, 0);
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

    public void includePackageToScan(String packageName) {
        includePrefixes.add(packageName);
    }

    public void excludePackageToScan(String packageName) {
        excludePrefixes.remove(packageName);
    }

    protected void scanTypes()
            throws IOException {
        for (String packageName : includePrefixes)
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

            info = "    Subclass: " + (baseClass.isAnnotation() ? "@" : "") + extensionName;
            if (aIndexedType == null) { // Not indexed-type, skipped
                info += " -noindex";
                continue;
            }

            if (extension.isAnnotation()) {
                if (!aIndexedType.includeAnnotation()) {
                    info += " -ann";
                    continue;
                }
            } else {
                if (!aIndexedType.includeClass()) {
                    info += " -class";
                    continue;
                }

                int modifier = extension.getModifiers();
                if (Modifier.isAbstract(modifier))
                    if (!aIndexedType.includeAbstract()) {
                        info += " -abstract";
                        continue;
                    }

                if (!Modifier.isPublic(modifier))
                    if (!aIndexedType.includeNonPublic()) {
                        info += " -nonpub";
                        continue;
                    }
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
                boolean loaded = fileContentMap.containsKey(sfile);
                List<String> lines = loadFile(sfile);

                // only update included packages.
                if (!loaded) {
                    Iterator<String> iterator = lines.iterator();
                    while (iterator.hasNext()) {
                        String line = iterator.next();
                        for (String prefix : includePrefixes)
                            if (line.startsWith(prefix)) {
                                iterator.remove();
                                break;
                            }
                    }
                }

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
                        List<String> lines = loadFile(file);
                        lines.clear();
                    }

                    @Override
                    public void addLine(String path, String s) {
                        File file = new File(resDir, path);
                        List<String> lines = loadFile(file);
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

        for (Class<?> derivation : scanner.getDerivations(base, -1)) {
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

    protected List<String> loadFile(File file) {
        List<String> list = fileContentMap.get(file);
        if (list == null) {
            list = new ArrayList<>();
            if (file.exists())
                for (String line : new FileResource(file).to(StreamReading.class).lines()) {
                    list.add(line.trim());
                }
            fileContentMap.put(file, list);
        }
        return list;
    }

    protected void saveFiles(Map<File, List<String>> fileContentMap)
            throws IOException {
        for (Map.Entry<File, List<String>> entry : fileContentMap.entrySet()) {
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
