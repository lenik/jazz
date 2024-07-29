package net.bodz.bas.c.loader.scan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ClassNameList
        extends ArrayList<String> {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(ClassNameList.class);

    ClassLoader classLoader;
    ClassForrest forrest;

    public ClassNameList(ClassLoader classLoader, ClassForrest forrest) {
        this.classLoader = classLoader;
        this.forrest = forrest;
    }

    public ClassForrest getForrest() {
        return forrest;
    }

    public void setForrest(ClassForrest forrest) {
        if (forrest == null)
            throw new NullPointerException("forrest");
        this.forrest = forrest;
    }

    public ClassForrest forrest() {
        return forrest;
    }

    public ClassNameList forrest(ClassForrest forrest) {
        if (forrest == null)
            throw new NullPointerException("forrest");
        this.forrest = forrest;
        return this;
    }

    public List<Class<?>> resolveAll(ClassLoader classLoader, Set<String> failedNames) {
        if (classLoader == null)
            throw new NullPointerException("classLoader");

        List<Class<?>> classList = new ArrayList<>();
        for (String qName : this) {
            if (failedNames != null)
                if (failedNames.contains(qName))
                    continue;

            Class<?> clazz;
            try {
                clazz = Class.forName(qName, false, classLoader);
            } catch (ClassNotFoundException e) {
                logger.error("Failed to resolve class: " + qName);
                if (failedNames != null)
                    failedNames.add(qName);
                continue;
            } catch (NoClassDefFoundError e) {
                logger.error("Failed to resolve class: " + qName);
                if (failedNames != null)
                    failedNames.add(qName);
                continue;
            }
            classList.add(clazz);
        }
        return classList;
    }

    public ClassForrest analyze() {
        return analyze(null);
    }

    public ClassForrest analyze(Set<String> failedNames) {
        List<Class<?>> classes = resolveAll(classLoader, failedNames);
        forrest.parseAll(classes);
        return forrest;
    }

}
