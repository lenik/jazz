package net.bodz.lily.site.test;

import net.bodz.bas.meta.autowire.ProjectDependencies;
import net.bodz.bas.meta.autowire.ProjectName;
import net.bodz.lily.AbstractLilyWebApp;
import net.bodz.lily.JazzLilyProject;

@ProjectName("lilytest")
@ProjectDependencies(JazzLilyProject.class)
public class LilyTestModule
        extends AbstractLilyWebApp {

}
