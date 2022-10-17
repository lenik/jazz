package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class FileContentMap
        extends TreeMap<File, List<String>> {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(FileContentMap.class);

    public List<Class<?>> extensions = new ArrayList<Class<?>>();

    public void saveFiles(boolean deleteEmptyFiles)
            throws IOException {
        for (Map.Entry<File, List<String>> entry : this.entrySet()) {
            File file = entry.getKey();

            List<String> lines = entry.getValue();
            Collections.sort(lines);

            if (lines.isEmpty() && deleteEmptyFiles) {
                if (file.delete())
                    logger.info("Deleted " + file);
                continue;
            }

            StringBuilder buf = new StringBuilder(lines.size() * 100);
            for (String line : new LinkedHashSet<String>(lines)) {
                buf.append(line);
                buf.append('\n');
            }
            String content = buf.toString();

            // The same?
            if (file.exists() == Boolean.TRUE) {
                String old = FileData.readString(file);
                if (content.equals(old)) {
                    logger.debug("Not changed: " + file);
                    continue;
                }
            }

            try {
                logger.info("Update: " + file);
                file.getParentFile().mkdirs();
                FileData.writeString(file, content);
            } catch (IOException e) {
                logger.error("Failed to update " + file, e);
            }
        }
    }

    public List<String> loadFile(File file) {
        List<String> list = this.get(file);
        if (list == null) {
            list = new ArrayList<String>();
            if (file.exists())
                for (String line : new FileResource(file).to(StreamReading.class).lines()) {
                    list.add(line.trim());
                }
            this.put(file, list);
        }
        return list;
    }

}
