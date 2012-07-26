package net.bodz.bas.cli.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.cli.skel.CLIException;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.util.PluginCategory;
import net.bodz.bas.util.PluginTypeEx;

public class CLIPluginCategory
        extends PluginCategory {

    public CLIPluginCategory(Class<? extends CLIPlugin> baseType) {
        super(baseType);
    }

    public CLIPluginCategory(String name, Class<? extends CLIPlugin> baseType) {
        super(name, baseType);
    }

    public void help(IPrintOut out, String prefix)
            throws CLIException {
        List<String> ids = new ArrayList<String>(registry.keySet());
        Collections.sort(ids);

        int maxlen = 0;
        for (String id : ids)
            if (id.length() > maxlen)
                maxlen = id.length();
        for (String id : ids) {
            PluginTypeEx pluginClass = registry.get(id);
            String desc = pluginClass.getDescription();
            out.print(prefix);
            out.print(id);
            out.print(": ");
            int delta = maxlen - id.length();
            out.print(Strings.repeat(delta, ' '));
            if (desc == null) {
                out.print(pluginClass);
            } else {
                out.print(desc);
            }
            out.println();

            try {
                CLIPlugin tmp = (CLIPlugin) pluginClass.newInstance();
                tmp.help(out, prefix + "    ");
            } catch (CreateException e) {
                out.println(prefix + "    (failed to instantiate: " + e + ")");
            }
        }
    }

}