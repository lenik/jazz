package net.bodz.bas.script.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;

public class FileSearcher {

    static final Logger logger = LoggerFactory.getLogger(FileSearcher.class);

    List<Path> dirs = new ArrayList<>();

    static final String pathSeparator = SystemProperties.getPathSeparator();

    public int addPathEnv(String env) {
        return addPathEnv(env, null);
    }

    public int addPathEnv(String env, String dirPrefix) {
        StringTokenizer tokens = new StringTokenizer(env, pathSeparator);
        int count = 0;
        while (tokens.hasMoreTokens()) {
            String dir = tokens.nextToken();
            if (dirPrefix != null)
                dir += dirPrefix;
            if (addSearchDir(dir))
                count++;
        }
        return count;
    }

    public int addPathDir(File pathDir)
            throws IOException {
        int count = 0;
        if (pathDir.isDirectory())
            for (File child : pathDir.listFiles()) {
                if (child.isFile()) {
                    count += addPathFile(child);
                }
            }
        return count;
    }

    public int addPathFile(String pathPath)
            throws IOException {
        File file = new File(pathPath);
        return addPathFile(file);
    }

    public int addPathFile(File pathFile)
            throws IOException {
        int count = 0;
        if (!pathFile.isFile())
            return 0;

        LineReader reader = ResFn.file(pathFile).newLineReader();
        String line;
        while ((line = reader.readLine()) != null) {
            String dir = line.trim();
            if (dir.isEmpty())
                continue;
            if (dir.startsWith("#"))
                continue;
            File df = new File(dir);
            if (!df.isDirectory()) {
                logger.error("skipped non-dir entry: " + dir);
                continue;
            }
            if (addSearchDir(df))
                count++;
        }
        reader.close();
        return count;
    }

    public boolean addSearchDir(@NotNull String dir) {
        return addSearchDir(Paths.get(dir));
    }

    public boolean addSearchDir(@NotNull File dir) {
        return addSearchDir(dir.toPath());
    }

    public boolean addSearchDir(@NotNull Path dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        return dirs.add(dir);
    }

    public Set<Path> search(String name, String... extraDirs) {
        List<Path> extraDirsList = null;
        if (extraDirs != null && extraDirs.length > 0) {
            extraDirsList = new ArrayList<>();
            for (String d : extraDirs) {
                Path dir = Paths.get(d);
                extraDirsList.add(dir);
            }
        }
        return search(name, extraDirsList);
    }

    public Set<Path> search(String name, List<Path> extraDirs) {
        Set<Path> hits = new LinkedHashSet<>();
        for (Path dir : dirs) {
            Path file = dir.resolve(name);
            if (Files.exists(file))
                hits.add(file);
        }
        if (extraDirs != null)
            for (Path dir : extraDirs) {
                Path file = dir.resolve(name);
                if (Files.exists(file))
                    hits.add(file);
            }
        return hits;
    }

}
