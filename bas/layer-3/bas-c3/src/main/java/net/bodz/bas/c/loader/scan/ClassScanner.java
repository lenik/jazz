package net.bodz.bas.c.loader.scan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ClassScanner
        extends ResourceScanner {

    static final Logger logger = LoggerFactory.getLogger(ClassScanner.class);

    ClassForrest forrest = new ClassForrest();

    public ClassScanner() {
        this(new DefaultScanOptions());
    }

    public ClassScanner(IScanOptions options) {
        super(classOnly(options));
    }

    static IScanOptions classOnly(IScanOptions options) {
        return new DecoratedScanOptions(options) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean acceptFileEntry(String name) {
                if (! name.endsWith(".class"))
                    return false;
                return super.acceptFileEntry(name);
            }
        };
    }

    class Name2Type
            implements
                ITypeNameCallback {

        final ClassLoader classLoader;
        final ITypeCallback typeCallback;
        final List<Class<?>> classes = new ArrayList<>();

        public Name2Type(ClassLoader classLoader, ITypeCallback typeCallback) {
            if (classLoader == null)
                throw new NullPointerException("classLoader");
            this.classLoader = classLoader;
            this.typeCallback = typeCallback;
        }

        @Override
        public boolean typeName(String fqcn) {
            if (failedNames.contains(fqcn))
                return false;
            Class<?> clazz;
            try {
                clazz = Class.forName(fqcn, false, classLoader);
            } catch (ClassNotFoundException e) {
                logger.error("Failed to resolve class: " + fqcn);
                failedNames.add(fqcn);
                return false;
            } catch (NoClassDefFoundError e) {
                logger.error("Failed to resolve class: " + fqcn);
                failedNames.add(fqcn);
                return false;
            }

            classes.add(clazz);
            if (typeCallback != null)
                return typeCallback.type(clazz);
            return true;
        }

        @Override
        public String toString() {
            return String.format("%d classes, typefn = %s", classes.size(), typeCallback);
        }

    }

    static Set<String> failedNames = new HashSet<>();

    public ClassForrest scanTypes(ClassLoader classLoader, String packageName)
            throws IOException {
        return scanTypes(classLoader, packageName, null);
    }

    public ClassForrest scanTypes(ClassLoader classLoader, String packageName, final ITypeCallback typeCallback)
            throws IOException {
        Name2Type mapper = new Name2Type(classLoader, typeCallback);
        scanTypeNames(classLoader, packageName, mapper);
        forrest.parseAll(mapper.classes);
        return forrest;
    }

    public List<String> scanTypeNames(ClassLoader classLoader, String packageName, final ITypeNameCallback callback)
            throws IOException {
        List<String> qNames = new ArrayList<>();

        String packageDir = packageName.replace('.', '/');

        ResList resources = scanResources(classLoader, packageDir);
        for (String relativePath : resources.paths()) {
            assert relativePath.endsWith(".class");
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

            // fqcn = fqcn.replace('$', '.');
            if (! callback.typeName(qName)) {
                // break;
            }
        }

        return qNames;
    }

    public ClassForrest scanPackage(ClassLoader classLoader, String packageName)
            throws IOException {
        scanTypes(classLoader, packageName);
        return forrest;
    }

}
