package net.bodz.dist.ins;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.graphics.ImageData;

public class _Project extends RequiredSection implements Project {

    private ImageData logo;
    private String    version;
    private String    updateTime;
    private String    company;

    public _Project(Class<?> clazz, Component... children) {
        super("root", PackNLS.getString("_Project.projectRoot"), children); //$NON-NLS-1$ //$NON-NLS-2$
        if (clazz != null)
            loadInfo(clazz);
        this.variables = new TreeTextMap<Variable>();
    }

    @Override
    public double getProgressScaleToParent() {
        return super.getProgressScaleToParent();
    }

    protected void loadInfo(Class<?> clazz) {
        LogoImage alogo = Ns.getN(clazz, LogoImage.class);
        if (alogo != null) {
            String respath = alogo.value();
            logo = SWTResources.getImageDataRes(clazz, respath);
        }
        ClassInfo info = ClassInfo.get(clazz);
        setName(clazz.getName());
        setText(A_bas.getDisplayName(clazz));
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
                new Default(), //
                new Minimum(), //
                new Maximum(), //
                new Custom(), //
        };
        return commons;
    }

    private TextMap<Variable>              variables;
    private Map<Component, Set<Component>> bydeps;

    @Override
    public TextMap<Variable> getVariables() {
        return variables;
    }

    @Override
    public Variable get(String variableName) {
        return variables.get(variableName);
    }

    @Override
    public BaseDirVariable getBaseDir(String variableName) {
        return (BaseDirVariable) variables.get(variableName);
    }

    public void define(String name, Variable variable) {
        variables.put(name, variable);
    }

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
    public void findDependents(Component parent, TreeCallback<Component> callback) {
        IdentSet uniq = new IdentSet();
        findDependents(parent, callback, 0, uniq);
    }

    boolean findDependents(Component parent, TreeCallback<Component> callback, int level,
            IdentSet uniq) {
        if (parent == null)
            throw new NullPointerException("null component in level " + level); //$NON-NLS-1$
        if (uniq.contains(parent))
            throw new IllegalStateException(PackNLS.getString("_Project.loopDetected") + parent); //$NON-NLS-1$
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
    public void findDependentsBy(Component child, TreeCallback<Component> callback) {
        if (bydeps == null)
            refreshDependants();
        IdentSet uniq = new IdentSet();
        findDependentsBy(child, callback, 0, uniq);
    }

    boolean findDependentsBy(Component child, TreeCallback<Component> callback, int level,
            IdentSet uniq) {
        if (child == null)
            throw new NullPointerException("null component in rev-level " + level); //$NON-NLS-1$
        if (uniq.contains(child))
            throw new IllegalStateException(PackNLS.getString("_Project.loopDetected") + child); //$NON-NLS-1$
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

    boolean analyseDependency(Component child, TreeCallback<Component> missingCallback, int level,
            IdentSet uniq) {
        if (uniq.contains(child))
            throw new IllegalStateException(PackNLS.getString("_Project.loopDetected") + child); //$NON-NLS-1$
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
