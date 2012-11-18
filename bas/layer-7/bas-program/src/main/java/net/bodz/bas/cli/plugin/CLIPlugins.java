package net.bodz.bas.cli.plugin;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.l10n.en.English;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.util.PluginCategory;
import net.bodz.bas.util.Plugins;

public class CLIPlugins
        extends Plugins {

    @Override
    public boolean addCategory(PluginCategory category) {
        if (!(category instanceof CLIPluginCategory))
            throw new OutOfDomainException("category class", category.getClass(), CLIPluginCategory.class);
        return super.addCategory(category);
    }

    @Override
    public boolean addCategory(String name, Class<? extends ICLIPlugin> baseType) {
        if (!ICLIPlugin.class.isAssignableFrom(baseType))
            throw new OutOfDomainException("baseType", baseType, ICLIPlugin.class);
        CLIPluginCategory category = new CLIPluginCategory(name, baseType);
        return addCategory(category);
    }

    public void help(IPrintOut out, String prefix) {
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
