package net.bodz.bas.files;

import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.TypesNLS;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.bas.types.util.PrefetchedIterator;

public class INIFile extends _MapFile<TextMap<String>> {

    static class TextMapBuilder implements MapRecordBuilder<TextMap<String>> {

        TextMap<String> map;

        @Override
        public void reset() {
            map = new TreeTextMap<String>();
        }

        @Override
        public TextMap<String> accept() throws ControlContinue, ControlBreak {
            return map;
        }

        @Override
        public void add(Object key, Object value) {
            String skey = String.valueOf(key);
            String sval = String.valueOf(value);
            map.put(skey, sval);
        }

    }

    private String mergeDelim;

    public INIFile(Object in, Object charset, boolean merge) {
        super(in, charset);
        if (merge)
            mergeDelim = "."; //$NON-NLS-1$
    }

    public INIFile(Object in, boolean merge) {
        super(in);
        if (merge)
            mergeDelim = "."; //$NON-NLS-1$
    }

    public INIFile(Object in, Object charset) {
        this(in, charset, false);
    }

    public INIFile(Object in) {
        this(in, false);
    }

    @Override
    protected RecordBuilder<TextMap<String>> getRecordBuilder() {
        return new TextMapBuilder();
    }

    @Override
    public Iterator<TextMap<String>> iterator() {
        return new PrefetchedIterator<TextMap<String>>() {

            private Iterator<String> lines;
            {
                lines = Files.readByLine2(charset, in).iterator();
            }

            @Override
            protected TextMap<String> fetch() {
                try {
                    return _fetch();
                } catch (ParseException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (ControlContinue c) {
                    return fetch();
                } catch (ControlBreak b) {
                    return end();
                }
            }

            private String  sectionName = null;
            private boolean ended;

            protected TextMap<String> _fetch() throws ParseException {
                if (ended)
                    return end();
                MapRecordBuilder<TextMap<String>> builder;
                builder = (MapRecordBuilder<TextMap<String>>) INIFile.this.builder;
                builder.reset();
                while (lines.hasNext()) {
                    String line = lines.next().trim();
                    if (line.isEmpty() || line.startsWith("#")) //$NON-NLS-1$
                        continue;
                    if (line.startsWith("[")) { //$NON-NLS-1$
                        assert line.endsWith("]"); //$NON-NLS-1$
                        sectionName = line.substring(1, line.length() - 1)
                                .trim();
                        if (mergeDelim == null) {
                            TextMap<String> section = builder.accept();
                            builder.reset();
                            builder.add("_section", sectionName); //$NON-NLS-1$
                            return section;
                        } else {
                            String existKey = "exist"; //$NON-NLS-1$
                            if (existKey != null) {
                                String ekey = sectionName + mergeDelim
                                        + existKey;
                                builder.add(ekey, true);
                            }
                        }
                        continue;
                    }
                    int eq = line.indexOf('=');
                    if (eq == -1) {
                        // BOM test.
                        String mesg = TypesNLS.getString("INIFile.invalidEntry") + line; //$NON-NLS-1$
                        System.err.println(mesg);
                        // throw new ParseException(mesg);
                        continue;
                    }
                    String key = line.substring(0, eq).trim();
                    if (mergeDelim != null && sectionName != null)
                        key = sectionName + mergeDelim + key;
                    String value = line.substring(eq + 1).trim();
                    builder.add(key, value);
                } // lines
                ended = true;
                return builder.accept();
            }

        };
    }

}
