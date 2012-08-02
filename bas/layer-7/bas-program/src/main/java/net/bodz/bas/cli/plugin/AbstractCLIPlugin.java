package net.bodz.bas.cli.plugin;

import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.cli.ClassOptions;
import net.bodz.bas.cli.model.DefaultOptionGroup;
import net.bodz.bas.cli.model.IOption;
import net.bodz.bas.cli.model.IOptionGroup;
import net.bodz.bas.cli.skel.CLIException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.util.AbstractPlugin;

public class AbstractCLIPlugin
        extends AbstractPlugin
        implements CLIPlugin {

    protected IOptionGroup getOptions() {
        return ClassOptions.getClassOptions(getClass());
    }

    @Override
    public void setParameters(Map<String, Object> parameters)
            throws CLIException, ParseException {
        if (parameters.isEmpty())
            return;
        IOptionGroup group = getOptions();
        group.load(this, parameters);
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    @Override
    public void initialize() {
        super.initialize();
        IOptionGroup opts = getOptions();
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
        IOptionGroup opts = getOptions();
        Map<String, IOption> map = opts.getLocalOptionMap();
        int maxlen = 0;
        for (String name : map.keySet())
            if (name.length() > maxlen)
                maxlen = name.length();
        for (Map.Entry<String, IOption> e : map.entrySet()) {
            String name = e.getKey();
            IOption opt = e.getValue();
            // String vnam = opt.o.vnam();
            String description = opt.getDescription().toString();

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
