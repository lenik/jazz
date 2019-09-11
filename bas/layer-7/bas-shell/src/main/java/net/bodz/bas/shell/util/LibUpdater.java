package net.bodz.bas.shell.util;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.m2.ArtifactId;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenPomXml;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;

@ProgramName("lib-updater")
public class LibUpdater {

    static final Logger logger = LoggerFactory.getLogger(LibUpdater.class);

    ClassLoader loader = getClass().getClassLoader();
    MavenPomDir project;

    Set<String> unpacks = new HashSet<String>();

    public LibUpdater() {
        project = MavenPomDir.closest(SysProps.userWorkDir);
        if (project == null)
            throw new IllegalUsageException("not run inside a maven project.");

        File dir = new File(project.getBaseDir(), "classes");
        if (dir.exists())
            for (File child : dir.listFiles())
                if (child.isDirectory()) {
                    System.out.println("Should-Unpack: " + child.getName());
                    unpacks.add(child.getName());
                }
    }

    protected void execute(String... args)
            throws Exception {
        File libdir = new File("lib").getCanonicalFile();
        System.out.println("libdir: " + libdir);
        if (libdir.exists()) {
            logger.debug("Create non-existing libdir: " + libdir);
            if (!libdir.mkdirs()) {
                logger.error("Failed to mkdir.");
                System.exit(1);
            }
        }

        File orders = new File("order.lst");
        PrintStream out = new PrintStream(orders);

        try {
            for (File file : URLClassLoaders.getLocalURLs(loader, 1)) {
                String path = file.getPath();

                if (file.isFile()) {
                    if (unpacks.contains(file.getName())) {
                        // How to know the source dir of a dependency jar?
                    }
                    System.out.println("Copy-Jar: " + file);
                    File dst = new File(libdir, file.getName());
                    Files.copy(file.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    out.println("lib/" + file.getName());
                    continue;
                }

                if (path.endsWith("/target/classes")) {
                    File basedir = file.getParentFile().getParentFile();
                    MavenPomDir pomDir = new MavenPomDir(basedir);
                    if (project.equals(pomDir)) {
                        System.out.println("Ignore: " + file);
                        continue;
                    }

                    MavenPomXml xml = MavenPomXml.open(pomDir.getPomFile());
                    ArtifactId artifact = xml.getId();

                    if (unpacks.contains(artifact.artifactId)) {
                        System.out.println("Copy-Unpacked: " + file);
                        Processes.exec("cp", "-aT", path, project.getBaseDir() + "/classes/" + artifact.artifactId);
                        out.println("classes/" + artifact.artifactId);
                        continue;
                    }

                    path = artifact.groupId.replace('.', '/') + "/" + artifact.artifactId + "/" + artifact.version;
                    path += "/" + artifact.artifactId + "-" + artifact.version + ".jar";
                    path = SysProps.userHome + "/.m2/repository/" + path;
                    file = new File(path);
                    if (file.isFile()) {
                        System.out.println("Copy-Packed: " + file);
                        File dst = new File(libdir, file.getName());
                        Files.copy(file.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        out.println("lib/" + file.getName());
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
