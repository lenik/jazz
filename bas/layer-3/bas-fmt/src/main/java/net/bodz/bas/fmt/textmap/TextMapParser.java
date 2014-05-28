package net.bodz.bas.fmt.textmap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.java.lang.StringTypers;
import net.bodz.bas.err.IteratorTargetException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.typer.std.IParser;

public class TextMapParser<K, V>
        extends TextMapParserProc<K, V>
        implements Iterable<Entry<K, V>> {

    private Iterator<String> lines;

    public TextMapParser(Iterator<String> lines, IParser<K> keyParser, IParser<V> valueParser) {
        super(keyParser, valueParser);
        if (lines == null)
            throw new NullPointerException("lines");
        this.lines = lines;
    }

    public Map<K, V> toMap() {
        Map<K, V> map = new LinkedHashMap<>();
        for (Entry<K, V> entry : this)
            map.put(entry.getKey(), entry.getValue());
        return map;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new PrefetchedIterator<Entry<K, V>>() {

            @Override
            protected Entry<K, V> fetch() {
                if (lines == null)
                    return end();

                while (true) {
                    boolean eof = !lines.hasNext();

                    Entry<K, V> entry;
                    try {
                        entry = process(eof ? null : lines.next());
                    } catch (Exception e) {
                        throw new IteratorTargetException(e.getMessage(), e);
                    }

                    if (eof)
                        lines = null;
                    if (entry != null)
                        return entry;
                    if (eof)
                        return end();
                }
            } // fetch

        }; // PrefetchIterator+
    } // iterator()

    public static TextMapParser<String, String> parse(IStreamInputSource src) {
        Iterator<String> iterator = src.to(StreamReading.class).lines().iterator();
        return new TextMapParser<String, String>(iterator, StringTypers.INSTANCE, StringTypers.INSTANCE);
    }

}
