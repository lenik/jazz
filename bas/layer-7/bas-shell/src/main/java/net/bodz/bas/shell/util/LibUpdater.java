package net.bodz.bas.shell.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

@ProgramName("lib-updater")
public class LibUpdater
        extends BasicCLI {

    ClassLoader loader = getClass().getClassLoader();

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        File libdir = new File("lib").getCanonicalFile();
        System.out.println(libdir);
        for (File file : URLClassLoaders.getLocalURLs(loader, 1)) {
            if (file.isFile()) {
                File dst = new File(libdir, file.getName());
                System.out.println("Copy " + file);
                Files.copy(file.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.out.println(file);
            }
        }
    }

    public static void main(String[] args)
            throws Exception {
        new LibUpdater().execute(args);
    }

}
