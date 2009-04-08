package net.bodz.dist.ins;

import java.io.File;
import java.net.URL;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.Schemes.Custom;
import net.bodz.dist.ins.Schemes.Maximum;
import net.bodz.dist.ins.Schemes.Minimum;
import net.bodz.dist.ins.Schemes.Standard;
import net.bodz.dist.ins.builtins.Section;
import net.bodz.dist.ins.lic.License;

public class _Project extends Section implements IProject {

    private String  name;
    private String  caption;
    private String  description;
    private URL     iconURL;
    private String  version;
    private String  updateTime;
    private String  company;

    private BaseDir programBase;

    public _Project(Class<?> clazz) {
        super(true, null, null, null);
        if (clazz != null)
            loadInfo(clazz);

        String parent = "/usr/local";
        String name = getName();
        if (SystemInfo.isWin32()) {
            parent = System.getenv("ProgramFiles");
            if (parent == null)
                parent = "C:/Program Files";
        }
        File dir = new File(parent, name);
        programBase = new BaseDir("program", "Program Files",
                "Where do you want to put the program files", dir);
    }

    public void loadInfo(Class<?> clazz) {
        ClassInfo info = ClassInfo.get(clazz);
        name = clazz.getName();
        caption = A_bas.getDisplayName(getClass());
        description = info.getDoc();
        // info.getAuthor();
        version = info.getVersionString();
        iconURL = info.getIcon();
        updateTime = info.getDateString();
        company = info.getAuthor();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public URL getIconURL() {
        return iconURL;
    }

    public String getVersion() {
        return version;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getCompany() {
        return company;
    }

    public String getLicense() {
        return License.GPLv2;
    }

    public Scheme[] getSchemes() {
        Scheme[] commons = {
        //
                new Standard(), //
                new Minimum(), //
                new Maximum(), //
                new Custom(), //
        };
        return commons;
    }

    @Override
    public void enter(ISession session) {
        super.enter(session);
        // do project specific?
    }

    @Override
    public void leave(ISession session) {
        super.leave(session);
        // do project specific?
    }

    @Override
    public BaseDir[] getBaseDirs() {
        BaseDir[] defaultBaseDirs = { programBase, };
        return defaultBaseDirs;
    }

}
