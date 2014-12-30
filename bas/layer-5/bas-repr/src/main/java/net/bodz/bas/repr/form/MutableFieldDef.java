package net.bodz.bas.repr.form;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.typer.std.IValidator;
import net.bodz.bas.ui.dom1.MutableUiElement;

public class MutableFieldDef
        extends MutableUiElement
        implements IFieldDef {

    private static final long serialVersionUID = 1L;

    IPropertyAccessor accessor;
    IProperty property;
    Class<?> valueType;
    Object valueOverride;
    int priority;
    SortOrder preferredSortOrder = SortOrder.NONE;
    FieldCategory fieldGroup;
    String styleClass;
    boolean readOnly;
    int maxLength;
    int textWidth;
    iString placeholder;
    NumberFormat numberFormat;
    char echoChar;
    NullConvertion nullConvertion = NullConvertion.NONE;
    SpaceNormalization spaceNormalization = SpaceNormalization.NONE;
    List<IValidator<Object>> validators;

    @Override
    public IPropertyAccessor getAccessor() {
        return accessor;
    }

    public void setAccessor(IPropertyAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public IProperty getProperty() {
        return property;
    }

    public void setProperty(IProperty property) {
        this.property = property;
        this.accessor = property;
    }

    @Override
    public Class<?> getValueType() {
        return valueType;
    }

    public void setValueType(Class<?> valueType) {
        this.valueType = valueType;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public SortOrder getPreferredSortOrder() {
        return preferredSortOrder;
    }

    public void setPreferredSortOrder(SortOrder sortOrder) {
        if (sortOrder == null)
            throw new NullPointerException("sortOrder");
        this.preferredSortOrder = sortOrder;
    }

    @Override
    public FieldCategory getCategory() {
        return fieldGroup;
    }

    public void setFieldGroup(FieldCategory fieldGroup) {
        this.fieldGroup = fieldGroup;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public int getTextWidth() {
        return textWidth;
    }

    public void setTextWidth(int textWidth) {
        this.textWidth = textWidth;
    }

    @Override
    public iString getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(iString placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public Object getValueOverride() {
        return valueOverride;
    }

    public void setValue(Object value) {
        this.valueOverride = value;
    }

    @Override
    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    @Override
    public char getEchoChar() {
        return echoChar;
    }

    public void setEchoChar(char echoChar) {
        this.echoChar = echoChar;
    }

    @Override
    public NullConvertion getNullConvertion() {
        return nullConvertion;
    }

    public void setNullConvertion(NullConvertion nullConvertion) {
        if (nullConvertion == null)
            throw new NullPointerException("nullConvertion");
        this.nullConvertion = nullConvertion;
    }

    @Override
    public SpaceNormalization getSpaceNormalization() {
        return spaceNormalization;
    }

    public void setSpaceNormalization(SpaceNormalization spaceNormalization) {
        if (spaceNormalization == null)
            throw new NullPointerException("spaceNormalization");
        this.spaceNormalization = spaceNormalization;
    }

    @Override
    public Collection<IValidator<Object>> getValidators() {
        return validators;
    }

}
