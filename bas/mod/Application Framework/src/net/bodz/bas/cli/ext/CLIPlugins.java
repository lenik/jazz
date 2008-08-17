package net.bodz.bas.cli.ext;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.mod.plugins.PluginCategory;
import net.bodz.bas.mod.plugins.Plugins;
import net.bodz.bas.text.locale.English;
import net.bodz.bas.types.util.Strings;

public class CLIPlugins extends Plugins {

    public void help(CharOut out, String prefix) throws CLIException {
        int maxlen = 0;
        // List<String> typeNames = new ArrayList<String>();
        for (PluginCategory pluginType : categories.values()) {
            String name = pluginType.getName();
            if (name.length() > maxlen)
                maxlen = name.length();
            // typeNames.add(name);
        }
        // Collections.sort(typeNames);

        for (PluginCategory category : categories.values()) {
            String name = category.getName();
            String desc = category.getDescription();

            if (category.size() > 1)
                name = English.pluralOf(name);
            name = Strings.ucfirst(name);
            out.println();
            out.print(prefix);
            out.print(name);
            out.print(": ");
            if (desc != null)
                out.print(desc);
            out.println();

            CLIPluginCategory cliCategory = (CLIPluginCategory) category;
            cliCategory.help(out, prefix + "    ");
        }
    }

}
