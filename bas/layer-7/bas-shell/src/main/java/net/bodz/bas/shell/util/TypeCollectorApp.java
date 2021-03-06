package net.bodz.bas.shell.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.loader.ClassLoaders;
import net.bodz.bas.c.loader.scan.TypeCollector;
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
    List<Class<?>> baseTypes = new ArrayList<Class<?>>();

    /**
     * Packages to be collected.
     * <p lang="zh-cn">
     * 指定要收集的包名列表。
     *
     * @option -p =FQPN
     */
    List<String> packages = new ArrayList<String>();

    // List<String> excludedPackages;

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

        if (packages.isEmpty())
            buildPackageList(packages);
        for (String p : packages)
            logger.info("Search-Prefix: ", p);

        if (baseTypes.isEmpty())
            for (Class<?> type : typeIndex.listAnnodated(IndexedType.class))
                baseTypes.add(type);

        for (Class<?> baseType : baseTypes) {
            logger.info("Base-Type: ", baseType);

            if (listOnly) {
                for (Class<?> extension : typeIndex.listIndexed(baseType))
                    logger.info("    Extension: ", extension);
                continue;
            }

            TypeCollector<?> collector = new TypeCollector<Object>(classLoader, baseType);

            for (String pkg : packages)
                collector.includePackageToScan(pkg);

            collector.collect();
        }
    }

    protected void buildPackageList(List<String> packages) {
        String projectPackage = getClass().getPackage().getName();
        packages.add(projectPackage);
        packages.add("user");
    }

    public static void main(String[] args)
            throws Exception {
        new TypeCollectorApp().execute(args);
    }

}
