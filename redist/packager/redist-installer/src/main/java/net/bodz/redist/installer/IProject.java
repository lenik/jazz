package net.bodz.redist.installer;

import java.util.Map;

import org.eclipse.swt.graphics.ImageData;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.t.tree.legacy.ITreeCallback;
import net.bodz.mda.xjdoc.model.artifact.Author;

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
    void findDependents(IComponent c, ITreeCallback<IComponent> callback);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    void findDependentsBy(IComponent c, ITreeCallback<IComponent> callback);

    /**
     * @throws IllegalStateException
     *             if loop detected
     */
    void analyseDependency(ITreeCallback<IComponent> missingCallback);

}
