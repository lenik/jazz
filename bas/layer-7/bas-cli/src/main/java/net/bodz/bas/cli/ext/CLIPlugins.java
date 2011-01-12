package net.bodz.bas.cli.ext;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.l10n.en.English;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.string.Strings;
import net.bodz.bas.util.Plugin;
import net.bodz.bas.util.PluginCategory;
import net.bodz.bas.util.Plugins;
import net.bodz.bas.util.exception.OutOfDomainException;

public class CLIPlugins
        extends Plugins {

    @Override
    public boolean registerCategory(PluginCategory category) {
        if (!(category instanceof CLIPluginCategory))
            throw new OutOfDomainException("category class", category.getClass(), CLIPluginCategory.class);
        return super.registerCategory(category);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean registerCategory(String name, Class<? extends Plugin> baseType) {
        if (!CLIPlugin.class.isAssignableFrom(baseType))
            throw new OutOfDomainException("baseType", baseType, CLIPlugin.class);
        CLIPluginCategory category = new CLIPluginCategory(name, (Class<? extends CLIPlugin>) baseType);
        return registerCategory(category);
    }

    public void help(IPrintOut out, String prefix)
            throws CLIException {
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
