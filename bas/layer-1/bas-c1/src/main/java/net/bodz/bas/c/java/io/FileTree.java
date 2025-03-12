package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;

public class FileTree {

    public static int delete(File file, DeleteOption... options) {
        int count = 0;

        if (file.isDirectory())
            if (DeleteOptions.isDeleteTree(options)) {
                File[] files = file.listFiles();
                if (files != null)
                    for (File child : files)
                        count += delete(child, options);
            }

        if (file.delete())
            count++;

        if (DeleteOptions.isRemoveEmptyParents(options)) {
            while (true) {
                file = file.getParentFile();
                if (file == null)
                    break;
                if (!file.delete())
                    break;
            }
        }

        return count;
    }

    public static int delete(Path file, DeleteOption... options) {
        int count = 0;

        if (Files.isDirectory(file)) {
            if (DeleteOptions.isDeleteTree(options)) {
                try (Stream<Path> children = Files.list(file)) {
                    Iterator<Path> iterator = children.iterator();
                    while (iterator.hasNext()) {
                        Path child = iterator.next();
                        count += delete(child, options);
                    }
                } catch (IOException e) {
                    // ignore;
                }
            }
        }

        try {
            Files.delete(file);
            count++;
        } catch (IOException e) {
            // ignore
        }

        if (DeleteOptions.isRemoveEmptyParents(options)) {
            while (true) {
                file = file.getParent();
                if (file == null)
                    break;
                try {
                    Files.delete(file);
                } catch (IOException e) {
                    break;
                }
            }
        }

        return count;
    }

}
