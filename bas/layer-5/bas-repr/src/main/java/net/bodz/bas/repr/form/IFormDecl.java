package net.bodz.bas.repr.form;

import java.util.Collection;

import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.ui.dom1.IUiElement;

public interface IFormDecl
        extends IMetadata, IUiElement {

    IFieldDecl getFieldDef(String name);

    /**
     * @see FieldCategory#group(Iterable)
     */
    Collection<IFieldDecl> getFieldDefs();

    Collection<IFieldDecl> getFieldDefs(int maxDetailLevel);

}
