package net.bodz.bas.files;

import java.nio.charset.Charset;

import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;
import net.bodz.bas.types.TreeTextMap;

/**
 * Preferred file name: *.maps
 */
public class MapsFile extends MultipartsFile<MapsFile.PartMap> {

    public class PartMap extends TreeTextMap<String> {

        private static final long serialVersionUID = 7647573703380582923L;

        public String getText() {
            return get(getTextKey());
        }

        public void setText(String text) {
            put((String) getTextKey(), text);
        }

    }

    public class PartMapBuilder implements MapRecordBuilder<PartMap> {

        PartMap part;

        @Override
        public void reset() {
            // part = null;
            if (part != null)
                part.clear();
        }

        @Override
        public PartMap accept() throws ControlContinue, ControlBreak {
            if (part == null || part.isEmpty())
                throw new ControlBreak();
            PartMap ret = part;
            part = null;
            return ret;
        }

        @Override
        public void add(Object key, Object value) {
            if (part == null)
                part = new PartMap();
            String skey = String.valueOf(key);
            String sval = String.valueOf(value);
            part.put(skey, sval);
        }

    }

    public MapsFile(Object file, Charset charset) {
        super(file, charset);
    }

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
    protected RecordBuilder<PartMap> getRecordBuilder() {
        return new PartMapBuilder();
    }

}
