package net.bodz.bas.text.textmap;

import java.util.Iterator;
import java.util.Map.Entry;

import net.bodz.bas.err.IteratorTargetException;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.typer.std.IParser;

public class TextMapParser<K, V>
        extends TextMapParserImpl<K, V>
        implements Iterable<Entry<K, V>> {

    private Iterator<String> lines;

    public TextMapParser(Iterator<String> lines, IParser<K> keyParser, IParser<V> valueParser) {
        super(keyParser, valueParser);
        if (lines == null)
            throw new NullPointerException("lines");
        this.lines = lines;
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

}
