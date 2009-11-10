package net.bodz.bas.files;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;

import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.types.util.DirectIterator;
import net.bodz.bas.types.util._DirectIterator;

/**
 * @test {@link CSVRecordsTest}
 */
public class CSVRecords extends _ResRecords<String[]> {

    private String delim;
    private int limit;

    public CSVRecords(ResLink resLink, Charset charset, String delim, int maxColumns) throws IOException {
        super(resLink, charset);
        this.delim = delim;
        this.limit = maxColumns;
    }

    public CSVRecords(ResLink resLink, Charset charset, String delim) throws IOException {
        this(resLink, charset, delim, 0);
    }

    public CSVRecords(ResLink resLink, String delim) throws IOException {
        this(resLink, null, delim);
    }

    public CSVRecords(ResLink resLink, Charset charset) throws IOException {
        this(resLink, charset, ":"); //$NON-NLS-1$
    }

    public CSVRecords(ResLink resLink) throws IOException {
        this(resLink, ":"); //$NON-NLS-1$
    }

    class Iter extends _DirectIterator<String[], IOException> {

        private LineReader lineReader;
        private boolean end;
        private String[] row;

        @Override
        public boolean next() throws IOException {
            if (end)
                return false;
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
                row = line.split(delim, limit);
                for (int i = 0; i < row.length; i++)
                    row[i] = row[i].trim();
                return true;
            }
            end = true;
            return false;
        }

        @Override
        public String[] get() throws NoSuchElementException {
            return row;
        }

    }

    public DirectIterator<? extends String[], IOException> iterator() {
        return new Iter();
    }

    @Override
    public DirectIterator<? extends String[], IOException> iterator(boolean allowOverlap) {
        return iterator();
    }

}
