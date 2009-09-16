package net.bodz.xfo.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.files.PartRecords;
import net.bodz.bas.files.PartRecords.PartMap;
import net.bodz.bas.io.FileResLink;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.bas.types.util.DirectIterator;
import net.bodz.xfo.types.AttributedFile;
import net.bodz.xfo.types.FileVersion;
import net.bodz.xfo.types.MapAttributes;

/**
 * @test {@link TaggedFileTest}
 */
public class TaggedFile extends AttributedFile {

    public static final String EXTENSION = ".tm";

    private final ResLink      ixRes;

    public TaggedFile(File file) {
        this(file, new File(file.getPath() + EXTENSION));
    }

    public TaggedFile(File file, File ixFile) {
        this(file, new FileResLink(ixFile));
    }

    public TaggedFile(File file, ResLink ixRes) {
        super(file);
        if (ixRes == null)
            throw new NullPointerException("ixFile");
        if (ixRes.exists() == Boolean.FALSE)
            throw new IllegalArgumentException(".ix isn't existed: " + ixRes);
        this.ixRes = ixRes;
        File ixFile = ixRes.getFile();
        if (ixFile != null)
            addReference(new FileVersion(ixFile));
    }

    public static boolean isLookedLike(File file) {
        File ixFile = new File(file.getPath() + EXTENSION);
        return ixFile.isFile();
    }

    @Override
    protected MapAttributes fetchAttributes() throws IOException, ParseException {
        final String _sectionKey = "_section";

        PartRecords records = new PartRecords(ixRes, Charset.forName("utf-8"),
                PartRecords.FREE_FORM) {
            @Override
            protected Map<String, String> isStartOfPart(String line) {
                if (line.startsWith("::")) {
                    Map<String, String> map = newMap();
                    String section = line.substring(2).trim();
                    map.put(_sectionKey, section);
                    return map;
                }
                return null;
            }
        };
        DirectIterator<PartMap, IOException> dit = records.iterator();
        TextMap<String> all = new TreeTextMap<String>();
        while (dit.next()) {
            PartMap map = dit.get();
            String prefix = map.remove(_sectionKey);
            if (prefix == null)
                prefix = "";
            else
                prefix = prefix.trim() + ".";
            for (Entry<String, String> e : map.entrySet())
                all.put(prefix + e.getKey(), e.getValue());
        }
        return new MapAttributes(all);
    }

}
