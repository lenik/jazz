package net.bodz.bas.files;

import java.io.IOException;
import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.types.util.PrefetchedIterator;

public class CSVFile implements FileSource<String[]> {

    private String     delim;
    private int        limit;
    private LineReader lineReader;

    public CSVFile(String delim, int maxColumns, Object in, Object charset)
            throws IOException {
        this.delim = delim;
        this.limit = maxColumns;
        this.lineReader = Files.getLineReader(in, charset);
    }

    public CSVFile(String delim, Object in, Object charset) throws IOException {
        this(delim, 0, in, charset);
    }

    public CSVFile(String delim, Object in) throws IOException {
        this(delim, in, null);
    }

    public CSVFile(Object in, Object charset) throws IOException {
        this(":", in, charset);
    }

    public CSVFile(Object in) throws IOException {
        this(":", in);
    }

    @Override
    public Iterator<String[]> iterator() {
        return new PrefetchedIterator<String[]>() {
            @Override
            protected Object fetch() {
                try {
                    while (true) {
                        String line = lineReader.readLine();
                        if (line == null)
                            return END;
                        if (line.isEmpty()) // option
                            continue;
                        // if (line.startsWith("#")) // option
                        // continue;
                        String[] row = line.split(delim, limit);
                        for (int i = 0; i < row.length; i++)
                            row[i] = row[i].trim();
                        return row;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

}
