package net.bodz.bas.cli.ext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.mod.CreateException;
import net.bodz.bas.mod.plugins.PluginClass;
import net.bodz.bas.mod.plugins.PluginType;
import net.bodz.bas.types.util.Strings;

public class CLIPluginType<T extends CLIPlugin> extends PluginType<T> {

    public CLIPluginType(Class<T> type) {
        super(type);
    }

    public CLIPluginType(String name, Class<T> type) {
        super(name, type);
    }

    public void help(CharOut out, String prefix) throws CLIException {
        List<String> ids = new ArrayList<String>(registry.keySet());
        Collections.sort(ids);

        int maxlen = 0;
        for (String id : ids)
            if (id.length() > maxlen)
                maxlen = id.length();
        for (String id : ids) {
            PluginClass<T> pluginClass = registry.get(id);
            String desc = pluginClass.getDescription();
            out.print(prefix);
            out.print(id);
            if (desc != null) {
                out.print(": ");
                int delta = maxlen - id.length();
                out.print(Strings.repeat(delta, ' '));
            }
            out.println();

            try {
                T tmp = pluginClass.newInstance();
                tmp.help(out, prefix + "    ");
            } catch (CreateException e) {
                out.println(prefix + "    (failed to instantiate: " + e + ")");
            }
        }
    }

}
