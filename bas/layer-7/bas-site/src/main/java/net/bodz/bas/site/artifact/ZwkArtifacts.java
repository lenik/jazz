package net.bodz.bas.site.artifact;

import net.bodz.bas.html.artifact.ArtifactBuilder;
import net.bodz.bas.html.artifact.Group;
import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.IMutableArtifact;
import net.bodz.bas.html.artifact.StaticArtifactProvider;
import net.bodz.bas.site.IBasicSiteAnchors;

public class ZwkArtifacts
        extends StaticArtifactProvider
        implements IBasicSiteAnchors {

    public static int DIALOGS = 1;
    public static int LISTS = 1;
    public static int MENUS = 1;
    public static int TABS = 1;
    public static int TREES = 1;

    static final ArtifactBuilder fn = new ArtifactBuilder();

    public ZwkArtifacts() {
        super(fn.getContainer());
    }

    public static Group zwkLib = fn.group("zwkLib");

    public static ZwkPackage zwkDialogs = pkg("zwkDialogs");
    public static ZwkPackage zwkLists = pkg("zwkLists");
    public static ZwkPackage zwkMenus = pkg("zwkMenus");
    public static ZwkPackage zwkTabs = pkg("zwkTabs");
    public static ZwkPackage zwkTrees = pkg("zwkTrees");

    {
        for (int i = 1; i <= DIALOGS; i++) {
            String name = "ZwkDialog" + i;
            IArtifact item = item(name);
            zwkDialogs.addChild(item);
        }

        for (int i = 1; i <= LISTS; i++) {
            String name = "ZwkList" + i;
            IArtifact item = item(name);
            zwkLists.addChild(item);
        }

        for (int i = 1; i <= MENUS; i++) {
            String name = "ZwkMenu" + i;
            IArtifact item = item(name);
            zwkMenus.addChild(item);
        }

        for (int i = 1; i <= TABS; i++) {
            String name = "ZwkTab" + i;
            IArtifact item = item(name);
            zwkTabs.addChild(item);
        }

        for (int i = 1; i <= TREES; i++) {
            String name = "ZwkTree" + i;
            IArtifact item = item(name);
            zwkTrees.addChild(item);
        }

        zwkLib.dependsOn(zwkDialogs);
        zwkLib.dependsOn(zwkLists);
        zwkLib.dependsOn(zwkMenus);
        zwkLib.dependsOn(zwkTabs);
        zwkLib.dependsOn(zwkTrees);
    }

    public static ZwkPackage pkg(String name) {
        ZwkPackage pkg = new ZwkPackage(name);
        fn.getContainer().addArtifact(pkg);
        return pkg;
    }

    public static Group item(String name) {
        IMutableArtifact css = fn.css(name + ".css", "x", _js_.join("zwk/" + name + ".css"));
        IMutableArtifact js = fn.javascript(name + ".js", "x", _js_.join("zwk/" + name + ".js"));
        js.addDependency(css);
        js.addDependency(LibJsArtifacts.jQuery);
        Group group = fn.group(name);
        group.addChild(js);
        return group;
    }

}
