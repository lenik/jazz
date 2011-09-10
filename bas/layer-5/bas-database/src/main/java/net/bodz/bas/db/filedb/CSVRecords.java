package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.collection.iterator.AbstractIteratorMX;
import net.bodz.bas.collection.iterator.IteratorM2X;
import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.resource.IStreamInputSource;

public class CSVRecords
        extends _ResRecords<String[]> {

    private String delim;
    private int limit;

    public CSVRecords(IStreamInputSource source, String delim, int maxColumns)
            throws IOException {
        super(source);
        if (delim == null)
            throw new NullPointerException("delim");
        this.delim = delim;
        this.limit = maxColumns;
    }

    public CSVRecords(IStreamInputSource source, String delim)
            throws IOException {
        this(source, delim, 0);
    }

    public CSVRecords(IStreamInputSource source)
            throws IOException {
        this(source, ":");
    }

    class Iter
            extends AbstractIteratorMX<String[], IOException> {

        private LineReader lineReader;

        @Override
        public String[] _next()
                throws IOException {
            if (lineReader == null)
                lineReader = source.newLineReader();
            String line;
            while ((line = lineReader.readLine()) != null) {
                if (line.isEmpty()) // option
                    continue;
                // if (line.startsWith("#")) // option
                // continue;
                String[] row = line.split(delim, limit);
                for (int i = 0; i < row.length; i++)
                    row[i] = row[i].trim();
                return row;
            }
            return end();
        }

    }

    @Override
    public IteratorMX<String[], IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

    @Override
    public IteratorX<String[], IOException> iteratorX() {
        IteratorMX<String[], IOException> immIter = iterator(false);
        return new IteratorM2X<String[], IOException>(immIter);
    }

}
