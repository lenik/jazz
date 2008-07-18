package net.bodz.bas.cli.ext;

import java.util.Map;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.mod.plugins.PluginType;
import net.bodz.bas.mod.plugins.Plugins;
import net.bodz.bas.text.locale.English;
import net.bodz.bas.types.util.Strings;

public class CLIPlugins extends Plugins {

    @SuppressWarnings("unchecked")
    protected Map<Class<?>, CLIPluginType<?>> getTypes() {
        return (Map<Class<?>, CLIPluginType<?>>) (Map<Class<?>, ?>) types;
    }

    @Override
    public <T> boolean registerPluginType(PluginType<T> pluginType) {
        assert pluginType instanceof CLIPluginType;
        return super.registerPluginType((CLIPluginType<?>) pluginType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> boolean registerPluginType(String name, Class<T> pluginTypeClass) {
        assert CLIPlugin.class.isAssignableFrom(pluginTypeClass);
        PluginType<T> pluginType = new CLIPluginType(name, pluginTypeClass);
        return registerPluginType(pluginType);
    }

    public void help(CharOut out, String prefix) throws CLIException {
        Map<Class<?>, CLIPluginType<?>> types = getTypes();

        int maxlen = 0;
        // List<String> typeNames = new ArrayList<String>();
        for (PluginType<?> pluginType : types.values()) {
            String name = pluginType.getName();
            if (name.length() > maxlen)
                maxlen = name.length();
            // typeNames.add(name);
        }
        // Collections.sort(typeNames);

        for (CLIPluginType<?> pluginType : types.values()) {
            String name = pluginType.getName();
            String desc = pluginType.getDescription();

            if (pluginType.size() > 1)
                name = English.pluralOf(name);
            name = Strings.ucfirst(name);
            out.println();
            out.print(prefix);
            out.print(name);
            out.print(": ");
            if (desc != null)
                out.print(desc);
            out.println();

            pluginType.help(out, prefix + "    ");
        }
    }
}
