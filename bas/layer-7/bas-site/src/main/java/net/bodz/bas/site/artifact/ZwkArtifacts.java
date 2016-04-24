package net.bodz.bas.site.artifact;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.MutableArtifactManager;
import net.bodz.bas.site.IBasicSiteAnchors;

public class ZwkArtifacts
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    public static int MENUS = 1;
    public static int TABS = 1;

    String version = "z";

    IArtifact zwkLib = group("zwkLib");
    IArtifact zwkMenus = group("zwkMenu");
    IArtifact zwkTabs = group("zwkTab");

    {
        for (int i = 1; i <= MENUS; i++) {
            String name = "ZwkMenu" + i;
            IArtifact item = item(name);
            zwkMenus.addDependency(item);
        }

        for (int i = 1; i <= TABS; i++) {
            String name = "ZwkTab" + i;
            IArtifact item = item(name);
            zwkTabs.addDependency(item);
        }

        zwkLib.addDependency(zwkMenus);
        zwkLib.addDependency(zwkTabs);
    }

    IArtifact item(String name) {
        IArtifact css = css(name + ".css", version, _js_.join("zwk/" + name + ".css"));
        IArtifact js = javascript(name + ".js", version, _js_.join("zwk/" + name + ".js"));
        js.addDependency(css);
        js.addDependency(LibJsArtifacts.JQUERY_MIN);
        return group(name).addDependency(js);
    }

}
