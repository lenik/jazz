package net.bodz.bas.uber;

import java.io.File;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ClassHashesCollector
        extends NetBodzTypeCollector<Object> {

    static Logger logger = LoggerFactory.getLogger(ClassHashesCollector.class);

    public ClassHashesCollector() {
        super(Object.class, null);
    }

    @Override
    protected void createFiles() {
        for (Class<?> clazz : listFilteredDerivations(Object.class)) {
            if (!clazz.getName().startsWith("net.bodz."))
                continue;

            MavenPomDir pomDir = MavenPomDir.fromClass(clazz);
            if (pomDir == null)
                continue;

            String name = pomDir.getName();
            System.out.println("POM = " + name + "   ---   " + clazz);

            File srcdir = pomDir.getSourceDir(clazz);
            if (srcdir == null)
                continue;

        }
    }

    public static void main(String[] args)
            throws Exception {
        new ClassHashesCollector().collect();
    }

}
