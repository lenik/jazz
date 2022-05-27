package net.bodz.bas.shell.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.m2.ArtifactId;
import net.bodz.bas.c.m2.LocalRepoDir;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenPomXml;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;

@ProgramName("lib-updater")
public class LibUpdater {

    static final Logger logger = LoggerFactory.getLogger(LibUpdater.class);

    ClassLoader loader = getClass().getClassLoader();
    MavenPomDir project;

    Set<String> unpacks = new HashSet<String>();

    public LibUpdater()
            throws IOException {
        project = MavenPomDir.closest(SysProps.userWorkDir);
        if (project == null)
            throw new IllegalUsageException("not run inside a maven project.");

        String mapFile = SysProps.userHome + "/.m2/map";
        parseMapFile(mapFile);

        File dir = new File(project.getBaseDir(), "classes");
        if (dir.exists())
            for (File child : dir.listFiles())
                if (child.isDirectory()) {
                    System.out.println("Should-Unpack: " + child.getName());
                    unpacks.add(child.getName());
                }
    }

    Map<String, String> qNameWsMap = new HashMap<>();
    Map<String, String> artifactIdWsMap = new HashMap<>();

    void parseMapFile(String fileName)
            throws IOException {
        logger.info("parse map file " + fileName);
        FileResource mapFile = new FileResource(fileName);
        if (!mapFile.getFile().exists())
            return;

        for (String line : mapFile.read().readLines()) {
            line = line.trim();
            if (line.startsWith("#"))
                continue;
            if (line.isEmpty())
                continue;
            int pos = line.indexOf(' ');
            if (pos == -1) {
                pos = line.indexOf('\t');
                if (pos == -1)
                    continue; // invalid line
            }
            String qName = line.substring(0, pos).trim();
            String path = line.substring(pos + 1).trim();
            qNameWsMap.put(qName, path);

            int colon = qName.indexOf(':');
            if (colon == -1)
                continue; // not a qualified name.

            // String groupId = qName.substring(0, colon);
            String artifactId = qName.substring(colon + 1);
            artifactIdWsMap.put(artifactId, path);
        }
    }

    protected void execute(String... args)
            throws Exception {
        File libdir = new File(project.getBaseDir(), "lib").getCanonicalFile();
        String hrefRef = project.getBaseDir().getPath() + "/";

        System.out.println("libdir: " + libdir);
        if (!libdir.exists()) {
            logger.debug("Create non-existing libdir: " + libdir);
            if (!libdir.mkdirs()) {
                logger.error("Failed to mkdir: " + libdir);
                System.exit(1);
            }
        }

        OrderListing orderListing = new OrderListing();

        try {
            for (File file : URLClassLoaders.getLocalURLs(loader, 1)) {
                String path = file.getPath();

                if (file.isFile()) {
                    if (unpacks.contains(file.getName())) {
                        // How to know the source dir of a dependency jar?
                    }

                    String wsDir = null;
                    LocalRepoDir repoDir = LocalRepoDir.closest(file);
                    if (repoDir != null) {
                        ArtifactId id = repoDir.getQualifiedName(file);
                        String qName = id.groupId + ":" + id.artifactId;
                        wsDir = qNameWsMap.get(qName);
                        if (wsDir == null)
                            wsDir = artifactIdWsMap.get(id.artifactId);
                        if (wsDir != null) {
                            // there's workspace corresponding
                            wsDir = new File(wsDir).getCanonicalPath();
                            String href = FilePath.getRelativePath(wsDir, hrefRef);
                            orderListing.addDebug(href + "/target/classes");
                        }
                    }

                    File dst = new File(libdir, file.getName());
                    boolean isLatest = false;

                    do {
                        if (!dst.exists())
                            break;

                        long srcVer = file.lastModified();
                        long dstVer = dst.lastModified();
                        if (srcVer >= dstVer)
                            break;

                        isLatest = true;
                    } while (false);

                    if (!isLatest) {
                        System.out.println("Copy-Jar: " + file);
                        Files.copy(file.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                    if (wsDir != null)
                        orderListing.addRelease("lib/" + file.getName());
                    else
                        orderListing.add("lib/" + file.getName());

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
                        orderListing.add("classes/" + artifact.artifactId);
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
                        orderListing.add("lib/" + file.getName());
                        continue;
                    }
                }
            }
        } finally {
            orderListing.close();
        }
    }

    public static void main(String[] args)
            throws Exception {
        new LibUpdater().execute(args);
    }

}
