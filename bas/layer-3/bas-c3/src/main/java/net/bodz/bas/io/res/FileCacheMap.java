package net.bodz.bas.io.res;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class FileCacheMap {

    static final Logger logger = LoggerFactory.getLogger(FileCacheMap.class);

    Map<File, List<String>> map = new TreeMap<>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<File> fileSet() {
        return map.keySet();
    }

    public boolean containsFile(File file) {
        return map.containsKey(file);
    }

    public FileCacheMap grep(Predicate<File> p) {
        FileCacheMap sub = new FileCacheMap();
        for (File file : map.keySet()) {
            if (p.test(file)) {
                List<String> data = map.get(file);
                sub.map.put(file, data);
            }
        }
        return sub;
    }

    public List<String> loadCache(File file) {
        List<String> list = map.get(file);
        if (list == null) {
            list = new ArrayList<String>();

            if (file.exists())
                for (String line : ResFn.file(file).to(StreamReading.class).lines()) {
                    list.add(line.trim());
                }

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
        for (Map.Entry<File, List<String>> entry : map.entrySet()) {
            File file = entry.getKey();

            List<String> lines = entry.getValue();
            Collections.sort(lines);

            if (lines.isEmpty() && options.isPurgeEmpty()) {
                logger.info("delete " + file);
                if (! file.delete())
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
                if (file.exists()) {
                    String old;
                    try {
                        old = FileData.readString(file);
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
                    file.getParentFile().mkdirs();

                if (options.getEncoding() == null)
                    FileData.writeString(file, content);
                else
                    FileData.writeString(file, content, options.getEncoding());
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
