package net.bodz.bas.vcs.git;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.bodz.bas.c.java.util.Calendars;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.vcs.*;

public class NativeGitLogParser
        extends PrefetchedIterator<IVcsLogEntry> {

    static final Logger logger = LoggerFactory.getLogger(NativeGitLogParser.class);

    private IVcsWorkingCopy workingCopy;
    private Iterator<String> lines;
    private VcsLogOptions options;

    private static final char START = '0';
    private static final char SUBJECT = 'S';
    private static final char TEXT = 'T';
    private static final char NAME_STATUS = 'N';
    private char state = START;

    private String hash;
    private String authorName;
    private String authorEmail;
    private Calendar authorDate;
    private StringBuilder subjectBuf = new StringBuilder();
    private StringBuilder textBuf = new StringBuilder();
    private List<IFileChangement> changes = new ArrayList<IFileChangement>();

    private LinkedList<IVcsLogEntry> queue;

    public NativeGitLogParser(IVcsWorkingCopy workingCopy, Iterator<String> lines, VcsLogOptions options) {
        if (workingCopy == null)
            throw new NullPointerException("workingCopy");
        if (lines == null)
            throw new NullPointerException("lines");
        this.workingCopy = workingCopy;
        this.lines = lines;
        this.options = options;
        queue = new LinkedList<IVcsLogEntry>();
    }

    public static Iterable<IVcsLogEntry> parse(final IVcsWorkingCopy workingCopy, final Iterable<String> lines,
            final VcsLogOptions options) {
        return new Iterable<IVcsLogEntry>() {
            @Override
            public Iterator<IVcsLogEntry> iterator() {
                return new NativeGitLogParser(workingCopy, lines.iterator(), options);
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
                while (!processLine(line))
                    ;
            } else {
                commit();
                lines = null;
            }
        }
    }

    public boolean processLine(String line) {
        line = StringPart.chomp(line);
        switch (state) {
        case START:
            line = line.trim();
            if (line.isEmpty()) {
                state = SUBJECT;
                return true;
            }

            if (line.startsWith("commit ")) {
                commit();
                hash = line.substring(7).trim();
                return true;
            }

            int colon = line.indexOf(':');
            if (colon == -1) {
                logger.warn("Illegal header in the log: " + line);
                return true;
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
            return true;

        case SUBJECT:
            if (line.isEmpty()) {
                state = TEXT;
                return true;
            }

            if (subjectBuf.length() != 0)
                subjectBuf.append(' ');
            subjectBuf.append(line.trim());
            return true;

        case TEXT:
            if (line.isEmpty()) {
                state = NAME_STATUS;
                return true;
            }
            if (!line.startsWith("    ")) {
                state = NAME_STATUS;
                return false; // Rejected
            }
            line = line.substring(4);
            textBuf.append(line);
            return true;

        case NAME_STATUS:
            if (line.isEmpty()) {
                state = START;
                return true;
            }

            char statusChar = line.charAt(0);
            if (Character.isUpperCase(statusChar)) {
                int tab = line.indexOf('\t');
                if (tab != -1) {
                    String statusStr = line.substring(0, tab);
                    String path = line.substring(tab + 1).trim();
                    FileChangement change = new FileChangement(workingCopy);
                    change.setPath(path);
                    switch (statusChar) {
                    case 'A':
                        change.setStatus(FileChangeStatus.ADD);
                        break;
                    case 'D':
                        change.setStatus(FileChangeStatus.DELETE);
                        break;
                    case 'M':
                        change.setStatus(FileChangeStatus.MODIFY);
                        break;
                    case 'R':
                        change.setStatus(FileChangeStatus.RENAME);

                        int similarity = Integer.parseInt(statusStr.substring(1));
                        change.setSimilarity(similarity);

                        tab = path.indexOf('\t');
                        String renamedFrom = path.substring(tab + 1);
                        path = path.substring(0, tab);
                        change.setPath(path);
                        change.setRenamedFrom(renamedFrom);
                        break;
                    }

                    changes.add(change);
                    return true;
                } // if tab char exists.
            } // if statusChar is upper case.

            state = START;
            return false;

        default:
            throw new IllegalStateException(String.valueOf(state));
        } // switch state
    }

    public void commit() {
        if (hash != null) {
            MutableVcsLogEntry entry = new MutableVcsLogEntry();
            if (options.abbrVersionLength > 0)
                hash = hash.substring(0, options.abbrVersionLength);
            entry.setVersion(hash);
            entry.setAuthorName(authorName);
            entry.setAuthorEmail(authorEmail);
            entry.setAuthorDate(authorDate);
            entry.setSubject(subjectBuf.toString());
            entry.setBody(textBuf.toString());
            entry.setChanges(changes);
            queue.addLast(entry);
        }
        hash = null;
        authorName = null;
        authorEmail = null;
        authorDate = null;
        subjectBuf.setLength(0);
        textBuf.setLength(0);
        changes = new ArrayList<IFileChangement>();
    }

}
