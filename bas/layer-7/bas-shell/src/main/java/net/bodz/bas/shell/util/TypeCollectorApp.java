package net.bodz.bas.shell.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.c.loader.scan.TypeCollector;
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
     * Base types to be scanned.
     * <p lang="zh-cn">
     * 指定要收集的基类。
     *
     * @option -t
     */
    List<Class<?>> indexedTypes = new ArrayList<Class<?>>();

    /**
     * Include packages to be scaned.
     *
     * Default to search all packages in the working project.
     *
     * <p lang="zh-cn">
     * 指定要收集的包名列表。
     *
     * 默认收集当前工作项目。
     *
     * @option -p --package =FQPN
     */
    List<String> scanPackages = new ArrayList<String>();

    // List<String> excludedPackages;

    /**
     * Include directories to be scanned.
     *
     * Default to search all dirs/projects.
     *
     * @option -d --dir =PATH
     */
    List<File> scanDirs = new ArrayList<>();

    ClassLoader classLoader = ClassLoaders.getRuntimeClassLoader();
    TypeIndex typeIndex;

    public TypeCollectorApp() {
        typeIndex = TypeIndex.getInstance(classLoader);
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {

        if (logger.getLevel().compareTo(LogLevel.DEBUG) >= 0)
            URLClassLoaders.dump(classLoader, Stdio.cout);

        buildPackageList(scanPackages);

        for (String pkg : scanPackages)
            logger.info("Search-Package: ", pkg);

        if (scanPackages.isEmpty() && scanDirs.isEmpty()) {
            File workDir = SysProps.userWorkDir;
            MavenPomDir pomDir = MavenPomDir.closest(workDir);
            if (pomDir == null) {
                logger.error("Not with-in a maven project: " + workDir);
                _exit(1);
            }
            File targetDir = new File(pomDir.getBaseDir(), "target");
            scanDirs.add(targetDir);
        }

        for (File dir : scanDirs)
            logger.info("Search-Dir: ", dir);

        if (indexedTypes.isEmpty())
            for (Class<?> type : typeIndex.listAnnodated(IndexedType.class))
                indexedTypes.add(type);

        TypeCollector collector = new TypeCollector(classLoader);

        for (Class<?> indexedType : indexedTypes) {

            if (listOnly) {
                System.out.println("SPI: " + indexedType);
                Collection<?> list = typeIndex.listIndexed(indexedType);
                int i = 0, n = list.size();
                for (Class<?> extension : typeIndex.listIndexed(indexedType)) {
                    String graph = i++ == n - 1 ? "    `- " : "    |- ";
                    System.out.println(graph + extension);
                }
                continue;
            }

            for (String pkg : scanPackages)
                collector.includePackageToScan(pkg);

            for (File dir : scanDirs) {
                dir = dir.getCanonicalFile();
                collector.includeDirToScan(dir);
            }

            collector.collect(indexedType);
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
