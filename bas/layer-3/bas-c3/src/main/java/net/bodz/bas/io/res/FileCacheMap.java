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

import net.bodz.bas.c.java.io.FileRelation;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;

public class FileCacheMap<K extends Comparable<K>> {

    static final Logger logger = LoggerFactory.getLogger(FileCacheMap.class);

    final Map<K, List<String>> map = new TreeMap<>();
    final IResourceType<K> type;

    public FileCacheMap(@NotNull IResourceType<K> type) {
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

    public Set<K> keySet() {
        return map.keySet();
    }

    public boolean containsFile(K file) {
        return map.containsKey(file);
    }

    protected FileCacheMap<K> newMap() {
        return new FileCacheMap<K>(type);
    }

    public FileCacheMap<K> grep(Predicate<K> filePredicate) {
        FileCacheMap<K> sub = newMap();
        for (K file : map.keySet()) {
            if (filePredicate.test(file)) {
                List<String> data = map.get(file);
                sub.map.put(file, data);
            }
        }
        return sub;
    }

    public List<String> loadLinesCached(K file)
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
        for (Map.Entry<K, List<String>> entry : map.entrySet()) {
            K file = entry.getKey();

            if (!options.isUpdateAll()) {
                Path path = type.toPath(file);
                if (path != null) {
                    List<Path> updateDirs = options.getUpdateDirs();
                    boolean inAny = false;
                    for (Path updateDir : updateDirs)
                        if (FileRelation.isChildOf(path, updateDir)) {
                            inAny = true;
                            break;
                        }
                    if (!inAny) // skip elsewhere
                        continue;
                }
            }

            List<String> lines = entry.getValue();
            Collections.sort(lines);

            if (lines.isEmpty() && options.isPurgeEmpty()) {
                logger.info("delete " + file);
                if (!type.delete(file))
                    logger.error("can't delete " + file);
                continue;
            }

            StringBuilder buf = new StringBuilder(lines.size() * 100);
            for (String line : new LinkedHashSet<String>(lines))
                buf.append(line).append('\n');
            String content = buf.toString();

            if (options.isSaveChangedOnly()) {
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
                if (options.isCreateParentDirs())
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
