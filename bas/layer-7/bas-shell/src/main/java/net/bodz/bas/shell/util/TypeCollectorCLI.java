package net.bodz.bas.shell.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.loader.scan.TypeCollector;
import net.bodz.bas.c.type.TypeIndex;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.program.meta.ProgramName;
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
public class TypeCollectorCLI
        extends BasicCLI {

    private static final Logger logger = LoggerFactory.getLogger(TypeCollectorCLI.class);

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
    List<Class<?>> baseTypes = new ArrayList<>();

    /**
     * Packages to be collected.
     * <p lang="zh-cn">
     * 指定要收集的包名列表。
     *
     * @option -p =FQPN
     */
    List<String> packages = new ArrayList<>();

    // List<String> excludedPackages;

    TypeIndex typeIndex;

    public TypeCollectorCLI() {
        typeIndex = TypeIndex.getSclTypeIndex();
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {

        if (packages.isEmpty()) {
            packages.add("net.bodz");
            packages.add("user");
        }

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

            TypeCollector<?> collector = new TypeCollector<Object>(baseType);

            for (String pkg : packages)
                collector.includePackageToScan(pkg);

            collector.collect();
        }
    }

    public static void main(String[] args)
            throws Exception {
        new TypeCollectorCLI().execute(args);
    }

}
