package net.bodz.bas.codegen.java;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.c.loader.scan.ClassCollector;
import net.bodz.bas.c.loader.scan.ClassForrest;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Collect derived types for specific base types.
 * <p lang="zh-cn">
 * 收集指定类的派生类型集。
 *
 * @label TypeColl
 * @label.zh_cn 类收集器
 */
@ProgramName("tcoll")
public class TypeCollectorApp
        extends BasicCLI {

    private static final Logger logger = LoggerFactory.getLogger(TypeCollectorApp.class);

    /**
     * Don't collect, just show the extensions.
     * <p lang="zh-cn">
     * 只显示扩展类，不执行收集动作。
     *
     * @option -l
     */
    boolean listOnly;

    /**
     * Update all files reachable. By default only files under cwd will be updated.
     * @option -a
     */
    boolean updateAll;

    /**
     * Save files only if it's in one of the updates dirs.
     * @option
     */
    List<Path> updateDirs = new ArrayList<>();

    /**
     * Base types to be scanned.
     * <p lang="zh-cn">
     * 指定要收集的基类。
     *
     * @option -t
     */
    List<Class<?>> indexedTypes = new ArrayList<Class<?>>();

    /**
     * Include packages to be scaned.
     * <p>
     * Default to search all packages in the working project.
     *
     * <p lang="zh-cn">
     * 指定要收集的包名列表。
     * <p>
     * 默认收集当前工作项目。
     *
     * @option -p --package =FQPN
     */
    List<String> scanPackages = new ArrayList<String>();

    // List<String> excludedPackages;

    /**
     * Include directories to be scanned.
     * <p>
     * Default to search all dirs/projects.
     *
     * @option -d --dir =PATH
     */
    List<Path> scanDirs = new ArrayList<>();

    ClassLoader classLoader;
    TypeIndex typeIndex;

    public TypeCollectorApp() {
        classLoader = getDefaultClassLoader();
        typeIndex = TypeIndex.getInstance(classLoader);
    }

    protected ClassLoader getDefaultClassLoader() {
        return ClassLoaders.getRuntimeClassLoader();
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {

        if (logger.getLevel().compareTo(LogLevel.DEBUG) >= 0)
            URLClassLoaders.dump(classLoader, Stdio.cout);

        if (updateDirs.isEmpty())
            updateDirs.add(SysProps.userWorkDir);

        buildPackageList(scanPackages);

        for (String pkg : scanPackages)
            logger.info("Search-Package: ", pkg);

        if (scanPackages.isEmpty() && scanDirs.isEmpty()) {
            Path workDir = SysProps.userWorkDir;
            MavenPomDir pomDir = MavenPomDir.closest(workDir);
            if (pomDir == null) {
                logger.error("Not with-in a maven project: " + workDir);
                _exit(1);
                return;
            }
            Path targetDir = pomDir.getBaseDir().resolve("target");
            scanDirs.add(targetDir);
        }

        for (Path dir : scanDirs)
            logger.info("Search-Dir: ", dir);

        if (indexedTypes.isEmpty())
            indexedTypes.addAll(typeIndex.listAnnodated(IndexedType.class));

        ClassCollector collector = new ClassCollector();
        // collector.includeDirToScan(SysProps.userWorkDir);
        collector.setUpdateAllFiles(updateAll);
        collector.setUpdateDirs(updateDirs);

        for (String pkg : scanPackages)
            collector.includePackageToScan(pkg);

        for (Path dir : scanDirs) {
            dir = FilePath.canoniOf(dir);
            collector.includeDirToScan(dir);
        }

        ClassForrest forrest = collector.scan(classLoader);

        for (Class<?> baseClass : indexedTypes) {
            if (!baseClass.isAnnotationPresent(IndexedType.class)) {
                logger.error("@IndexedType not present on " + baseClass);
                continue;
            }

            if (listOnly) {
                System.out.println("SPI: " + baseClass);
                Collection<?> list = typeIndex.listIndexed(baseClass);
                int i = 0, n = list.size();
                for (Class<?> extension : typeIndex.listIndexed(baseClass)) {
                    String graph = i++ == n - 1 ? "    `- " : "    |- ";
                    System.out.println(graph + extension);
                }
            } else {
                collector.collect(forrest, baseClass);
            }
        }
    }

    protected void buildPackageList(List<String> packages) {
        // String projectPackage = getClass().getPackage().getName();
        // packages.add(projectPackage);
    }

    public static void main(String[] args)
            throws Exception {
        TypeCollectorApp app = new TypeCollectorApp();
        app.execute(args);
    }

}
