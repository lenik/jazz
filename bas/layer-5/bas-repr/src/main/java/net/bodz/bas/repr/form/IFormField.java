package net.bodz.bas.repr.form;

import java.text.NumberFormat;
import java.util.Collection;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.stereo.IMetadata;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.typer.std.IValidator;
import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.style.IInputBehaviorDeclaration;

public interface IFormField
        extends IMetadata, IUiElement, IPriority {

    // IFormStruct getFormStruct();

    IPropertyAccessor getAccessor();

    Class<?> getValueType();

    // value-override...
    Object getValue();

    /**
     * @return Non-<code>null</code> sort order.
     */
    SortOrder getPreferredSortOrder();

    /**
     * @return <code>null</code> if it's not belong to any group.
     */
    FieldGroup getFieldGroup();

    String getStyleClass();

    /**
     * @see IInputBehaviorDeclaration#getReadOnly()
     */
    boolean isReadOnly();

    /**
     * @see IInputBehaviorDeclaration#getMaxLength()
     */
    int getMaxLength();

    /**
     * It's the column width in the index view, and the text input width in the edit form view.
     */
    int getTextWidth();

    /**
     * @return <code>null</code> if no placeholder.
     */
    iString getPlaceholder();

    /**
     * @return <code>null</code> if not specified.
     */
    NumberFormat getNumberFormat();

    /**
     * @return <code>'\0'</code> if none.
     */
    char getEchoChar();

    /**
     * @return Non-<code>null</code> value.
     */
    NullConvertion getNullConvertion();

    /**
     * @return Non-<code>null</code> value.
     */
    SpaceNormalization getSpaceNormalization();

    Collection<IValidator<Object>> getValidators();

}
