package net.bodz.bas.cli.skel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeComparator;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.util.exception.ExceptionLog.LogItem;

public class FileHandleResultStat {

    private int ignored;
    private int total;
    private int changed;
    private int saved;
    private int deleted;
    private int renamed;
    private int moved;
    private int copied;
    private int errorred;

    private Map<String, Integer> tagStat = new TreeMap<String, Integer>();
    private Map<Class<?>, Integer> errorStat = new HashMap<Class<?>, Integer>();

    public void commit(FileHandler handler) {
        FileHandleResult result = handler.getResult();
        switch (result) {
        case ignored:
            ignored++;
            break;
        case saved:
            saved++;
            break;
        case deleted:
            deleted++;
            break;
        case renamed:
            renamed++;
            break;
        case moved:
            moved++;
            break;
        case copied:
            copied++;
            break;
        default:
            throw new UnexpectedException("Unknown file handle result: " + result);
        }

        if (handler.isErrored()) {
            for (LogItem logItem : handler.getExceptionLog()) {
                Class<? extends Throwable> errorType = logItem.getException().getClass();
                Integer errCount = errorStat.get(errorType);
                if (errCount == null)
                    errCount = 0;
                errorStat.put(errorType, ++errCount);
            }
            errorred++;
        }

        Boolean diff = handler.getDiff();
        if (diff == null)
            throw new IllegalStateException();
        if (diff)
            changed++;

        for (String tag : handler.getTags()) {
            Integer tagCount = tagStat.get(tag);
            if (tagCount == null)
                tagCount = 0;
            tagStat.put(tag, ++tagCount);
        }

        total++;
    }

    public void dumpBrief(IPrintOut out) {
        String ignores = "";
        if (ignored > 0)
            ignores = "(" + ignored + " ignored)";
        out.println(String.format("Total: %d/%d changed, %d ignored, %d saved, %d errors", //
                changed, total, ignores, saved, errorred));
    }

    static final int nameWidth = 40;

    void dumpField(IPrintOut out, int indent, String name, int value, Integer value2) {
        String tab = Strings.repeat(indent, ' ');
        out.print(tab);
        out.printf("%" + (nameWidth - indent) + "s: %8d file", name, value);
        if (value > 1)
            out.print('s');
        if (value2 != null) {
            out.printf("%8d file", value2);
            if (value2 > 1)
                out.print('s');
        }
        out.println();
    }

    void dumpField(IPrintOut out, int indent, String name, int value) {
        dumpField(out, indent, name, value, null);
    }

    public void dumpDetail(IPrintOut out) {
        dumpField(out, 4, "Total/Ignored", total, ignored);

        List<String> tags = new ArrayList<String>(tagStat.keySet());
        Collections.sort(tags);
        for (String tag : tags) {
            int count = tagStat.get(tag);
            dumpField(out, 8, tag, count);
        }

        if (changed > 0)
            dumpField(out, 4, "Changed", changed, total - changed);
        if (saved > 0)
            dumpField(out, 4, "Saved", saved, total - saved);
        if (deleted > 0)
            dumpField(out, 4, "Deleted", deleted, total - deleted);
        if (renamed > 0)
            dumpField(out, 4, "Renamed", renamed, total - renamed);
        if (moved > 0)
            dumpField(out, 4, "Moved", moved, total - moved);
        if (copied > 0)
            dumpField(out, 4, "Copied", copied, total - copied);
        if (errorred > 0)
            dumpField(out, 4, "Errors", errorred, total - errorred);

        List<Class<?>> errTypes = new ArrayList<Class<?>>(errorStat.keySet());
        Collections.sort(errTypes, TypeComparator.getInstance());
        for (Class<?> errType : errTypes) {
            int count = errorStat.get(errType);
            dumpField(out, 8, errType.getName(), count);
        }
    }

}
