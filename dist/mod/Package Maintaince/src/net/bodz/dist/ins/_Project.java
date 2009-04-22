package net.bodz.dist.ins;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeCallback;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.bas.types.util.IdentSet;
import net.bodz.bas.types.util.Ns;
import net.bodz.dist.ins.Schemes.Custom;
import net.bodz.dist.ins.Schemes.Default;
import net.bodz.dist.ins.Schemes.Maximum;
import net.bodz.dist.ins.Schemes.Minimum;
import net.bodz.dist.ins.builtins.RequiredSection;
import net.bodz.dist.ins.lic.License;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.graphics.ImageData;

public class _Project extends RequiredSection implements Project {

    private ImageData       logo;
    private String          version;
    private String          updateTime;
    private String          company;

    private List<BaseDir>   baseDirs;
    private TextMap<Object> env;

    public _Project(Class<?> clazz, Component... children) {
        super("root", "Project Root", children);
        if (clazz != null)
            loadInfo(clazz);
        this.baseDirs = new ArrayList<BaseDir>();
        this.env = new TreeTextMap<Object>();
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
                new Minimum(), //
                new Default(), //
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

    @Override
    public TextMap<Object> getEnv() {
        return env;
    }

    public void setEnv(String key, Object defaultValue) {
        env.put(key, defaultValue);
    }

    Map<Component, Set<Component>> bydeps;

    @Override
    public void refreshDependants() {
        bydeps = new HashMap<Component, Set<Component>>();
        reverseDependants(this);
    }

    void reverseDependants(Component parent) {
        Collection<Component> children = parent.getDependancy();
        for (Component child : children) {
            Set<Component> parentList = bydeps.get(child);
            if (parentList == null) {
                parentList = new HashSet<Component>(1);
                bydeps.put(child, parentList);
            }
            parentList.add(parent);
            reverseDependants(child);
        }
    }

    @Override
    public void findDependents(Component parent,
            TreeCallback<Component> callback) {
        IdentSet uniq = new IdentSet();
        findDependents(parent, callback, 0, uniq);
    }

    boolean findDependents(Component parent, TreeCallback<Component> callback,
            int level, IdentSet uniq) {
        if (parent == null)
            throw new NullPointerException("null component in level " + level);
        if (uniq.contains(parent))
            throw new IllegalStateException("Loop detected: " + parent);
        uniq.add(parent);
        Collection<Component> children = parent.getDependancy();
        if (children == null)
            return true;
        L: for (Component child : children) {
            switch (callback.each(child, level)) {
            case TreeCallback.OK:
                break;
            case TreeCallback.BREAK:
                break L;
            case TreeCallback.CANCEL:
                return false;
            }
            if (!findDependents(child, callback, level + 1, uniq))
                return false; // canceled
        }
        return true;
    }

    @Override
    public void findDependentsBy(Component child,
            TreeCallback<Component> callback) {
        if (bydeps == null)
            refreshDependants();
        IdentSet uniq = new IdentSet();
        findDependentsBy(child, callback, 0, uniq);
    }

    boolean findDependentsBy(Component child, TreeCallback<Component> callback,
            int level, IdentSet uniq) {
        if (child == null)
            throw new NullPointerException("null component in rev-level "
                    + level);
        if (uniq.contains(child))
            throw new IllegalStateException("Loop detected: " + child);
        uniq.add(child);
        Set<Component> parents = bydeps.get(child);
        if (parents == null)
            return true;
        L: for (Component parent : parents) {
            switch (callback.each(parent, level)) {
            case TreeCallback.OK:
                break;
            case TreeCallback.BREAK:
                break L;
            case TreeCallback.CANCEL:
                return false;
            }
            if (!findDependentsBy(parent, callback, level + 1, uniq))
                return false; // canceled
        }
        return true;
    }

    @Override
    public void analyseDependency(TreeCallback<Component> missingCallback) {
        IdentSet uniq = new IdentSet();
        analyseDependency(this, missingCallback, 0, uniq);
    }

    boolean analyseDependency(Component child,
            TreeCallback<Component> missingCallback, int level, IdentSet uniq) {
        if (uniq.contains(child))
            throw new IllegalStateException("Loop detected: " + child);
        uniq.add(child);
        Collection<Component> dependancy = child.getDependancy();
        if (dependancy == null)
            return true;
        L: for (Component parent : dependancy) {
            if (!parent.getSelection())
                switch (missingCallback.each(parent, level)) {
                case TreeCallback.OK:
                    break;
                case TreeCallback.BREAK:
                    break L;
                case TreeCallback.CANCEL:
                    return false;
                }
            if (!analyseDependency(parent, missingCallback, level + 1, uniq))
                return false; // canceled
        }
        return true;
    }

}
