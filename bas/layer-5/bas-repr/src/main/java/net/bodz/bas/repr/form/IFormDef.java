package net.bodz.bas.repr.form;

import java.util.Collection;

import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.ui.dom1.IUiElement;

public interface IFormDef
        extends IMetadata, IUiElement {

    IFieldDef getFieldDef(String name);

    /**
     * @see FieldCategory#group(Iterable)
     */
    Collection<IFieldDef> getFieldDefs();

    Collection<IFieldDef> getFieldDefs(int maxDetailLevel);

}
