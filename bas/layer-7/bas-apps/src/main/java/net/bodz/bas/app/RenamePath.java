package net.bodz.bas.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.PatternSubst;
import net.bodz.bas.c.java.util.Patterns;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Rename path
 */
@ProgramName("renpath")
public class RenamePath
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(RenamePath.class);

    Pattern pattern;
    String replacement;

    boolean pruneEmptyDirs = true;

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        if (args.length == 0) {
            System.err.println("Expect subst regexp.");
            System.exit(1);
        }

        String substExpr = args[0];
        PatternSubst subst = Patterns.subst(substExpr);
        pattern = subst.getPattern();
        replacement = subst.getReplacement();

        for (int i = 1; i < args.length; i++) {
            String path = args[i];
            rename(path);
        }
    }

    public void rename(String pathStr)
            throws IOException {
        String newPathStr = pattern.matcher(pathStr).replaceAll(replacement);
        Path path = Paths.get(pathStr);
        Path newPath = Paths.get(newPathStr);

        Path newParent = newPath.getParent();
        if (Files.notExists(newParent)) {
            logger.info("Create parent dir " + newParent);
            Files.createDirectories(newParent);
        }

        try {
            Files.move(path, newPath);
        } catch (IOException e) {
            logger.error("Failed to move " + path + " to " + newPath + ": " + e.getMessage(), e);
            return;
        }

        if (pruneEmptyDirs)
            pruneEmptyDirs(path.getParent());
    }

    static boolean pruneEmptyDirs(Path dir) {
        if (!Files.isDirectory(dir))
            return false;
        try {
            Files.delete(dir);
        } catch (IOException e) {
            return false;
        }
        Path parent = dir.getParent();
        if (parent != null)
            pruneEmptyDirs(parent);
        return true;
    }

    public static void main(String[] args)
            throws Exception {
        new RenamePath().execute(args);
    }

}
