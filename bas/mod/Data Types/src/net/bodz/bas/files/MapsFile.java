package net.bodz.bas.files;

import java.util.HashMap;
import java.util.Map;

/**
 * Preferred file name: *.maps
 */
public class MapsFile extends MultipartsFile<Map<String, String>> {

    private Map<String, String> partMap;

    public MapsFile(Object file, String encoding) {
        super(file, encoding);
    }

    public MapsFile(Object file) {
        super(file);
    }

    public MapsFile(String path) {
        super(path);
    }

    @Override
    protected void beginPart() {
        if (partMap == null)
            partMap = new HashMap<String, String>();
        else
            partMap.clear();
    }

    @Override
    protected Object endPart() {
        return partMap;
    }

    @Override
    protected boolean isPartEmpty() {
        return partMap.isEmpty();
    }

    @Override
    protected void addPartEntry(Object key, Object value) {
        partMap.put((String) key, (String) value);
    }

}
