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

    public void initFrom(EsmSource source) {
        this.from = source;
        defaultAlias = null;
        names.clear();
        typeNames.clear();
    }

    public int printOutTypeScriptForNewFrom(IPrintOut out, EsmSource source) {
        int lines = 0;
        if (from != source) {
            int fromPriority = -1;
            if (from != null) {
                fromPriority = from.module.priority;
                lines += printOutTypeScript(out);
            }

            if (fromPriority != -1 && source != null) {
                int nextPriority = source.module.priority;
                if (nextPriority != fromPriority)
                    out.println(); // group separator
            }

            initFrom(source);
        }
        return lines;
    }

    public int printOutTypeScript(IPrintOut out) {
        String path = from.getFullPath();
        String pathQuoted = StringQuote.qqJavaString(path);
        int lines = 0;

        if (defaultAlias != null) {
            out.printf("import %s from %s;\n", defaultAlias, pathQuoted);
            lines++;
        }

        if (! names.isEmpty()) {
            out.print("import ");
            printNames(out, names);
            out.print(" from ");
            out.print(pathQuoted);
            out.println(";");
            lines++;
        }

        if (! typeNames.isEmpty()) {
            out.print("import type ");
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
