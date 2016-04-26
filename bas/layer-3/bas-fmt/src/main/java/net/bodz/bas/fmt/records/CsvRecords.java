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

        private ICharIn in;

        static final int S_START = 0;
        static final int S_QQ = 1;

        @Override
        public CsvRow _next()
                throws IOException {
            if (in == null)
                in = source.newCharIn();

            CsvRow row = new CsvRow(Math.max(maxColumnCount, 10));
            StringBuilder sb = new StringBuilder();

            int state = S_START;
            int c;
            char last = 0;
            char ch = 0;

            while (true) {
                last = ch;
                ch = (char) (c = in.read());
                if (c == -1)
                    break;

                switch (state) {
                case S_START:
                    switch (ch) {
                    case QQ:
                        if (last == QQ)
                            sb.append(QQ); // "" -> "
                        state = S_QQ;
                        continue;

                    case '\r':
                        continue; // ignore CR

                    case '\n':
                        return finish(push(row, sb));

                    default:
                        if (ch == delimiter) {
                            push(row, sb);
                            continue;
                        }
                        sb.append(ch);
                    }
                    continue;

                case S_QQ:
                    if (ch == QQ)
                        state = S_START;
                    else
                        sb.append(ch);
                    continue;
                }
            }

            if (!row.isEmpty() || sb.length() != 0)
                push(row, sb);

            if (!row.isEmpty())
                return finish(row);

            return end();
        }
    }

    CsvRow push(CsvRow row, StringBuilder sb) {
        String s = sb.toString();
        sb.setLength(0);
        if (trim)
            s = s.trim();
        row.add(s);
        return row;
    }

    CsvRow finish(CsvRow row) {
        return row;
    }

    @Override
    public Mitorx<CsvRow, IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

}
