package net.bodz.bas.cli;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.cli.ext.CLIPlugins;
import net.bodz.bas.cli.ext._CLIPlugin;

import org.junit.Test;

public class CLIPluginsTest {

    static class Ext extends _CLIPlugin {
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
    public void testRegister() throws Throwable {
        CLIPlugins plugins = new CLIPlugins();
        Ext a = new Ext("ext-A"); //$NON-NLS-1$
        Ext b = new Ext("ext-B"); //$NON-NLS-1$
        plugins.register("a", a); //$NON-NLS-1$
        plugins.register("ab", a); //$NON-NLS-1$
        plugins.register("b", b); //$NON-NLS-1$

        assertEquals(a, plugins.load(Ext.class, "a")); //$NON-NLS-1$
        assertEquals(a, plugins.load(Ext.class, "ab")); //$NON-NLS-1$
        assertEquals(b, plugins.load(Ext.class, "b")); //$NON-NLS-1$
    }

}
