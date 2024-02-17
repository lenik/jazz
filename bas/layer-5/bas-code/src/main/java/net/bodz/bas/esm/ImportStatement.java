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

    boolean debug = false; //true;

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
        out.print("{ ");
        for (int i = 0; i < n; i++) {
            if (i != 0)
                out.print(", ");
            EsmName name = list.get(i);
            if (name.name != null)
                out.print(name.name);
            if (name.alias != null) {
                if (name.name != null)
                    out.print(" as ");
                out.print(name.alias);
            }
        }
        out.print(" }");
    }

}
