package net.bodz.bas.cli.ext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.mod.plugins.PluginCategory;
import net.bodz.bas.mod.plugins.PluginTypeEx;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.Strings;

public class CLIPluginCategory extends PluginCategory {

    public CLIPluginCategory(Class<? extends CLIPlugin> baseType) {
        super(baseType);
    }

    public CLIPluginCategory(String name, Class<? extends CLIPlugin> baseType) {
        super(name, baseType);
    }

    public void help(CharOut out, String prefix) throws CLIException {
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
            out.print(": "); //$NON-NLS-1$
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
                tmp.help(out, prefix + "    "); //$NON-NLS-1$
            } catch (CreateException e) {
                out
                        .println(prefix
                                + AppNLS
                                        .getString("CLIPluginCategory.errNewInst") + e + ")"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
    }

}
