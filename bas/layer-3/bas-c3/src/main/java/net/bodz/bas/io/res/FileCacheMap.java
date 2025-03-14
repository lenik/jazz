package net.bodz.bas.io.res;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;

public class FileCacheMap<T extends Comparable<T>> {

    static final Logger logger = LoggerFactory.getLogger(FileCacheMap.class);

    Map<T, List<String>> map = new TreeMap<>();
    final IResourceType<T> type;

    public FileCacheMap(@NotNull IResourceType<T> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    public static FileCacheMap<File> create() {
        return new FileCacheMap<File>(FileResourceType.INSTANCE);
    }

    public static FileCacheMap<Path> createPathIndexed() {
        return new FileCacheMap<Path>(PathResourceType.INSTANCE);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<T> keySet() {
        return map.keySet();
    }

    public boolean containsFile(T file) {
        return map.containsKey(file);
    }

    protected FileCacheMap<T> newMap() {
        return new FileCacheMap<T>(type);
    }

    public FileCacheMap<T> grep(Predicate<T> filePredicate) {
        FileCacheMap<T> sub = newMap();
        for (T file : map.keySet()) {
            if (filePredicate.test(file)) {
                List<String> data = map.get(file);
                sub.map.put(file, data);
            }
        }
        return sub;
    }

    public List<String> loadLinesCached(T file)
            throws IOException {
        List<String> list = map.get(file);
        if (list == null) {
            list = type.loadLines(file);
            map.put(file, list);
        }
        return list;
    }

    public final void save()
            throws IOException {
        save(ISaveOptions.DEFAULT);
    }

    public void save(ISaveOptions options)
            throws IOException {
        for (Map.Entry<T, List<String>> entry : map.entrySet()) {
            T file = entry.getKey();

            List<String> lines = entry.getValue();
            Collections.sort(lines);

            if (lines.isEmpty() && options.isPurgeEmpty()) {
                logger.info("delete " + file);
                if (!type.delete(file))
                    logger.error("can't delete " + file);
                continue;
            }

            StringBuilder buf = new StringBuilder(lines.size() * 100);
            for (String line : new LinkedHashSet<String>(lines)) {
                buf.append(line);
                buf.append('\n');
            }
            String content = buf.toString();

            if (options.isCompare()) {
                if (type.exists(file)) {
                    String old;
                    try {
                        old = type.loadText(file);
                        if (content.equals(old)) {
                            logger.debug("unchanged: " + file);
                            continue;
                        }
                    } catch (IOException e) {
                        logger.error("error reading file " + file, e);
                        if (options.getErrorHandler() != null)
                            options.getErrorHandler().readError(e);
                        else
                            throw e;
                    }
                }
            }

            try {
                logger.info("update file " + file);
                if (options.isMkdirs())
                    type.createParentDirs(file);

                if (options.getEncoding() == null)
                    type.saveText(file, content);
                else
                    type.saveText(file, content, options.getEncoding());
            } catch (IOException e) {
                logger.error("error writing file " + file, e);
                if (options.getErrorHandler() != null)
                    options.getErrorHandler().writeError(e);
                else
                    throw e;
            }
        }
    }

}
