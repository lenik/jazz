package net.bodz.bas.cli.ext;

import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.cli.CLIException;
import net.bodz.bas.cli.ClassCLI;
import net.bodz.bas.cli.opt.AbstractOption;
import net.bodz.bas.cli.opt.IOption;
import net.bodz.bas.cli.opt.DefaultOptionGroup;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.util._Plugin;

public class _CLIPlugin
        extends _Plugin
        implements CLIPlugin {

    protected DefaultOptionGroup getOptions() {
        return (DefaultOptionGroup) ClassCLI.getClassOptions(getClass());
    }

    @Override
    public void setParameters(Map<String, Object> parameters)
            throws CLIException, ParseException {
        if (parameters.isEmpty())
            return;
        DefaultOptionGroup opts = getOptions();
        opts.load(this, parameters);
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    @Override
    public void initialize() {
        super.initialize();
        DefaultOptionGroup opts = getOptions();
        Map<?, ?> properties = System.getProperties();
        try {
            opts.load(this, (Map<String, ?>) properties);
        } catch (CLIException e) {
            throw new Error(e.getMessage(), e);
        } catch (ParseException e) {
            throw new Error(e.getMessage(), e);
        }
    }

    @Override
    public void help(IPrintOut out, String prefix) {
        DefaultOptionGroup opts = getOptions();
        TreeMap<String, IOption> map = opts.getOptions();
        int maxlen = 0;
        for (String name : map.keySet())
            if (name.length() > maxlen)
                maxlen = name.length();
        for (Map.Entry<String, IOption> e : map.entrySet()) {
            String name = e.getKey();
            IOption opt = e.getValue();
            // String vnam = opt.o.vnam();
            String description = opt.getDescription();

            out.print(prefix);
            out.print(name);
            if (!description.isEmpty()) {
                out.print(": ");
                if (name.length() < maxlen)
                    out.print(Strings.repeat(maxlen - name.length(), ' '));
                out.print(description);
            }
            out.println();
        }
    }

}
