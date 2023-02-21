package net.bodz.bas.shell.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
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

    public static final String RECOMMEND_JARDIR = "lib";
    public static final String RECOMMEND_CLASSDIR = "modules";

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
     * The mapping file lists the project id and the workdir each line. (e.g. `groupId:artifactId path`.)
     *
     * @option --project-dirs =MAPFILE
     */
    File projectDirMapFile;
    ProjectDirMap projectDirMap = new ProjectDirMap();

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
     * @option -A --absolute-dirs
     */
    boolean absoluteDirs;

    /**
     * Use absolute paths for external jars.
     *
     * @option -a --absolute-jars
     */
    boolean absoluteJars;

    /**
     * Where to put the dependent jars, recommend lib/. (default don't create files)
     *
     * @option -d --jardir =DIRNAME
     */
    String jarDirName;
    File jarDir;

    /**
     * Where to copy classpath dirs, recommend modules/.
     *
     * @option -D --classdir =DIRNAME
     */
    String classDirName;
    File classDir;

    /**
     * Only synchronize modules (subdirs) already existed in the --classdir.
     *
     * @option -i --include-existed-only
     */
    boolean includeExistedOnly;
    Set<String> syncModules = new HashSet<String>();

    /**
     * Create symlink to dirs instead of recursive copying. (implied --symlink-jars)
     *
     * @option -s --symlink
     */
    boolean useSymlinks;

    /**
     * Use symlink to jar instead of copying.
     *
     * Enabled by default if --outdir isn't specified.
     *
     * @option -S --symlink-jars
     */
    boolean useSymlinkToJars;

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
     * Removed unused items in output directory. (default)
     *
     * @option -c --clean
     */
    boolean removeUnused;

    /**
     * Keep unused items.
     *
     * @option -k --keep
     */
    boolean keepUnused;
    Set<String> unusedJars = new TreeSet<>();
    Set<String> unusedDirs = new TreeSet<>();

    /**
     * Overwrite existing items.
     *
     * @option -f --force
     */
    boolean forceMode;

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (!setDefaults())
            _exit(1);

        for (File item : URLClassLoaders.getUserClassPath(loader)) {
            processItem(item);
        }

        try (PrintStream listOut = new PrintStream(listFilename)) {
            for (String item : classpathList) {
                listOut.println(item);
//                System.out.println(item);
            }
        }

        if (removeUnused) {
            for (String item : unusedJars) {
                File file = new File(jarDir, item);
                println("Delete unused jar: " + file);
                file.delete();
            }
            for (String item : unusedDirs) {
                File dir = new File(classDir, item);
                println("Delete unused class dir: " + dir);
                FileTree.delete(dir, TreeDeleteOption.DELETE_TREE);
            }
        }
    }

    boolean setDefaults()
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
            if (projectDirMapFile.exists()) {
                projectDirMap.parseMapFile(projectDirMapFile);
            }
        }

        if (jarDirName == null)
            useSymlinkToJars = true;

        if (useSymlinks)
            useSymlinkToJars = true;

        if (classDirName == null)
            useSymlinks = true;
        else {
            classDir = new File(workProject.getBaseDir(), classDirName);
            classDir = classDir.getCanonicalFile();
            if (classDir.exists()) {
                File[] modules = classDir.listFiles();
                if (modules != null) {
                    for (File item : modules)
                        if (item.isDirectory())
                            unusedDirs.add(item.getName());
                    syncModules.addAll(unusedDirs);
                }
            } else {
                logger.debug("create non-existing classdir: " + classDir);
                if (!classDir.mkdirs()) {
                    logger.error("error mkdir: " + classDir);
                    return false;
                }
            }
        }

        if (jarDirName != null) {
            jarDir = new File(workProject.getBaseDir(), jarDirName);
            jarDir = jarDir.getCanonicalFile();
            if (jarDir.exists()) {
                File[] jars = jarDir.listFiles();
                if (jars != null)
                    for (File item : jars)
                        if (item.isFile())
                            unusedJars.add(item.getName());
            } else {
                logger.debug("create non-existing jardir: " + jarDir);
                if (!jarDir.mkdirs()) {
                    logger.error("error mkdir: " + jarDir);
                    return false;
                }
            }
        }

        if (removeUnused == keepUnused)
            if (keepUnused)
                removeUnused = false;
            else
                removeUnused = true;

        return true;
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
                    String dir = projectDirMap.getDir(itemId, false);
                    if (dir != null) {
                        itemProjectDir = new File(dir);
                        itemProjectDir = itemProjectDir.getCanonicalFile();
                        // analyze pom to get output dir.
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
                logger.debug("ignored current working project: " + item);
                return;
            }

            ArtifactId itemId;
            try {
                MavenPomXml xml = MavenPomXml.open(pomDir.getPomFile());
                itemId = xml.getId();
                // System.err.println("ITEM-ID: "+itemId);
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
                    logger.debug("dir2jar: " + jarFile);
                    addFile(jarFile);
                    return;
                }
            }

            addDir(item, itemId.artifactId);
        } // =~ /target/classes$
    }

    void addFile(File src)
            throws IOException {
        src = src.getCanonicalFile();
        String dstName = src.getName();
        if (jarDir != null) {
            File dst = new File(jarDir, dstName);

            if (useSymlinkToJars)
                createSymlink(dst, src);

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

            addClasspath(dst);
            unusedJars.remove(dst.getName());
        }

        // no --outdir
        else {
            addClasspath(src);
        }
    }

    void addDir(File src, String dstName)
            throws IOException {
        File dst = null;
        if (classDir != null)
            dst = new File(classDir, dstName);

        if (includeExistedOnly) {
            if (classDir != null && syncModules.contains(dstName)) {
                syncDirTree(src, dst);
                unusedDirs.remove(dst.getName());
                addClasspath(dst);
            }
            return;
        }

        if (dst != null) {
            if (useSymlinks) {
                createSymlink(dst, src);
                addClasspath(dst);
                return;
            } else {
                // rsync
                syncDirTree(src, dst);
            }
            unusedDirs.remove(dst.getName());
        }

        addClasspath(src);
    }

    void addClasspath(File file)
            throws IOException {
        String path = file.getAbsolutePath();
        boolean useAbsoluteForm;
        if (file.isDirectory())
            useAbsoluteForm = absoluteDirs;
        else
            useAbsoluteForm = absoluteJars;

        if (useAbsoluteForm) {
            if (path.startsWith(relativePathOrigin))
                path = path.substring(relativePathOrigin.length());
        } else {
            path = FilePath.getRelativePath(path, relativePathOrigin);
        }

        classpathList.add(path);
    }

    void createSymlink(File linkFile, File targetFile)
            throws IOException {
        Path link = linkFile.toPath();
        String target;

        boolean useAbsolute;
        if (targetFile.isDirectory())
            useAbsolute = absoluteDirs;
        else
            useAbsolute = absoluteJars;

        if (useAbsolute)
            target = targetFile.getAbsolutePath();
        else
            target = FilePath.getRelativePath(targetFile.getPath(), linkFile.getPath());

        Path targetPath = Paths.get(target);

        boolean outdated = true;
        if (Files.exists(link, LinkOption.NOFOLLOW_LINKS)) {
            if (Files.isSymbolicLink(link)) {
                Path old = Files.readSymbolicLink(link);
                if (old.equals(targetPath))
                    outdated = false;
                else
                    println("    -- outdated symlink target: " + old);
            } else
                println("    -- remove non-symlink: " + link);

            if (outdated) {
                Files.delete(link);
            }
        }
        if (outdated) {
            println("    create symlink to: " + targetPath);
            try {
                Files.createSymbolicLink(link, targetPath);
            } catch (IOException e) {
                throw e;
            }
        }
    }

    void syncDirTree(File src, File dst)
            throws IOException {
        List<String> opts = new ArrayList<>();
        opts.addAll(Arrays.asList("rsync", "-am"));
        opts.add("-v");
        if (removeUnused)
            opts.add("--delete");
        opts.add(src.getPath());
        opts.add(dst.getPath());
        String[] args = opts.toArray(new String[0]);
        Processes.exec(args);
    }

    void println(String message) {
        // logger.info(message);
        System.out.println(message);
    }

    public static void main(String[] args)
            throws Exception {
        new LibUpdater().execute(args);
    }

}
