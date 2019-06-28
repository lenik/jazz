package net.bodz.bas.shell.util;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.m2.ArtifactId;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenPomXml;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.meta.build.ProgramName;

@ProgramName("lib-updater")
public class LibUpdater {

    ClassLoader loader = getClass().getClassLoader();

    protected void execute(String... args)
            throws Exception {
        File libdir = new File("lib").getCanonicalFile();
        System.out.println(libdir);

        File orders = new File("order.lst");
        PrintStream out = new PrintStream(orders);

        try {
            for (File file : URLClassLoaders.getLocalURLs(loader, 1)) {
                String path = file.getPath();

                if (file.isFile()) {
                    System.out.println("Copy dependency: " + file);
                    File dst = new File(libdir, file.getName());
                    Files.copy(file.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    out.println(file.getName());
                    continue;
                }

                if (path.endsWith("/target/classes")) {
                    File basedir = file.getParentFile().getParentFile();
                    MavenPomDir pomDir = new MavenPomDir(basedir);
                    MavenPomXml xml = MavenPomXml.open(pomDir.getPomFile());
                    ArtifactId artifact = xml.getId();
                    path = artifact.groupId.replace('.', '/') + "/" + artifact.artifactId + "/" + artifact.version;
                    path += "/" + artifact.artifactId + "-" + artifact.version + ".jar";
                    path = SysProps.userHome + "/.m2/repository/" + path;
                    file = new File(path);
                    if (file.isFile()) {
                        System.out.println("Copy from installed: " + file);
                        File dst = new File(libdir, file.getName());
                        Files.copy(file.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        out.println(file.getName());
                        continue;
                    }
                }
            }
        } finally {
            out.close();
        }
    }

    public static void main(String[] args)
            throws Exception {
        new LibUpdater().execute(args);
    }

}
