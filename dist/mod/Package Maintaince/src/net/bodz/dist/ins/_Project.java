package net.bodz.dist.ins;

import java.net.URL;
import java.util.Collection;

import net.bodz.bas.a.ClassInfo;
import net.bodz.dist.ins.Schemes.Custom;
import net.bodz.dist.ins.Schemes.Maximum;
import net.bodz.dist.ins.Schemes.Minimum;
import net.bodz.dist.ins.Schemes.Standard;
import net.bodz.dist.ins.lic.License;

public class _Project implements IProject {

    private String name;
    private String caption;
    private String description;
    private URL    iconURL;
    private String version;
    private String updateTime;
    private String company;

    // public String

    public void loadInfo(Class<?> clazz) {
        ClassInfo info = ClassInfo.get(clazz);
        name = clazz.getName();
        caption = clazz.getSimpleName();
        description = info.getDoc();
        // info.getAuthor();
        version = info.getVersionString();
        iconURL = info.getIcon();
        updateTime = info.getDateString();
        company = info.getAuthor();
    }

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
                new Minimum(), //
                new Standard(), //
                new Maximum(), //
                new Custom(), //
        };
        return commons;
    }

    @Override
    public Collection<IComponent> getRootComponents() {
        return null;
    }

}
