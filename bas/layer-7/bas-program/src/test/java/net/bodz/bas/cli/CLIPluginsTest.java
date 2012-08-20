package net.bodz.bas.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.bodz.bas.cli.plugin.AbstractCLIPlugin;
import net.bodz.bas.cli.plugin.CLIPlugins;

public class CLIPluginsTest {

    static class Ext
            extends AbstractCLIPlugin {
        private String name;

        public Ext(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Test
    public void testRegister()
            throws Throwable {
        CLIPlugins plugins = new CLIPlugins();
        Ext a = new Ext("ext-A");
        Ext b = new Ext("ext-B");
        plugins.register("a", a);
        plugins.register("ab", a);
        plugins.register("b", b);

        assertEquals(a, plugins.load(Ext.class, "a"));
        assertEquals(a, plugins.load(Ext.class, "ab"));
        assertEquals(b, plugins.load(Ext.class, "b"));
    }

}
