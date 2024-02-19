package net.bodz.bas.esm;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.io.IPrintOut;

public class ImportStatement {

    public String defaultAlias;
    public List<EsmName> names = new ArrayList<>();
    public List<EsmName> typeNames = new ArrayList<>();
    public EsmSource from;

    boolean debug = false; // true;

    public void initFrom(EsmSource source) {
        this.from = source;
        defaultAlias = null;
        names.clear();
        typeNames.clear();
    }

    public int printOutTypeScriptForNewFrom(IPrintOut out, EsmSource source) {
        int lines = 0;
        if (from != source) {
            int lastPriority = -10000;
            if (from != null) {
                lastPriority = from.module.priority;
                lines += printOutTypeScript(out);
            }

            if (lastPriority != -1 && source != null) {
                int nextPriority = source.module.priority;
                int sepDiv = 100;
                if (nextPriority / sepDiv != lastPriority / sepDiv) {
                    if (lastPriority >= 0)
                        out.println(); // group separator
                    lastPriority = nextPriority;
                }
            }

            initFrom(source);
        }
        return lines;
    }

    public int printOutTypeScript(IPrintOut out) {
        String path = from.getFullPath();
        String pathQuoted = StringQuote.qqJavaString(path);
        int lines = 0;

        String _import = "import";
        if (debug)
            _import = String.format("/* %d */ import", from.module.getPriority());

        if (defaultAlias != null) {
            out.printf(_import);
            out.printf(" %s from %s;\n", defaultAlias, pathQuoted);
            lines++;
        }

        if (! names.isEmpty()) {
            out.print(_import);
            out.print(" ");
            printNames(out, names);
            out.print(" from ");
            out.print(pathQuoted);
            out.println(";");
            lines++;
        }

        if (! typeNames.isEmpty()) {
            out.print(_import);
            out.print(" type ");
            printNames(out, typeNames);
            out.print(" from ");
            out.print(pathQuoted);
            out.println(";");
            lines++;
        }
        return lines;
    }

    void printNames(IPrintOut out, List<EsmName> list) {
        int n = list.size();
        int pos = 0;
        int wilds = 0;
        for (int i = 0; i < n; i++) {
            EsmName name = list.get(i);
            if (name.isWildcard() && name.alias != null) {
                if (pos++ != 0)
                    out.print(", ");
                out.print("* as ");
                out.print(name.alias);
                wilds++;
            }
        }

        if (n == wilds)
            return;

        if (pos != 0)
            out.print(", ");

        out.print("{ ");
        pos = 0;
        for (int i = 0; i < n; i++) {
            EsmName name = list.get(i);
            if (name.isWildcard())
                continue;
            if (pos++ != 0)
                out.print(", ");
            if (name.name != null)
                out.print(name.name);
            if (name.name != null && name.alias != null)
                out.print(" as ");
            if (name.alias != null)
                out.print(name.alias);
        }
        out.print(" }");
    }

}
