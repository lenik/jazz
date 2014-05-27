package net.bodz.bas.fmt.records;

import java.io.IOException;

import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class CSVRecords
        extends AbstractSeqRecords<String[]> {

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
            extends AbstractMitorx<String[], IOException> {

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
    public Mitorx<String[], IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

}
