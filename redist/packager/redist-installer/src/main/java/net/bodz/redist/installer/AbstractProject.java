package net.bodz.redist.installer;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.graphics.ImageData;

import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.c.java.util.TreeTextMap;
import net.bodz.bas.c.object.IdentityObjectSet;
import net.bodz.bas.collection.tree.TreeCallback;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;
import net.bodz.mda.xjdoc.model1.Author;
import net.bodz.redist.installer.Schemes.Custom;
import net.bodz.redist.installer.Schemes.Default;
import net.bodz.redist.installer.Schemes.Maximum;
import net.bodz.redist.installer.Schemes.Minimum;
import net.bodz.redist.installer.builtins.RequiredSection;
import net.bodz.redist.installer.lic.License;
import net.bodz.swt.c.resources.SWTResources;

public class AbstractProject
        extends RequiredSection
        implements IProject {

    private ImageData logo;
    private IVersion version;
    private String updateTime;
    private Author company;

    public AbstractProject(Class<?> clazz, IComponent... children) {
        super("root", tr._("Project Root"), children);
        if (clazz != null)
            loadInfo(clazz);
        this.variables = new TreeTextMap<Variable>();
    }

    @Override
    public double getProgressScaleToParent() {
        return super.getProgressScaleToParent();
    }

    protected void loadInfo(Class<?> clazz) {
        LogoImage alogo = clazz.getAnnotation(LogoImage.class);
        if (alogo != null) {
            String respath = alogo.value();
            logo = SWTResources.getImageDataRes(clazz, respath);
        }

        ArtifactDoc artifactDoc = ClassDocs.loadFromResource(clazz).as(ArtifactDoc.class);

        setName(clazz.getName());
        setText(artifactDoc.getLabel().toString());
        setDoc(artifactDoc.getText().getHeadPar());
        URL iconURL = artifactDoc.getIcon();
        if (iconURL != null) {
            ImageData icon = SWTResources.getImageData(iconURL);
            setImage(icon);
        }
        version = artifactDoc.getVersion();
        company = artifactDoc.getAuthor();
        updateTime = artifactDoc.getReleaseDateString();
    }

    @Override
    public ImageData getLogo() {
        return logo;
    }

    public void setLogo(ImageData logo) {
        this.logo = logo;
    }

    @Override
    public IVersion getVersion() {
        return version;
    }

    @Override
    public Author getCompany() {
        return company;
    }

    @Override
    public String getLicense() {
        return License.GPLv2;
    }

    @Override
    public String getUpdateTime() {
        return updateTime;
    }

    @Override
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

    private TextMap<Variable> variables;
    private Map<IComponent, Set<IComponent>> bydeps;

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

    public void define(String name, String value) {
        Variable var = new Variable(null, value);
        variables.put(name, var);
    }

    public void define(String name, File value) {
        Variable var = new Variable(null, value);
        variables.put(name, var);
    }

    @Override
    public void refreshDependants() {
        bydeps = new HashMap<IComponent, Set<IComponent>>();
        reverseDependants(this);
    }

    void reverseDependants(IComponent parent) {
        Collection<IComponent> children = parent.getDependancy();
        for (IComponent child : children) {
            Set<IComponent> parentList = bydeps.get(child);
            if (parentList == null) {
                parentList = new HashSet<IComponent>(1);
                bydeps.put(child, parentList);
            }
            parentList.add(parent);
            reverseDependants(child);
        }
    }

    @Override
    public void findDependents(IComponent parent, TreeCallback<IComponent> callback) {
        IdentityObjectSet uniq = new IdentityObjectSet();
        findDependents(parent, callback, 0, uniq);
    }

    boolean findDependents(IComponent parent, TreeCallback<IComponent> callback, int level, IdentityObjectSet uniq) {
        if (parent == null)
            throw new NullPointerException("null component in level " + level);
        if (uniq.contains(parent))
            throw new IllegalStateException(tr._("Loop detected: ") + parent);
        uniq.add(parent);
        Collection<IComponent> children = parent.getDependancy();
        if (children == null)
            return true;
        L: for (IComponent child : children) {
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
    public void findDependentsBy(IComponent child, TreeCallback<IComponent> callback) {
        if (bydeps == null)
            refreshDependants();
        IdentityObjectSet uniq = new IdentityObjectSet();
        findDependentsBy(child, callback, 0, uniq);
    }

    boolean findDependentsBy(IComponent child, TreeCallback<IComponent> callback, int level, IdentityObjectSet uniq) {
        if (child == null)
            throw new NullPointerException("null component in rev-level " + level);
        if (uniq.contains(child))
            throw new IllegalStateException(tr._("Loop detected: ") + child);
        uniq.add(child);
        Set<IComponent> parents = bydeps.get(child);
        if (parents == null)
            return true;
        L: for (IComponent parent : parents) {
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
    public void analyseDependency(TreeCallback<IComponent> missingCallback) {
        IdentityObjectSet uniq = new IdentityObjectSet();
        analyseDependency(this, missingCallback, 0, uniq);
    }

    boolean analyseDependency(IComponent child, TreeCallback<IComponent> missingCallback, int level,
            IdentityObjectSet uniq) {
        if (uniq.contains(child))
            throw new IllegalStateException(tr._("Loop detected: ") + child);
        uniq.add(child);
        Collection<IComponent> dependancy = child.getDependancy();
        if (dependancy == null)
            return true;
        L: for (IComponent parent : dependancy) {
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
