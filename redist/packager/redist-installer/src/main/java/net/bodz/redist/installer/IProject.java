package net.bodz.redist.installer;

import java.util.Map;

import org.eclipse.swt.graphics.ImageData;

import net.bodz.bas.collection.tree.TreeCallback;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.mda.xjdoc.model1.Author;

public interface IProject
        extends IComponent {

    /**
     * This should be a bigger logo image compared to {@link #getImage()}.
     */
    ImageData getLogo();

    IVersion getVersion();

    Author getCompany();

    String getLicense();

    String getUpdateTime();

    /**
     * The schemes, only used by installer.
     */
    Scheme[] getSchemes();

    /**
     * Get the variable definitions.
     * 
     * A session should copy this map using the default values.
     * 
     * @return must be non-<code>null</code>
     */
    Map<String, Variable> getVariables();

    Variable get(String variableName);

    BaseDirVariable getBaseDir(String variableName);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    void refreshDependants();

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    void findDependents(IComponent c, TreeCallback<IComponent> callback);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    void findDependentsBy(IComponent c, TreeCallback<IComponent> callback);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    void analyseDependency(TreeCallback<IComponent> missingCallback);

}
