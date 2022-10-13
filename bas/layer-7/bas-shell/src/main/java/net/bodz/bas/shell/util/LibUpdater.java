package net.bodz.bas.shell.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.m2.ArtifactId;
import net.bodz.bas.c.m2.LocalRepoDir;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenPomXml;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

@ProgramName("lib-updater")
public class LibUpdater
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(LibUpdater.class);

    ClassLoader loader = getClass().getClassLoader();

    /**
     * Use the existing project workdir instead of the external jar whenever possible.
     *
     * The workdir is searched in the project-dir mapping file.
     *
     * @option -m
     */
    boolean useProjectDirMap;

    /**
     * Override the project-dir mapping file. (default <code>$HOME/.m2/project-dir.map</code>)
     *
     * The mapping file lists the project id and the workdir each line. (e.g. `groupId:artifactId
     * path`.)
     *
     * @option --project-dirs =MAPFILE
     */
    File projectDirMapFile;
    ProjectDirMap projectDirMap;

    /**
     * The start dir to search the project. CWD by default.
     *
     * @option -C --chdir =PATH
     */
    File workDir = SysProps.userWorkDir;
    MavenPomDir workProject;
    String relativePathOrigin;

    /**
     * Use absolute paths for referenced projects. (implied --absolute-jars)
     *
     * @option -a --absolute-dirs
     */
    boolean absoluteDirs;

    /**
     * Use absolute paths for external jars.
     *
     * @option -A --absolute-jars
     */
    boolean absoluteJars;

    /**
     * Where to put the dependant jars and symlinks. (default don't create files)
     *
     * @option -O --outdir =PATH
     */
    String outDirName;
    File outLibDir;

    /**
     * Use symlinks to jars instead of copying.
     *
     * Enabled by default if --outdir isn't specified.
     *
     * @option -s --symlink
     */
    boolean useSymlinks;

    /**
     * Use installed jars instead of the `target/class` working dirs.
     *
     * @option -j --find-jars
     */
    boolean findJars;

    /**
     * Dump the dependencies in order.
     *
     * @option -o --list-file =FILE
     */
    String listFilename = "classpath.lst";
    List<String> classpathList = new ArrayList<>();

    /**
     * Module dirs in the sync-dir can be synchronized with dependency workdirs if available.
     * (default autocopy.d)
     *
     * @option --sync-dir =DIR
     */
    String syncDirName = "autocopy.d";
    File syncDir;
    Set<String> syncModules = new HashSet<String>();

    /**
     * Removed unused items in output directory. (default)
     *
     * @option --purge
     */
    boolean purgeUnused;

    /**
     * Keep unused items.
     *
     * @option --keep
     */
    boolean keepUnused;
    Set<String> unusedLibItems = new TreeSet<>();

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        setDefaults();

        for (File item : URLClassLoaders.getLocalURLs(loader, 1)) {
            processItem(item);
        }

        try (PrintStream listOut = new PrintStream(listFilename)) {
            for (String item : classpathList)
                listOut.println(item);
        }

        if (purgeUnused) {
            for (String item : unusedLibItems) {
                if (item.endsWith(".jar")) {
                    File file = new File(outLibDir, item);
                    println("Delete unused jar: " + file);
                    file.delete();
                }
            }
        }
    }

    void setDefaults()
            throws IOException {
        workProject = MavenPomDir.closest(workDir);
        if (workProject == null)
            throw new IllegalUsageException("not run inside a maven project.");

        relativePathOrigin = workProject.getBaseDir().getCanonicalPath() + "/";
        if (absoluteDirs)
            absoluteJars = true;

        if (useProjectDirMap) {
            if (projectDirMapFile == null)
                projectDirMapFile = new File(SysProps.userHome, ".m2/project-dir.map");
            if (projectDirMapFile.exists())
                projectDirMap.parseMapFile(projectDirMapFile);
        }

        if (outDirName == null)
            useSymlinks = true;

        File syncDir = new File(workProject.getBaseDir(), syncDirName);
        if (syncDir.exists()) {
            for (File child : syncDir.listFiles())
                if (child.isDirectory()) {
                    println("sync-module: " + child.getName());
                    syncModules.add(child.getName());
                }
            this.syncDir = syncDir;
        }

        if (outDirName != null) {
            outLibDir = new File(workProject.getBaseDir(), outDirName).getCanonicalFile();
            System.out.println("libDir: " + outLibDir);
            if (!outLibDir.exists()) {
                logger.debug("Create non-existing libdir: " + outLibDir);
                if (!outLibDir.mkdirs()) {
                    logger.error("Failed to mkdir: " + outLibDir);
                    System.exit(1);
                }
            }
        }

        if (outLibDir != null)
            unusedLibItems.addAll(Arrays.asList(outLibDir.list()));

        if (purgeUnused == keepUnused)
            if (keepUnused)
                purgeUnused = false;
            else
                purgeUnused = true;
    }

    void processItem(File item)
            throws IOException, ParseException {
        boolean resolved = false;

        if (item.isFile()) {
            File itemProjectDir = null;

            // get groupId from the path within repo.
            LocalRepoDir repoDir = LocalRepoDir.closest(item);
            if (repoDir != null) {
                ArtifactId itemId = repoDir.getQualifiedName(item);

                if (useProjectDirMap) {
                    String dir = projectDirMap.getDir(itemId);
                    if (dir != null) {
                        itemProjectDir = new File(dir);
                        addDir(new File(itemProjectDir, "target/classes"), itemId.artifactId);
                        // testing scope?..
                        resolved = true;
                    }
                }
            }

            if (!resolved) {
                addFile(item);
                resolved = true;
            }
            return;
        } // file item

        String itemPath = item.getPath();
        if (itemPath.endsWith("/target/classes")) {
            File itemBaseDir = item.getParentFile().getParentFile();
            // MavenPomDir.closest(item);
            MavenPomDir pomDir = new MavenPomDir(itemBaseDir);
            if (workProject.equals(pomDir)) {
                println("Ignore self: " + item);
                return;
            }

            ArtifactId itemId;
            try {
                MavenPomXml xml = MavenPomXml.open(pomDir.getPomFile());
                itemId = xml.getId();
            } catch (XPathExpressionException | SAXException e) {
                throw new ParseException("error parse pom: " + e.getMessage(), e);
            }

            if (findJars) {
                String jarPath = itemId.groupId.replace('.', '/') + "/" + itemId.artifactId + "/" + itemId.version;
                String jarName = itemId.artifactId + "-" + itemId.version + ".jar";
                jarPath += "/" + jarName;
                jarPath = SysProps.userHome + "/.m2/repository/" + jarPath;
                File jarFile = new File(jarPath);
                if (jarFile.isFile()) {
                    println("Found jar: " + jarFile);
                    addFile(jarFile);
                    return;
                }
            }

            addDir(item, itemId.artifactId);
        } // =~ /target/classes$
    }

    void addFile(File src)
            throws IOException {
        String dstName = src.getName();
        if (outLibDir != null) {
            File dst = new File(outLibDir, dstName);

            if (useSymlinks) {
                boolean outdated = true;
                if (dst.exists()) {
                    Path old = Files.readSymbolicLink(dst.toPath());
                    if (old.equals(src.toPath()))
                        outdated = false;
                    else {
                        println("outdated symlink target: " + old);
                        Files.delete(dst.toPath());
                    }
                }
                if (outdated) {
                    println("create symlink to: " + src);
                    Files.createSymbolicLink(dst.toPath(), src.toPath());
                }
            }

            else {
                boolean outdated = true;
                if (dst.exists()) {
                    long srcVer = src.lastModified();
                    long dstVer = dst.lastModified();
                    if (dstVer >= srcVer)
                        outdated = false;
                }

                if (outdated) {
                    println("copy file: " + src);
                    Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            unusedLibItems.remove(dstName);
            addClasspath(dst);
        } // outDir != null

        else {
            addClasspath(src);
        } // outDir == null
    }

    void addDir(File src, String dstName)
            throws IOException {
        if (syncDir != null) {
            if (syncModules.contains(dstName)) {
                println("sync dir: " + dstName + " <- " + src);
                File dst = new File(syncDir, dstName);
                Processes.exec("rsync", "-amv", "--delete", src.getPath(), dst.getPath());
                addClasspath(dst);
            }
            return;
        }

        addClasspath(src);
    }

    void addClasspath(File file)
            throws IOException {
        String path = file.getCanonicalPath();
        boolean absolutePath = file.isDirectory() ? absoluteDirs : absoluteJars;
        if (absolutePath) {
            if (path.startsWith(relativePathOrigin))
                path = path.substring(relativePathOrigin.length());
        } else {
            path = FilePath.getRelativePath(path, relativePathOrigin);
        }
        classpathList.add(path);
    }

    void println(String message) {
        logger.info(message);
        System.out.println(message);
    }

    public static void main(String[] args)
            throws Exception {
        new LibUpdater().execute(args);
    }

}
