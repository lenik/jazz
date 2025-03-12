package net.bodz.bas.esm.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.nio.file.PathFn;
import net.bodz.bas.c.java.util.stream.iterable.Stream;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class NpmDirs {

    static final Logger logger = LoggerFactory.getLogger(NpmDirs.class);

    public static List<NpmDir> findPackageDirs(Path startDir, int maxDepth, int maxParents, String... moduleNames) {
        List<NpmDir> foundNpmDirs = new ArrayList<>(moduleNames.length);
        for (String name : moduleNames) {
            if (name == null)
                continue;
            NpmDir npmDir = findNpmDir(startDir, maxDepth, maxParents, name);
            if (npmDir != null)
                foundNpmDirs.add(npmDir);
        }
        return foundNpmDirs;
    }

    public static NpmDir findNpmDir(Path startDir, int maxDepth, int maxParents, String packageName) {
        if (packageName == null)
            throw new NullPointerException("packageName");

        // logger.debug("dir %s, maxd=%d, maxp=%d, mod %s\n", //
        // startDir, maxDepth, maxParents, packageName);

        Path packageDir = startDir.resolve(packageName);
        if (NpmDir.isPackageDir(packageDir)) {
            logger.debug("matched package dir: " + packageDir);
            return new NpmDir(packageDir);
        }

        if (maxDepth > 0) {
            try (Stream<Path> stream = PathFn.list(startDir)) {
                for (Path subDir : stream)
                    if (Files.isDirectory(subDir)) {
                        NpmDir child = findNpmDir(subDir, maxDepth - 1, 0, packageName);
                        if (child != null)
                            return child;
                    }
            } catch (IOException e) {
                // ignored: stop checking children.
            }
        }

        if (maxParents <= 0)
            return null;
        Path parent = startDir.getParent();
        if (parent == null)
            return null;

        return findNpmDir(parent, maxDepth + 1, maxParents - 1, packageName);
    }

}
