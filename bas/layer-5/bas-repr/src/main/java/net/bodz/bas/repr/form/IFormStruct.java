package net.bodz.bas.repr.form;

import java.util.Collection;
import java.util.Map;

import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.ui.dom1.IUiElement;

public interface IFormStruct
        extends IMetadata, IUiElement {

    IFormField getField(String name);

    Collection<IFormField> getFields();

    Map<FieldGroup, Collection<IFormField>> getFieldsGrouped(int maxDetailLevel);

}
