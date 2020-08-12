package net.bodz.bas.repr.form;

import java.beans.Transient;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IMutableElement;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.cache.Statistics;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.element.IAnnotated;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.IndexColumn;
import net.bodz.bas.repr.form.meta.ListInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.typer.std.IValidator;
import net.bodz.bas.ui.dom1.MutableUiElement;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class MutableFieldDecl
        extends MutableUiElement
        implements IFieldDecl {

    private static final long serialVersionUID = 1L;

    IPropertyAccessor accessor;
    IProperty property;
    Class<?> valueType;
    Object valueOverride;

    int priority;
    FieldCategory category;

    String face;
    String styleClass;

    Boolean columnVisibility;
    Integer columnMaxLength;
    SortOrder preferredSortOrder = SortOrder.NONE;

    String inputName;
    boolean enabled = true;
    boolean readOnly;
    Integer maxLength;
    Double minValue;
    Double maxValue;
    Double stepValue;
    Integer textWidth;
    String inputMask;
    NumberFormat numberFormat;
    iString placeholder;
    char echoChar;
    NullConvertion nullConvertion = NullConvertion.EMPTY;
    SpaceNormalization spaceNormalization = SpaceNormalization.NONE;
    SortOrder itemSortOrder = SortOrder.NONE;

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
    public Object getValueOverride() {
        return valueOverride;
    }

    public void setValueOverride(Object value) {
        this.valueOverride = value;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public FieldCategory getCategory() {
        return category;
    }

    public void setCategory(FieldCategory category) {
        this.category = category;
    }

    @Override
    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public Boolean getColumnVisibility() {
        return columnVisibility;
    }

    public void setColumnVisibility(Boolean columnVisibility) {
        this.columnVisibility = columnVisibility;
    }

    @Override
    public Integer getColumnMaxLength() {
        return columnMaxLength;
    }

    public void setColumnMaxLength(Integer columnMaxLength) {
        this.columnMaxLength = columnMaxLength;
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
    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Integer getTextWidth() {
        return textWidth;
    }

    public void setTextWidth(Integer textWidth) {
        this.textWidth = textWidth;
    }

    @Override
    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    @Override
    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public Double getStepValue() {
        return stepValue;
    }

    public void setStepValue(Double stepValue) {
        this.stepValue = stepValue;
    }

    @Override
    public String getInputMask() {
        return inputMask;
    }

    public void setInputMask(String inputMask) {
        this.inputMask = inputMask;
    }

    @Override
    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    @Override
    public iString getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(iString placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public char getEchoChar() {
        return echoChar;
    }

    public void setEchoChar(Character echoChar) {
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
    public SortOrder getItemSortOrder() {
        return itemSortOrder;
    }

    public void setItemSortOrder(SortOrder itemSortOrder) {
        if (itemSortOrder == null)
            throw new NullPointerException("itemSortOrder");
        this.itemSortOrder = itemSortOrder;
    }

    @Override
    public Collection<IValidator<Object>> getValidators() {
        return validators;
    }

    @Override
    public void populate(IElementDoc doc)
            throws ParseException {
        super.populate(doc);
        this.placeholder = (iString) doc.getTag("placeholder");
    }

    public void populate(IAnnotated annotations) {
        Priority aPriority = annotations.getAnnotation(Priority.class);
        if (aPriority != null)
            this.setPriority(aPriority.value());

        DetailLevel aDetailLevel = annotations.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            this.setDetailLevel(aDetailLevel.value());

        if (annotations.getAnnotation(Derived.class) != null)
            this.setEnabled(false);

        if (annotations.getAnnotation(Transient.class) != null)
            this.setEnabled(false);

        if (annotations.getAnnotation(Statistics.class) != null)
            this.setEnabled(false);

        OfGroup aOfGroup = annotations.getAnnotation(OfGroup.class);
        if (aOfGroup != null) {
            Class<?>[] ofGroups = aOfGroup.value();
            if (ofGroups.length >= 1) {
                FieldCategory category = FieldCategory.fromTagClass(ofGroups[0]);
                this.setCategory(category);
            }
        }

        IndexColumn aIndexColumn = annotations.getAnnotation(IndexColumn.class);
        if (aIndexColumn != null) {
            int maxLength = aIndexColumn.maxLength();
            this.columnMaxLength = maxLength == -1 ? null : maxLength;
            this.columnVisibility = aIndexColumn.display() ? aIndexColumn.visible() ? true : false : false;
        }

        FormInput aFormInput = annotations.getAnnotation(FormInput.class);
        if (aFormInput != null) {
            String inputName = aFormInput.name();
            this.face = aFormInput.face();
            this.inputName = inputName.isEmpty() ? null : inputName;

            this.readOnly |= aFormInput.readOnly();
            if (aFormInput.textWidth() != 0)
                this.textWidth = aFormInput.textWidth();

            String s = aFormInput.inputMask();
            this.inputMask = s.isEmpty() ? null : s;

            s = aFormInput.placeholder();
            this.placeholder = iString.fn.val(s.isEmpty() ? null : s);

            this.nullConvertion = aFormInput.nullconv();
        }

        TextInput aTextInput = annotations.getAnnotation(TextInput.class);
        if (aTextInput != null) {
            if (aTextInput.maxLength() != 0)
                this.maxLength = aTextInput.maxLength();
            this.echoChar = aTextInput.echoChar();
            this.spaceNormalization = aTextInput.space();
        }

        NumericInput aNumericInput = annotations.getAnnotation(NumericInput.class);
        if (aNumericInput != null) {
            double min = aNumericInput.min();
            double max = aNumericInput.max();
            double step = aNumericInput.step();
            String pattern = aNumericInput.numberFormat();
            this.minValue = min == Double.MIN_VALUE ? null : min;
            this.maxValue = max == Double.MAX_VALUE ? null : max;
            this.stepValue = step == Double.NaN ? null : step;
            this.numberFormat = pattern.isEmpty() ? null : new DecimalFormat(pattern);
        }

        ListInput aListInput = annotations.getAnnotation(ListInput.class);
        if (aListInput != null) {
            this.itemSortOrder = aListInput.sort();
        }
    }

    public MutableFieldDecl populate(IProperty property)
            throws ParseException {
        this.setProperty(property);
        this.setReadOnly(!property.isWritable());
        this.setValueType(property.getPropertyType());

        IMutableElement.fn.copy1(property, this);

        Class<?> propertyType = property.getPropertyType();
        if (TypeKind.isNumeric(propertyType))
            this.setStyleClass("numeric");

        this.populate((IAnnotated) property);

        IElementDoc xjdoc = property.getXjdoc();
        if (xjdoc != null)
            this.populate(xjdoc);

        return this;
    }

}
