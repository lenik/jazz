package net.bodz.bas.c.loader.scan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class Name2Type
        implements
            ITypeNameCallback {

    static final Logger logger = LoggerFactory.getLogger(Name2Type.class);

    final ClassLoader classLoader;
    final ITypeCallback typeCallback;
    final List<Class<?>> classes = new ArrayList<>();

    static Set<String> failedNames = new HashSet<>();

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
