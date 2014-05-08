package net.bodz.bas.vcs.git;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

import net.bodz.bas.c.java.util.Calendars;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.vcs.IVcsLogEntry;
import net.bodz.bas.vcs.MutableVcsLogEntry;

public class NativeGitLogParser
        extends PrefetchedIterator<IVcsLogEntry> {

    static final Logger logger = LoggerFactory.getLogger(NativeGitLogParser.class);

    private Iterator<String> lines;

    private char state = 'H'; // Header, Subject, Text
    private String hash;
    private String authorName;
    private String authorEmail;
    private Calendar authorDate;
    private StringBuilder subjectBuf = new StringBuilder();
    private StringBuilder textBuf = new StringBuilder();

    private LinkedList<IVcsLogEntry> queue;

    public NativeGitLogParser(Iterator<String> lines) {
        if (lines == null)
            throw new NullPointerException("lines");
        this.lines = lines;
        queue = new LinkedList<IVcsLogEntry>();
    }

    public static Iterable<IVcsLogEntry> parse(final Iterable<String> lines) {
        return new Iterable<IVcsLogEntry>() {
            @Override
            public Iterator<IVcsLogEntry> iterator() {
                return new NativeGitLogParser(lines.iterator());
            }
        };
    }

    @Override
    protected IVcsLogEntry fetch() {
        while (true) {
            if (!queue.isEmpty())
                return queue.removeFirst();

            if (lines == null)
                return end();

            if (lines.hasNext()) {
                String line = lines.next();
                processLine(line);
            } else {
                commit();
                lines = null;
            }
        }
    }

    public void processLine(String line) {
        line = StringPart.chomp(line);
        switch (state) {
        case 'H':
            line = line.trim();
            if (line.isEmpty()) {
                state = 'S';
                return;
            }

            if (line.startsWith("commit ")) {
                hash = line.substring(7).trim();
                return;
            }

            int colon = line.indexOf(':');
            if (colon == -1) {
                logger.warn("Illegal header in the log: " + line);
                return;
            }

            String header = line.substring(0, colon);
            String value = line.substring(colon + 1).trim();
            switch (header) {
            case "Author":
                int lt = value.indexOf('<');
                if (lt == -1) {
                    authorName = value;
                    authorEmail = null;
                } else {
                    authorName = value.substring(0, lt).trim();
                    authorEmail = value.substring(lt + 1, value.length() - 1).trim();
                }
                break;

            case "Date": // 1399457012 +0800
                authorDate = Calendars.parseTimestampTZ(value);
                break;

            default:
                break; // ignore unknown headers.
            }
            return;

        case 'S':
            if (line.isEmpty()) {
                state = 'T';
                return;
            }

            if (subjectBuf.length() != 0)
                subjectBuf.append(' ');
            subjectBuf.append(line.trim());
            return;

        case 'T':
        default:
            if (!line.startsWith("    ")) {
                commit();
                state = 'H';
                if (!line.isEmpty())
                    processLine(line); // Re-process.
            } else {
                line = line.substring(4);
                textBuf.append(line);
            }
        } // switch
    }

    public void commit() {
        if (hash != null) {
            MutableVcsLogEntry entry = new MutableVcsLogEntry();
            entry.setVersion(hash);
            entry.setAuthorName(authorName);
            entry.setAuthorEmail(authorEmail);
            entry.setAuthorDate(authorDate);
            entry.setSubject(subjectBuf.toString());
            entry.setBody(textBuf.toString());
            queue.addLast(entry);
        }
        hash = null;
        authorName = null;
        authorEmail = null;
        authorDate = null;
        subjectBuf.setLength(0);
        textBuf.setLength(0);
    }

}
