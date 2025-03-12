package net.bodz.uni.shelj.codegen.maven;

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
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
import net.bodz.bas.c.java.nio.file.FileFn;
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
import net.bodz.bas.t.tuple.Split;

/**
 * Lib updater
 *
 * @author Lenik
 * @version 1.0
 */
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
     * The workdir is searched in the project-dir mapping path.
     *
     * @option -m
     */
    boolean useProjectDirMap;

    /**
     * Override the project-dir mapping path. (default <code>$HOME/.m2/project-dir.map</code>)
     *
     * The mapping path lists the project id and the workdir each line. (e.g. `groupId:artifactId
     * path`.)
     *
     * @option --project-dirs =MAPFILE
     */
    Path projectDirMapFile;
    ProjectDirMap projectDirMap = new ProjectDirMap();

    /**
     * The start dir to search the project. CWD by default.
     *
     * @option -C --chdir =PATH
     */
    Path workDir = SysProps.userWorkDir;
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
    Path jarDir;

    /**
     * Where to copy classpath dirs, recommend modules/.
     *
     * @option -D --classdir =DIRNAME
     */
    String classDirName;
    Path classDir;

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
     * @option -o --list-path =PATH
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

    /**
     * Preserve maven directory structure in the destination dir.
     *
     * @option -M --map-dirstruct
     */
    boolean mapDirStruct;

    /**
     * Include sources, test jars, and the pom.xml.
     *
     * @option -J
     */
    boolean includeOthers;

    /** @option */
    boolean includeSources;

    /** @option */
    boolean includeTests;

    /** @option */
    boolean includePoms;

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (!setDefaults())
            _exit(1);

        for (Path item : URLClassLoaders.getUserClassPath(loader)) {
            item = FilePath.canoniOf(item);
            // boolean inM2Repo = item.getPath().contains("m2/repository");
            boolean isJar = item.getFileName().toString().endsWith(".jar");

            final Path baseDir;
            final String relativePath;
            if (isJar) {
                // group/artifact/ver/artifact-ver.jar
                Path verDir = item.getParent();
                Path artifactDir = verDir.getParent();
                Path groupDir = artifactDir.getParent();

                Path repoDir = groupDir.getParent();
                while (repoDir != null && !repoDir.getFileName().toString().equals("repository"))
                    repoDir = repoDir.getParent();
                if (repoDir == null) {
                    throw new IllegalUsageException("not in a repo dir: " + groupDir + ", " + item);
                }
                baseDir = repoDir;

                String baseDirPath = baseDir.toString();
                String itemPath = item.toString();
                relativePath = itemPath.substring(baseDirPath.length() + 1);
            } else {
                baseDir = item.getParent();
                relativePath = item.getFileName().toString();
            }

            processItem(baseDir, relativePath);
        }

        try (PrintStream listOut = new PrintStream(listFilename)) {
            for (String item : classpathList) {
                listOut.println(item);
//                System.out.println(item);
            }
        }

        if (removeUnused) {
            for (String item : unusedJars) {
                Path path = jarDir.resolve(item);
                println("Delete unused jar: " + path);
                Files.delete(path);
            }
            for (String item : unusedDirs) {
                Path dir = classDir.resolve(item);
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

        relativePathOrigin = workProject.getBaseDir().toAbsolutePath().normalize() + "/";
        if (absoluteDirs)
            absoluteJars = true;

        if (useProjectDirMap) {
            if (projectDirMapFile == null)
                projectDirMapFile = SysProps.userHome.resolve(".m2/project-dir.map");
            if (Files.exists(projectDirMapFile)) {
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
            classDir = workProject.getBaseDir().resolve(classDirName);
            classDir = classDir.toAbsolutePath().normalize();
            if (Files.exists(classDir)) {
                Files.list(classDir).forEach(item -> {
                    if (Files.isDirectory(item))
                        unusedDirs.add(item.getFileName().toString());
                });
                syncModules.addAll(unusedDirs);
            } else {
                logger.debug("create non-existing classdir: " + classDir);
                if (!FileFn.mkdirs(classDir)) {
                    logger.error("error mkdir: " + classDir);
                    return false;
                }
            }
        }

        if (jarDirName != null) {
            jarDir = workProject.getBaseDir().resolve(jarDirName);
            jarDir = FilePath.canoniOf(jarDir);
            if (Files.isDirectory(jarDir)) {
                Files.list(jarDir).forEach(item -> {
                    if (Files.isRegularFile(item))
                        unusedJars.add(item.getFileName().toString());
                });
            } else {
                logger.debug("create non-existing jardir: " + jarDir);
                if (!FileFn.mkdirs(jarDir)) {
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

        if (includeOthers) {
            includeSources = true;
            includeTests = true;
            includePoms = true;
        }
        return true;
    }

    void processItem(Path baseDir, String relativePath)
            throws IOException, ParseException {
        Path item = baseDir.resolve(relativePath);
        boolean resolved = false;

        if (Files.isRegularFile(item)) {
            Path itemProjectDir = null;

            // get groupId from the path within repo.
            LocalRepoDir repoDir = LocalRepoDir.closest(item);
            if (repoDir != null) {
                ArtifactId itemId = repoDir.getQualifiedName(item);

                if (useProjectDirMap) {
                    String dir = projectDirMap.getDir(itemId, false);
                    if (dir != null) {
                        itemProjectDir = Paths.get(dir);
                        itemProjectDir = FilePath.canoniOf(itemProjectDir);
                        // analyze pom to get output dir.
                        addDir(itemProjectDir.resolve("target/classes"), itemId.artifactId);
                        // testing scope?..
                        resolved = true;
                    }
                }
            }

            if (!resolved) {
                addFile(baseDir, relativePath);
                resolved = true;
            }
            return;
        } // path item

        String itemPath = item.toString();
        if (itemPath.endsWith("/target/classes")) {
            Path itemBaseDir = item.getParent();
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
                String groupDir = itemId.groupId.replace('.', '/') + "/" + itemId.artifactId + "/" + itemId.version;
                String jarName = itemId.artifactId + "-" + itemId.version + ".jar";
                String jarRelativePath = groupDir + "/" + jarName;
                Path jarBaseDir = SysProps.userHome.resolve(".m2/repository/");
                Path jarFile = jarBaseDir.resolve(jarRelativePath);
                if (Files.isRegularFile(jarFile)) {
                    logger.debug("dir2jar: " + jarFile);
                    addFile(jarBaseDir, jarRelativePath);
                    return;
                }
            }

            addDir(item, itemId.artifactId);
        } // =~ /target/classes$
    }

    void addFile(Path baseDir, String relativePath)
            throws IOException {
        Path src = baseDir.resolve(relativePath);
        Path srcCanonical = FilePath.canoniOf(src);

        String dstName = src.getFileName().toString();
        if (jarDir != null) {
            Path dstFile = createJar(jarDir, dstName, baseDir, relativePath);
            addClasspath(dstFile);
            unusedJars.remove(dstFile.getFileName().toString());

            if (includeSources)
                createOthers(jarDir, dstName, baseDir, relativePath, "-sources.jar");
            if (includeTests)
                createOthers(jarDir, dstName, baseDir, relativePath, "-tests.jar");
            if (includePoms)
                createOthers(jarDir, dstName, baseDir, relativePath, ".pom");
        }

        // no --outdir
        else {
            addClasspath(srcCanonical);
        }
    }

    void addDir(Path src, String dstName)
            throws IOException {
        final Path dst = classDir == null ? null : classDir.resolve(dstName);

        if (includeExistedOnly) {
            if (classDir != null && syncModules.contains(dstName)) {
                syncDirTree(src, dst);
                unusedDirs.remove(dst.getFileName().toString());
                addClasspath(dst);
            }
            return;
        }

        if (dst != null) {
            if (useSymlinks) {
                createSymlink(classDir, dstName, src.getParent(), src.getFileName().toString());
                addClasspath(dst);
                return;
            } else {
                // rsync
                syncDirTree(src, dst);
            }
            unusedDirs.remove(dst.getFileName().toString());
        }

        addClasspath(src);
    }

    void addClasspath(Path _path)
            throws IOException {
        String path = _path.toAbsolutePath().toString();
        boolean useAbsoluteForm;
        if (Files.isDirectory(_path))
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

    void createOthers(Path dir, String baseName, Path srcBaseDir, String srcRelativePath, String suffix)
            throws IOException {
        Split dirBase = Split.dirBase(srcRelativePath);
        Split nameExtension = Split.nameExtension(dirBase.b);
        String otherName = nameExtension.a + suffix;

        Path src = srcBaseDir.resolve(srcRelativePath);
        Path otherFile = src.getParent().resolve(otherName);
        if (Files.exists(otherFile)) {
            String otherRelativePath = dirBase.a + "/" + otherName;
            createJar(jarDir, otherName, srcBaseDir, otherRelativePath);
        }
    }

    Path createJar(Path dir, String baseName, Path srcBaseDir, String srcRelativePath)
            throws IOException {
        if (useSymlinkToJars) {
            return createSymlink(jarDir, baseName, srcBaseDir, srcRelativePath);
        } else {
            return createCopy(jarDir, baseName, srcBaseDir, srcRelativePath);
        }
    }

    Path createCopy(Path dir, String baseName, Path srcBaseDir, String srcRelativePath)
            throws IOException {
        Path srcFile = srcBaseDir.resolve(srcRelativePath);

        Path dstFile;
        if (mapDirStruct) {
            Path dstDir = dir.resolve(srcRelativePath).getParent();
            FileFn.mkdirs(dstDir);
            dstFile = dstDir.resolve(baseName);
        } else {
            dstFile = dir.resolve(baseName);
        }

        boolean outdated = true;
        if (Files.exists(dstFile)) {
            long srcVer = Files.getLastModifiedTime(srcFile).toMillis();
            long dstVer = Files.getLastModifiedTime(dstFile).toMillis();
            if (dstVer >= srcVer)
                outdated = false;
        }

        if (outdated) {
            println("copy path: " + srcFile);
            Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return dstFile;
    }

    Path createSymlink(Path dir, String baseName, Path targetBaseDir, String targetRelativePath)
            throws IOException {
        Path targetFile = targetBaseDir.resolve(targetRelativePath);

        Path linkFile;
        if (mapDirStruct) {
            Path linkDir = dir.resolve(targetRelativePath).getParent();
            FileFn.mkdirs(linkDir);
            linkFile = linkDir.resolve(baseName);
        } else {
            linkFile = dir.resolve(baseName);
        }

        Path link = linkFile; // .toPath();
        String target;

        boolean useAbsolute;
        if (Files.isDirectory(targetFile))
            useAbsolute = absoluteDirs;
        else
            useAbsolute = absoluteJars;

        if (useAbsolute)
            target = targetFile.toAbsolutePath().toString();
        else
            target = FilePath.getRelativePath(targetFile, linkFile);

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

        return linkFile;
    }

    void syncDirTree(Path src, Path dst)
            throws IOException {
        List<String> opts = new ArrayList<>();
        opts.addAll(Arrays.asList("rsync", "-am"));
        opts.add("-v");
        if (removeUnused)
            opts.add("--delete");
        opts.add(src.toString());
        opts.add(dst.toString());
        String[] args = opts.toArray(new String[0]);
        new ProcessBuilder().command(args).start();
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
