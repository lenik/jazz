package net.bodz.bas.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.bas.types.TypeMap;
import net.bodz.bas.types.TypeMap.HashTypeMap;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Strings;

public class ProcessResultStat {

    private int ignored;
    private int total;
    private int changed;
    private int saved;
    private int deleted;
    private int renamed;
    private int moved;
    private int copied;
    private int errorred;

    private TextMap<Integer> tagStat;
    private TypeMap<Integer> errStat;

    public ProcessResultStat() {
        tagStat = new TreeTextMap<Integer>();
        errStat = new HashTypeMap<Integer>();
    }

    public void add(EditResult result) {
        if (result == null) {
            ignored++;
            return;
        }
        total++;
        if (result.changed != null) {
            if (result.changed)
                changed++;
        }
        if (result.done)
            switch (result.operation) {
            case EditResult.SAVE:
                throw new IllegalStateException();
            case EditResult.SAVE_DIFF:
            case EditResult.SAVE_SAME:
                saved++;
            case EditResult.DELETE:
                deleted++;
                break;
            case EditResult.RENAME:
                renamed++;
                break;
            case EditResult.MOVE:
                moved++;
                break;
            case EditResult.COPY:
                copied++;
                break;
            default:
                throw new IllegalArgumentException(AppNLS.getString("ProcessResultStat.unknownOperation") //$NON-NLS-1$
                        + result.operation);
            }

        if (result.tags != null)
            for (String tag : result.tags) {
                Integer tagCount = tagStat.get(tag);
                if (tagCount == null)
                    tagCount = 0;
                tagStat.put(tag, ++tagCount);
            }
        if (result.error) {
            errorred++;
            if (result.cause != null) {
                Class<? extends Throwable> errType = result.cause.getClass();
                Integer errCount = errStat.get(errType);
                if (errCount == null)
                    errCount = 0;
                errStat.put(errType, ++errCount);
            }
        }
    }

    public void dumpBrief(CharOut out) {
        String ignores = ""; //$NON-NLS-1$
        if (ignored > 0)
            ignores = "(" + ignored + AppNLS.getString("ProcessResultStat.ignored_"); //$NON-NLS-1$ //$NON-NLS-2$
        out.println(AppNLS.format("ProcessResultStat.stat_ddsdd", //
                changed, total, ignores, saved, errorred));
    }

    static final int nameWidth = 40;

    void dumpField(CharOut out, int indent, String name, int value, Integer value2) {
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

    void dumpField(CharOut out, int indent, String name, int value) {
        dumpField(out, indent, name, value, null);
    }

    public void dumpDetail(CharOut out) {
        dumpField(out, 4, AppNLS.getString("ProcessResultStat.total_Ignored"), total, ignored); //$NON-NLS-1$

        List<String> tags = new ArrayList<String>(tagStat.keySet());
        Collections.sort(tags);
        for (String tag : tags) {
            int count = tagStat.get(tag);
            dumpField(out, 8, tag, count);
        }

        if (changed > 0)
            dumpField(out, 4, AppNLS.getString("ProcessResultStat.changed"), changed, total - changed); //$NON-NLS-1$
        if (saved > 0)
            dumpField(out, 4, AppNLS.getString("ProcessResultStat.saved"), saved, total - saved); //$NON-NLS-1$
        if (deleted > 0)
            dumpField(out, 4, AppNLS.getString("ProcessResultStat.deleted"), deleted, total - deleted); //$NON-NLS-1$
        if (renamed > 0)
            dumpField(out, 4, AppNLS.getString("ProcessResultStat.renamed"), renamed, total - renamed); //$NON-NLS-1$
        if (moved > 0)
            dumpField(out, 4, AppNLS.getString("ProcessResultStat.moved"), moved, total - moved); //$NON-NLS-1$
        if (copied > 0)
            dumpField(out, 4, AppNLS.getString("ProcessResultStat.copied"), copied, total - copied); //$NON-NLS-1$
        if (errorred > 0)
            dumpField(out, 4, AppNLS.getString("ProcessResultStat.errors"), errorred, total - errorred); //$NON-NLS-1$

        List<Class<?>> errTypes = new ArrayList<Class<?>>(errStat.keySet());
        Collections.sort(errTypes, Comparators.TYPE);
        for (Class<?> errType : errTypes) {
            int count = errStat.get(errType);
            dumpField(out, 8, errType.getName(), count);
        }
    }

}
