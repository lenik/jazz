package net.bodz.bas.c.loader.scan;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.io.res.ContainerItemList;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ClassFinder
        extends ClassForrest {

    static final Logger logger = LoggerFactory.getLogger(ClassFinder.class);

    IScanOptions options;
    Set<String> failedNames = new LinkedHashSet<>();

    public ClassFinder() {
        this(new DefaultScanOptions());
    }

    public ClassFinder(IScanOptions options) {
        this.options = (ScanOptions.classOnly(options));
    }

    public ClassFinder addAllClasses(ClassLoader classLoader)
            throws IOException {
        return addPackage(classLoader, null);
    }

    public ClassFinder addPackage(ClassLoader classLoader, String packageName)
            throws IOException {
        ClassNameList list = listPackage(classLoader, packageName);
//        System.out.println("Package " + packageName);
//        for (String cn : new TreeSet<String>(list))
//            System.out.println("    " + cn);
        list.analyze(failedNames);
        return this;
    }

    public ClassNameList listPackage(ClassLoader classLoader, String packageName)
            throws IOException {
        ClassNameList qNames = new ClassNameList(classLoader, this);

        String packageDir = packageName == null ? "" : packageName.replace('.', '/');

        ResourceScanner scanner = new ResourceScanner(options);
        ContainerItemList resources = scanner.scanResources(classLoader, packageDir);
        for (String relativePath : resources.paths()) {
            if (! relativePath.endsWith(".class"))
                continue;

            String qName = relativePath.substring(0, relativePath.length() - 6).replace('/', '.');

            int dollar = qName.lastIndexOf('$');
            if (dollar != -1) {
                String tail = qName.substring(dollar + 1);
                if (StringPred.isDecimal(tail)) {
                    // Ignore anonymous inner classes.
                    continue;
                }
            }

            qNames.add(qName);

        }
        return qNames;
    }

}