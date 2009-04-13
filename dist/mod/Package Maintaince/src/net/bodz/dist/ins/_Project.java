package net.bodz.dist.ins;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.types.util.Ns;
import net.bodz.dist.ins.Schemes.Custom;
import net.bodz.dist.ins.Schemes.Maximum;
import net.bodz.dist.ins.Schemes.Minimum;
import net.bodz.dist.ins.Schemes.Standard;
import net.bodz.dist.ins.builtins.RequiredSection;
import net.bodz.dist.ins.lic.License;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.graphics.ImageData;

public class _Project extends RequiredSection implements IProject {

    private ImageData     logo;
    private String        version;
    private String        updateTime;
    private String        company;

    private List<BaseDir> baseDirs;

    public _Project(Class<?> clazz, IComponent... children) {
        super("root", "Project Root", children);
        if (clazz != null)
            loadInfo(clazz);
        this.baseDirs = new ArrayList<BaseDir>();
    }

    protected void loadInfo(Class<?> clazz) {
        LogoImage alogo = Ns.getN(clazz, LogoImage.class);
        if (alogo != null) {
            String respath = alogo.value();
            logo = SWTResources.getImageDataRes(clazz, respath);
        }
        ClassInfo info = ClassInfo.get(clazz);
        setName(clazz.getName());
        setText(A_bas.getDisplayName(getClass()));
        setDoc(info.getDoc());
        URL iconURL = info.getIcon();
        if (iconURL != null) {
            ImageData icon = SWTResources.getImageData(iconURL);
            setImage(icon);
        }
        version = info.getVersionString();
        company = info.getAuthor();
        updateTime = info.getDateString();
    }

    @Override
    public ImageData getLogo() {
        return logo;
    }

    public void setLogo(ImageData logo) {
        this.logo = logo;
    }

    public String getVersion() {
        return version;
    }

    public String getCompany() {
        return company;
    }

    public String getLicense() {
        return License.GPLv2;
    }

    public String getUpdateTime() {
        return updateTime;
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
    public BaseDir[] getBaseDirs() {
        return baseDirs.toArray(new BaseDir[0]);
    }

    protected BaseDir getBaseDir(String base) {
        assert base != null : "base";
        for (BaseDir baseDir : baseDirs) {
            if (base.equals(baseDir.getName()))
                return baseDir;
        }
        return null;
    }

    protected void addBaseDir(BaseDir baseDir) {
        baseDirs.add(baseDir);
    }

}
