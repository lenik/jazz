package net.bodz.bas.codegen.maven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.m2.ArtifactId;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.PathResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ProjectDirMap {

    static final Logger logger = LoggerFactory.getLogger(ProjectDirMap.class);

    Path mapFile;
    Map<String, String> qNameDirMap = new HashMap<>();
    Map<String, String> nameDirMap = new HashMap<>();

    public void clear() {
        qNameDirMap.clear();
        nameDirMap.clear();
    }

    public String getDir(ArtifactId id, boolean skipGroupId) {
        String qName = id.groupId + ":" + id.artifactId;
        String dir = qNameDirMap.get(qName);
        if (dir == null && skipGroupId)
            dir = nameDirMap.get(id.artifactId);
        return dir;
    }

    public void parseMapFile(String fileName)
            throws IOException {
        parseMapFile(Paths.get(fileName));
    }

    public void parseMapFile(Path file)
            throws IOException {
        this.mapFile = file;
        logger.debug("parse map file " + mapFile);

        PathResource mapFile = ResFn.path(file);
        if (Files.notExists(mapFile.getPathObject()))
            return;

        for (String line : mapFile.read().readLines()) {
            line = line.trim();
            if (line.startsWith("#"))
                continue;
            if (line.isEmpty())
                continue;
            int pos = line.indexOf(' ');
            if (pos == -1) {
                pos = line.indexOf('\t');
                if (pos == -1)
                    continue; // invalid line
            }
            String qName = line.substring(0, pos).trim();
            String path = line.substring(pos + 1).trim();
            qNameDirMap.put(qName, path);

            int colon = qName.indexOf(':');
            if (colon == -1)
                continue; // not a qualified name.

            // String groupId = qName.substring(0, colon);
            String artifactId = qName.substring(colon + 1);
            nameDirMap.put(artifactId, path);
        } // for line
    }

}
