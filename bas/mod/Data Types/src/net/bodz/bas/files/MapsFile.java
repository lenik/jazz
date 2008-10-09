package net.bodz.bas.files;

import java.util.TreeMap;

/**
 * Preferred file name: *.maps
 */
public class MapsFile extends MultipartsFile<MapsFile.PartMap> {

    public class PartMap extends TreeMap<String, String> {

        private static final long serialVersionUID = 7647573703380582923L;

        public String getText() {
            return get(textKey);
        }

        public void setText(String text) {
            put((String) textKey, text);
        }

    }

    private PartMap partMap;

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
            partMap = new PartMap();
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
