package net.bodz.bas.fmt.records;

import java.io.IOException;

import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class CsvRecords
        extends AbstractSeqRecords<CsvRow> {

    static final char QQ = '"';

    private char delimiter;
    private int maxColumnCount;
    private boolean quoted = true;
    private boolean trim = false;

    public CsvRecords(IStreamInputSource source, char delim, int maxColumnCount)
            throws IOException {
        super(source);
        this.delimiter = delim;
        this.maxColumnCount = maxColumnCount;
    }

    public CsvRecords(IStreamInputSource source, char delim)
            throws IOException {
        this(source, delim, 0);
    }

    public CsvRecords(IStreamInputSource source)
            throws IOException {
        this(source, ',');
    }

    public char getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public int getMaxColumnCount() {
        return maxColumnCount;
    }

    public void setMaxColumnCount(int maxColumnCount) {
        this.maxColumnCount = maxColumnCount;
    }

    public boolean isQuoted() {
        return quoted;
    }

    public void setQuoted(boolean quoted) {
        this.quoted = quoted;
    }

    public boolean isTrim() {
        return trim;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    class Iter
            extends AbstractMitorx<CsvRow, IOException> {

        ICharIn in;
        StringBuilder cellBuf = new StringBuilder();
        StringBuilder rawLineBuf = new StringBuilder();

        static final int S_START = 0;
        static final int S_QQ = 1;

        @Override
        public CsvRow _next()
                throws IOException {
            if (in == null)
                in = source.newCharIn();

            CsvRow row = new CsvRow(Math.max(maxColumnCount, 10));
            cellBuf.setLength(0);
            rawLineBuf.setLength(0);

            int state = S_START;
            int c;
            char last = 0;
            char ch = 0;

            while (true) {
                last = ch;
                ch = (char) (c = in.read());
                rawLineBuf.append(ch);
                if (c == -1)
                    break;

                switch (state) {
                case S_START:
                    switch (ch) {
                    case QQ:
                        if (last == QQ)
                            cellBuf.append(QQ); // "" -> "
                        state = S_QQ;
                        continue;

                    case '\r':
                        continue; // ignore CR

                    case '\n':
                        return finish(push(row, cellBuf), rawLineBuf);

                    default:
                        if (ch == delimiter) {
                            push(row, cellBuf);
                            continue;
                        }
                        cellBuf.append(ch);
                    }
                    continue;

                case S_QQ:
                    if (ch == QQ)
                        state = S_START;
                    else
                        cellBuf.append(ch);
                    continue;
                }
            }

            if (! row.isEmpty() || cellBuf.length() != 0)
                push(row, cellBuf);

            if (! row.isEmpty())
                return finish(row, rawLineBuf);

            return end();
        }

        @Override
        protected void finalize()
                throws Throwable {
            if (in != null) {
                in.close();
                in = null;
            }
        }

    }

    CsvRow push(CsvRow row, StringBuilder cellBuffer) {
        String cell = cellBuffer.toString();
        cellBuffer.setLength(0);
        if (trim)
            cell = cell.trim();
        row.add(cell);
        return row;
    }

    CsvRow finish(CsvRow row, StringBuilder rawLineBuffer) {
        row.rawLine = rawLineBuffer.toString();
        return row;
    }

    @Override
    public Mitorx<CsvRow, IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

}
