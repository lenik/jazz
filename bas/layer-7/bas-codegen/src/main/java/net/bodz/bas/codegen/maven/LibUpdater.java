package net.bodz.bas.codegen.maven;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.loader.URLClassLoaders;
import net.bodz.bas.c.m2.ArtifactId;
import net.bodz.bas.c.m2.LocalRepoDir;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.c.m2.MavenPomXml;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.codegen.util.MultiSourceUsage;
import net.bodz.bas.codegen.util.UsageMap;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

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
     *<br>
     * The workdir is searched in the project-dir mapping path.
     *
     * @option -m
     */
    boolean useProjectDirMap;

    /**
     * Override the project-dir mapping path. (default <code>$HOME/.m2/project-dir.map</code>)
     *<br>
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
     *<br>
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
    UsageMap<String> foundJars = new UsageMap<>();
    UsageMap<String> foundDirs = new UsageMap<>();

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


        // new ClassLoaderDumper().dump(Stdio.cout, loader);

        // Maven use classwords, so classpath won't work here.
//        String[] classpath = System.getProperty("java.class.path").split(":");

        for (Path item : URLClassLoaders.getUserClassPath(loader)) {
//            boolean inM2Repo = item.toString().contains(".m2/repository");
            boolean isJar = item.getFileName().toString().endsWith(".jar");

            System.err.println("ITEM: " + item);
            try {
                item = item.toRealPath();
            } catch (IOException e) {
                logger.error("Error find " + item + ": " + e.getMessage());
                continue;
            }

            final Path baseDir;
            final String relativePath;
            if (isJar) {
                // group*/artifact/ver/artifact-ver.jar
                Path verDir = item.getParent();
                Path artifactDir = verDir.getParent();
                Path group1Dir = artifactDir.getParent();

                Path repoDir = group1Dir.getParent();

                while (repoDir != null) {
                    Path fileName = repoDir.getFileName();
                    if (fileName == null)
                        break; // repoDir==/
                    if ("repository".equals(fileName.toString()))
                        break;
                    repoDir = repoDir.getParent();
                }
                if (repoDir == null) {
                    throw new IllegalUsageException("not in a repo dir: " + group1Dir + ", " + item);
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
            System.out.println("Found jars: ");
            foundJars.dump(Stdio.cout.indented());
            System.out.println("Found Dirs: ");
            foundDirs.dump(Stdio.cout.indented());

            for (String item : foundJars.keySet()) {
                MultiSourceUsage usages = foundJars.get(item);
                if (usages != null && !usages.isUsed()) {
                    Path path = jarDir.resolve(item);
                    println("Delete unused jar: " + path);
                    Files.delete(path);
                }
            }
            for (String item : foundDirs.keySet()) {
                MultiSourceUsage usages = foundJars.get(item);
                if (usages != null && !usages.isUsed()) {
                    Path dir = classDir.resolve(item);
                    println("Delete unused class dir: " + dir);
                    FileTree.delete(dir, TreeDeleteOption.DELETE_TREE);
                }
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
                try (Stream<Path> stream = Files.list(classDir)) {
                    stream.forEach(item -> {
                        if (Files.isDirectory(item))
                            foundDirs.add(item.getFileName().toString(), "dir-scan");
                    });
                }
                syncModules.addAll(foundDirs.keySet());
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
            if (Files.isDirectory(jarDir)) {
                try (Stream<Path> jars = Files.list(jarDir)) {
                    jars.forEach(item -> {
                        if (Files.isRegularFile(item))
                            foundJars.add(item.getFileName().toString(), "jar-scan");
                    });
                }
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
        boolean isJar = item.getFileName().toString().endsWith(".jar") && Files.isRegularFile(item);

        if (isJar) {
            Path projectDir = null;

            // get groupId from the path within repo.
            LocalRepoDir repoDir = LocalRepoDir.closest(item);
            if (repoDir != null) {
                ArtifactId itemId = repoDir.getQualifiedName(item);

                if (useProjectDirMap) {
                    String projectDirStr = projectDirMap.getDir(itemId, false);
                    if (projectDirStr != null) {
                        projectDir = Paths.get(projectDirStr);
//                        projectDir = projectDir.toRealPath();
                        // analyze pom to get output dir.
                        addDir(projectDir.resolve("target/classes"), itemId.artifactId);
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
        } // process classpath item

        String itemPath = item.toString();
        if (itemPath.endsWith("/target/classes")) {
            Path itemBaseDir = item.getParent().getParent();
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
        Path file = baseDir.resolve(relativePath);
        String baseName = file.getFileName().toString();

        if (jarDir != null) {
            Path dstFile = makeJar(jarDir, baseName, baseDir, relativePath);
            addClasspath(dstFile);
            foundJars.add(dstFile.getFileName().toString(), "list-add");

            if (includeSources)
                createOthers(jarDir, baseDir, relativePath, "-sources.jar");
            if (includeTests)
                createOthers(jarDir, baseDir, relativePath, "-tests.jar");
            if (includePoms)
                createOthers(jarDir, baseDir, relativePath, ".pom");
        }

        // no --outdir
        else {
            file = file.toRealPath();
            addClasspath(file);
        }
    }

    void addDir(Path srcDir, String dstName)
            throws IOException {
        final Path dstDir = classDir == null ? null : classDir.resolve(dstName);

        if (includeExistedOnly) {
            if (classDir != null && syncModules.contains(dstName)) {
                syncDirTree(srcDir, dstDir);
                addClasspath(dstDir);
                foundDirs.add(dstName, "list-add");
            }
            return;
        }

        if (dstDir != null) {
            if (useSymlinks) {
                createSymlink(classDir, dstName, srcDir.getParent(), srcDir.getFileName().toString());
                addClasspath(dstDir);
                foundDirs.add(dstName, "list-add-symlink");
                return;
            } else {
                // rsync
                syncDirTree(srcDir, dstDir);
                foundDirs.add(dstName, "list-add-sync");
            }
        }
        addClasspath(srcDir);
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

    void createOthers(Path destDir, Path srcBaseDir, String relativePath, String suffix)
            throws IOException {
        String relativeDir = FilePath.dirName(relativePath);
        String name = FilePath.nameOfPath(relativePath);
        String otherName = name + suffix;
        String otherRelativePath = relativeDir + "/" + otherName;

        Path srcFile = srcBaseDir.resolve(relativePath);
        Path srcOtherFile = srcFile.getParent().resolve(otherName);
        if (Files.exists(srcOtherFile)) {
            makeJar(destDir, otherName, srcBaseDir, otherRelativePath);
        }
    }

    Path makeJar(Path dir, String baseName, Path srcBaseDir, String srcRelativePath)
            throws IOException {
        if (useSymlinkToJars) {
            return createSymlink(jarDir, baseName, srcBaseDir, srcRelativePath);
        } else {
            return createCopy(jarDir, baseName, srcBaseDir, srcRelativePath);
        }
    }

    Path createCopy(Path destBaseDir, String destName, Path srcBaseDir, String srcRelativePath)
            throws IOException {
        Path srcFile = srcBaseDir.resolve(srcRelativePath);

        Path dstFile;
        if (mapDirStruct) {
            Path dstDir = destBaseDir.resolve(srcRelativePath).getParent();
            FileFn.mkdirs(dstDir);
            dstFile = dstDir.resolve(destName);
        } else {
            dstFile = destBaseDir.resolve(destName);
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

    Path createSymlink(Path linkBaseDir, String linkName, Path targetBaseDir, String targetRelativePath)
            throws IOException {
        Path targetFile = targetBaseDir.resolve(targetRelativePath);

        Path linkFile;
        if (mapDirStruct) {
            Path linkDir = linkBaseDir.resolve(targetRelativePath).getParent();
            FileFn.mkdirs(linkDir);
            linkFile = linkDir.resolve(linkName);
        } else {
            linkFile = linkBaseDir.resolve(linkName);
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
                try {
                    Files.delete(link);
                } catch (IOException e) {
                    println("        Error delete " + link + ": " + e.getMessage());
                }
            }
        }
        if (outdated) {
            println("    create symlink to: " + targetPath);
            try {
                Files.createSymbolicLink(link, targetPath);
            } catch (IOException e) {
                println("        Error create symlink " + link + e.getMessage());
            }
        }

        return linkFile;
    }

    void syncDirTree(Path src, Path dst)
            throws IOException {
        List<String> opts = new ArrayList<>();
        opts.add("rsync");
        opts.add("-am");
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
