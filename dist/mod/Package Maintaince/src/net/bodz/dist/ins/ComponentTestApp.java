package net.bodz.dist.ins;

import net.bodz.bas.a.DisplayName;
import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.util.LogTerms;
import net.bodz.dist.ins.builtins.SimpleProject;

@DisplayName("Component Test Application")
@Doc("A test program for z.dist components")
@RcsKeywords(id = "$Id$")
@Version( { 1, 2, 3 })
public class ComponentTestApp extends ConsoleExecutor {

    public ComponentTestApp() {
        super(new SimpleProject(ComponentTestApp.class), LogTerms.console);
    }

}
