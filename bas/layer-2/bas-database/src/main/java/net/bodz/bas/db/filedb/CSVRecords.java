package net.bodz.bas.db.filedb;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.files.CSVRecordsTest;
import net.bodz.bas.io.LineReader;

/**
 * @test {@link CSVRecordsTest}
 */
public class CSVRecords
        extends _ResRecords<String[]> {

    private String delim;
    private int limit;

    public CSVRecords(ResLink resLink, Charset charset, String delim, int maxColumns)
            throws IOException {
        super(resLink, charset);
        this.delim = delim;
        this.limit = maxColumns;
    }

    public CSVRecords(ResLink resLink, Charset charset, String delim)
            throws IOException {
        this(resLink, charset, delim, 0);
    }

    public CSVRecords(ResLink resLink, String delim)
            throws IOException {
        this(resLink, null, delim);
    }

    public CSVRecords(ResLink resLink, Charset charset)
            throws IOException {
        this(resLink, charset, ":");
    }

    public CSVRecords(ResLink resLink)
            throws IOException {
        this(resLink, ":");
    }

    class Iter
            extends AbstractImmediateIteratorX<String[], IOException> {

        private LineReader lineReader;
        private boolean end;

        @Override
        public String[] next()
                throws IOException {
            if (end)
                return end();
            if (lineReader == null) {
                Reader reader = resLink.openReader(charset);
                lineReader = new LineReader(reader);
            }
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
    public ImmediateIteratorX<String[], IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

    @Override
    public IteratorX<String[], IOException> iterator() {
        ImmediateIteratorX<String[], IOException> immIter = iterator(false);
        return new ImmIterIterator<String[], IOException>(immIter);
    }

}
