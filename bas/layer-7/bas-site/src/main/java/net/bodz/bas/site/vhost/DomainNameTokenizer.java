package net.bodz.bas.site.vhost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.t.iterator.PrefetchedIterator;

/**
 * "a.b.c" => { c, b, a }
 */
public class DomainNameTokenizer
        implements Iterable<String> {

    private final String domainName;
    private final boolean reversed;

    public DomainNameTokenizer(String domainName, boolean reversed) {
        if (domainName == null)
            throw new NullPointerException("domainName");
        this.domainName = domainName;
        this.reversed = reversed;
    }

    @Override
    public Iterator<String> iterator() {
        return reversed ? new ReversedLabelIterator(domainName) : new LabelIterator(domainName);
    }

    public List<String> toList() {
        List<String> list = new ArrayList<String>();
        for (String label : this)
            list.add(label);
        return list;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (String label : this) {
            if (buf.length() != 0)
                buf.append(", ");
            buf.append("(" + label + ")");
        }
        return buf.toString();
    }

}

class LabelIterator
        extends PrefetchedIterator<String> {

    private String labels;
    private final char delim;

    public LabelIterator(String labels) {
        this.labels = labels;
        this.delim = '.';
    }

    public LabelIterator(String labels, char delim) {
        this.labels = labels;
        this.delim = delim;
    }

    @Override
    protected String fetch() {
        if (labels == null)
            return end();
        int pos = labels.indexOf(delim);
        String label;
        if (pos == -1) {
            label = labels;
            labels = null;
        } else {
            label = labels.substring(0, pos);
            labels = labels.substring(pos + 1);
        }
        return label;
    }

}

class ReversedLabelIterator
        extends PrefetchedIterator<String> {

    private String labels;
    private final char delim;

    public ReversedLabelIterator(String labels) {
        this.labels = labels;
        this.delim = '.';
    }

    public ReversedLabelIterator(String labels, char delim) {
        this.labels = labels;
        this.delim = delim;
    }

    @Override
    protected String fetch() {
        if (labels == null)
            return end();
        int pos = labels.lastIndexOf(delim);
        String label;
        if (pos == -1) {
            label = labels;
            labels = null;
        } else {
            label = labels.substring(pos + 1);
            labels = labels.substring(0, pos);
        }
        return label;
    }

}