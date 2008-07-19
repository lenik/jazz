package net.bodz.bas.cli.ext;

import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.annotations.ClassInfo;
import net.bodz.bas.cli.CLIException;
import net.bodz.bas.cli.ClassCLI;
import net.bodz.bas.cli.ClassOptions;
import net.bodz.bas.cli._Option;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.util.Strings;

public class _CLIPlugin implements CLIPlugin {

    private final String description;

    public _CLIPlugin() {
        ClassInfo info = ClassInfo.get(getClass());
        description = info.getDoc();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unchecked")
    protected ClassOptions<_CLIPlugin> getOptions() {
        return (ClassOptions<_CLIPlugin>) ClassCLI.getClassOptions(getClass());
    }

    @Override
    public void setParameters(Map<String, Object> parameters)
            throws CLIException, ParseException {
        if (parameters.isEmpty())
            return;
        ClassOptions<_CLIPlugin> opts = getOptions();
        opts.load(this, parameters);
    }

    @Override
    public void help(CharOut out, String prefix) {
        ClassOptions<_CLIPlugin> opts = getOptions();
        TreeMap<String, _Option<?>> map = opts.getOptions();
        int maxlen = 0;
        for (String name : map.keySet())
            if (name.length() > maxlen)
                maxlen = name.length();
        for (Map.Entry<String, _Option<?>> e : map.entrySet()) {
            String name = e.getKey();
            _Option<?> opt = e.getValue();
            // String vnam = opt.o.vnam();
            String doc = opt.o.doc();

            out.print(prefix);
            out.print(name);
            if (!doc.isEmpty()) {
                out.print(": ");
                if (name.length() < maxlen)
                    out.print(Strings.repeat(maxlen - name.length(), ' '));
                out.print(doc);
            }
            out.println();
        }
    }

}
