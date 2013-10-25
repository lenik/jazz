package net.bodz.pkg.sis;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.vfs.IFile;

@IndexedType
public interface ISisProject
        extends ISisComponent, ISisComponentManager {

    IVersion getVersion();

    String getLicense();

    // ________________________________________________________________________
    // ⇱ Part: Variables
    //

    /**
     * Get the variable definitions.
     * 
     * A session should copy this map using the default values.
     * 
     * @return must be non-<code>null</code>
     */
    Map<String, SisVariable> getVariableMap();

    SisVariable getVariable(String varName);

    void setVariable(String name, SisVariable variable);

    /**
     * Get value of the specified variable.
     * 
     * @throws NoSuchElementException
     *             if variable isn't defined.
     */
    <T> T getValue(String varName);

    /**
     * @throws NoSuchElementException
     *             if variable isn't defined.
     */
    void setValue(String varName, Object value);

    String expand(String s);

    // ________________________________________________________________________
    // ⇱ Part: Session
    //

    ISisArchive getArchive();

    void setArchive(ISisArchive archive);

    void setArchive(IFile archiveFile);

    List<ISisInstallProfile> getInstallProfiles();

    /**
     * XXX configure() at once, this property is optinoal.
     */
    ISisInstallProfile getInstallProfile();

    void setInstallProfile(ISisInstallProfile profile);

    boolean isRebootRequired();

    void setRebootRequired(boolean rebootRequired);

}
